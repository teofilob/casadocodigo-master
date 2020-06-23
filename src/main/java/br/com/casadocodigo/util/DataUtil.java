package br.com.casadocodigo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-DD");
	private static SimpleDateFormat formatPT = new SimpleDateFormat("dd/MM/YYYY");
	
	public static GregorianCalendar stringToDate(String valor) throws ParseException {
		
		if(valor == null || valor.isEmpty() ) {
			return null;
		}
		
		String[] intDate = valor.split("-"); 
		
		return new GregorianCalendar(Integer.valueOf( intDate[0] )  , Integer.valueOf(intDate[1]) -1 , Integer.valueOf(intDate[2])  ); 

	}

	public static String dataToString(Date date) {
		// TODO Auto-generated method stub
		return formatPT.format(date);
	}
	
	
	
}
