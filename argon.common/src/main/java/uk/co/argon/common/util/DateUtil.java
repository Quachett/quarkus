package uk.co.argon.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ws.rs.core.Response.Status;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import uk.co.argon.common.exceptions.HttpException;

public class DateUtil {
    private static final String DATE_FORMAT = "dd-MMM-yyy";
    public static final String ADD_CLAIM_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static XMLGregorianCalendar getDate(Date date) throws HttpException {
        
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
         
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            
            return xgc;
         } catch (DatatypeConfigurationException e) {
             e.printStackTrace();
             throw new HttpException(e.getMessage(),Status.INTERNAL_SERVER_ERROR.getStatusCode());
         }
    }

    public static XMLGregorianCalendar getDateTime() throws HttpException {
        
        try {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(new Date());
         
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            
            return xgc;
         } catch (DatatypeConfigurationException e) {
             e.printStackTrace();
             throw new HttpException(e.getMessage(),Status.INTERNAL_SERVER_ERROR.getStatusCode());
         }
    }
    
    public static XMLGregorianCalendar addTimetoDate(XMLGregorianCalendar date) throws HttpException {
        try {
            Date formatedDate = null;
            String time = "T12:00:00";
            String dateTime = date.toString() + time;
            DateFormat df = new SimpleDateFormat(ADD_CLAIM_DATE_FORMAT);
            
            formatedDate = df.parse(dateTime);
            
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(formatedDate);
         
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            return xgc;
        } catch (ParseException | DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new HttpException(e.getMessage(),Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
    
    public static XMLGregorianCalendar setDate(String date) throws HttpException {
        
        try {
            Date formatedDate = null;
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            
            if(StringUtils.isNotBlank(date))
                formatedDate = df.parse(date);
            else
                formatedDate = new Date();
            
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(formatedDate);
         
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(cal.get(Calendar.YEAR),
              cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),DatatypeConstants.FIELD_UNDEFINED);
            return xgc;
        } catch (ParseException | DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new HttpException(e.getMessage(),Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
    
    public static XMLGregorianCalendar setDateTime(String date) throws HttpException {
        
        try {
            Date formatedDate = null;
            DateFormat df = new SimpleDateFormat(ADD_CLAIM_DATE_FORMAT);
            
            if(StringUtils.isNotBlank(date))
                formatedDate = df.parse(date);
            else
                formatedDate = new Date();
            
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(formatedDate);
         
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            return xgc;
        } catch (ParseException | DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new HttpException(e.getMessage(),Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
    }
    
    
}
