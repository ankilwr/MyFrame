package com.shihang.myframe.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {

	public static <T> T getBean(String json,Class<T> t){
		if(CheckUtil.isJson(json)){
			Gson gson = new Gson();
			return gson.fromJson(json, t);
		}else{
			return null;
		}
	}
	

	public static <T> List<T> getList(String jsonArray,TypeToken<List<T>> typetoken){
		if(CheckUtil.isJsonArray(jsonArray)){
			Gson gson = new Gson();
			return gson.fromJson(jsonArray, typetoken.getType());
		}else{
			return null;
		}
	}


	public static String toJson(Object obj){
		Gson gson = new Gson();  
		return gson.toJson(obj);  
	}

	public static <T> Map<String, T> getGoodsMap(String json,Class<T> t){
		Gson gson = new Gson();
		Map<String,T> map = gson.fromJson(json, new TypeToken<HashMap<String, T>>(){}.getType());  
		return map;
	}
}
