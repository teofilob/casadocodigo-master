package br.com.casadocodigo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("YYYY-mm-dd");
	
	public static Date stringToDate(String valor) throws ParseException {
		return format.parse(valor);
	}
	
	
}
