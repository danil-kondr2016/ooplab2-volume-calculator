package ru.danilakondr.volumes.web;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class QueryUtils {

	public static HashMap<String, String> getQueryMap(String queryString)
    {
    	HashMap<String, String> result = new HashMap<>();
    	
    	for (String query : queryString.split("\\&")) {
    		String[] pair = query.split("=");
    		String key, value;
    		
    		key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8);
    		if (pair.length >= 2)
    			value = URLDecoder.decode(pair[1], StandardCharsets.UTF_8);
    		else
    			value = "";
    		
    		result.put(key, value);
    	}
    	
    	return result;
    }
}
