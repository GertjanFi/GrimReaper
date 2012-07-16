/*******************************************************************************
 * Copyright (c) 2012 Huygens ING.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Huygens ING - initial API and implementation
 ******************************************************************************/
package nl.knaw.huygens.grim.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

	public static String normalize(String value) {
	      String temp = Normalizer.normalize(value, Normalizer.Form.NFD);
	      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	      return pattern.matcher(temp).replaceAll("");		
	}
	
	public static int lastIndexOfUCL(String value) {        
	    for(int i=value.length()-1; i>=0; i--) {
	        if(Character.isUpperCase(value.charAt(i))) {
	            return i;
	        }
	    }
	    return -1;
	}
	
	public static String dropFromLastUpperCase(String value) {
		int i = StringUtils.lastIndexOfUCL(value);
		value = i != -1 ? value.substring(0, i) : "";
		if (value.endsWith("_") && i > 0) {
			return value.substring(0, i-1);
		} return value;
	}
}
