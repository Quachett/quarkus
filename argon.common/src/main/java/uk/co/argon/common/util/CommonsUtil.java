package uk.co.argon.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jaxb.JAXBBundle;

import uk.co.argon.common.exceptions.HttpException;

public class CommonsUtil {
	private static final Genson genson = new GensonBuilder().withBundle(new JAXBBundle()).setSkipNull(true).create();
	
    public static String listToString(List<String> attributes, String delimiter) {
    	if(delimiter==null)
    		delimiter = SynapseConstants.COMMA;
    	return StringUtils.join(attributes, delimiter + " ");
    }
    
    public static String getLineOfQs(int num) {
        return Joiner.on(", ").join(Iterables.limit(Iterables.cycle("?"), num));
    }
    
    public static String arrayRangeToString(Object[] obj, int start, int end) {
    	int i;
    	String result = "";
    	for(i=0;i<end;i++) {
    		if(i<end-1)
    			result += obj[i] + ", ";
    		else
    			result += obj[i];
    	}
        return "[" + result +"]";
    }
   
    public static <T> String performMarshaling(T pojo) throws HttpException {           
        try {
            Marshaller jaxbMarshaller = JaxbContextCache.getInstance().getContext(pojo.getClass()).createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            
            jaxbMarshaller.marshal(pojo, os);                       
            os.flush();
            
            String xml = new String(os.toByteArray(), StandardCharsets.UTF_8);
            RegExUtils.removeAll(xml, StringUtils.LF);
            
            System.out.println("======================================================");
            System.out.println(xml);
            System.out.println("======================================================\n\n");
            
            return xml;
        }
        catch (JAXBException | IOException e) {
            throw new HttpException(e.toString(), 500);
        }
    }
    
    public static <T> T performUnmarshalling(ByteArrayInputStream bais, Class<T> clazz) throws HttpException {
        Unmarshaller unmarshaller = null;
        
        try {
            unmarshaller = JaxbContextCache.getInstance().getContext(clazz).createUnmarshaller();
            T pojo = clazz.cast(unmarshaller.unmarshal(bais));
            return pojo;
        }catch (JAXBException e) {
            throw new HttpException(e.toString(), 500);
        }
    }
    
    public static <T> T[] swap(T[] t, int i, int j) {
		
		T temp = t[j];
		t[j] = t[i];
		t[i] = temp;
		
		return t;
	}
    
    public static <T> String getSerialisedObj(T t) {
    	return genson.serialize(t);
    }
}