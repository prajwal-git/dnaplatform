package com.dna.backend.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Nischal Dahal
 * @since 2022/02/22
 * 
 **/


@RestController
@RequestMapping("/zoom")
public class ZoomController {

	
	@GetMapping("/Java1007")//producer, creating a restful services
	public String getAllZoomDetails1007() {
		
		String zoomCall;
		JSONObject json = new JSONObject();
		json.put("page_count", "1");
		json.put("page_number", "1");
		json.put("page_size", "1");
		
		JSONArray jArray = new JSONArray();

		JSONObject json1 = new JSONObject();
		json1.put("userName", "anirudra");
		json1.put("email","anirudram@hotmail.com");
		json1.put("meetingDate", "2022-02-22 18:50:00");
		json1.put("loginTime", "2022-02-22 18:51:00");
		json1.put("logoutTime", "2022-02-22 19:30:00");
		json1.put("meetingName", "Java1007");
		jArray.put(json1);
		
		JSONObject json2 = new JSONObject();
		json2.put("userName", "aman");
		json2.put("email","aman@gmail.com");
		json2.put("meetingDate", "2022-02-22 18:50:00");
		json2.put("loginTime", "2022-02-22 18:51:00");
		json2.put("logoutTime", "2022-02-22 19:30:00");
		json2.put("meetingName", "Java1007");
		jArray.put(json2);
		
		JSONObject json3 = new JSONObject();
		json3.put("userName", "mita");
		json3.put("email","mita@gmail.com");
		json3.put("meetingDate", "2022-02-22 18:50:00");
		json3.put("loginTime", "2022-02-22 18:51:00");
		json3.put("logoutTime", "2022-02-22 19:30:00");
		json3.put("meetingName", "Java1007");
		jArray.put(json3);
		
		JSONObject json4 = new JSONObject();
		json4.put("userName", "nischal");
		json4.put("email","nischal@gmail.com");
		json4.put("meetingDate", "2022-02-22 18:50:00");
		json4.put("loginTime", "2022-02-22 18:51:00");
		json4.put("logoutTime", "2022-02-22 19:30:00");
		json4.put("meetingName", "Java1007");
		jArray.put(json4);
		
		JSONObject json5 = new JSONObject();
		json5.put("userName", "shashank");
		json5.put("email","shashank@gmail.com");
		json5.put("meetingDate", "2022-02-22 18:50:00");
		json5.put("loginTime", "2022-02-22 18:51:00");
		json5.put("logoutTime", "2022-02-22 19:30:00");
		json5.put("meetingName", "Java1007");
		jArray.put(json5);
		
		JSONObject json6 = new JSONObject();
		json6.put("userName", "swas");
		json6.put("email","swas@gmail.com");
		json6.put("meetingDate", "2022-02-22 18:50:00");
		json6.put("loginTime", "2022-02-22 18:51:00");
		json6.put("logoutTime", "2022-02-22 19:30:00");
		json6.put("meetingName", "Java1007");
		jArray.put(json6);
		
		JSONObject json7 = new JSONObject();
		json7.put("userName", "tika");
		json7.put("email","tika@gmail.com");
		json7.put("meetingDate", "2022-02-22 18:50:00");
		json7.put("loginTime", "2022-02-22 18:52:00");
		json7.put("logoutTime", "2022-02-22 19:31:00");
		json7.put("meetingName", "Java1007");
		jArray.put(json7);
		
		JSONObject json8 = new JSONObject();
		json8.put("userName", "unregistered");
		json8.put("email","unregistered@gmail.com");
		json8.put("meetingDate", "2022-02-22 18:50:00");
		json8.put("loginTime", "2022-02-22 18:52:00");
		json8.put("logoutTime", "2022-02-22 19:31:00");
		json8.put("meetingName", "Java1007");
		jArray.put(json8);
		
		json.put("participants", jArray);

		zoomCall = json.toString();
		
		return zoomCall;
	}
	@GetMapping("/Java1008")
	public String getAllZoomDetails1008() {
		
		String zoomCall;
		JSONObject json9 = new JSONObject();
		
		json9.put("page_count", "1");
		json9.put("page_number", "1");
		json9.put("page_size", "1");
		JSONArray jArray1 = new JSONArray();

		JSONObject json10 = new JSONObject();
		json10.put("userName", "peter");
		json10.put("email","peter@gmail.com");
		json10.put("meetingDate", "2022-02-22 19:50:00");
		json10.put("loginTime", "2022-02-22 19:51:00");
		json10.put("logoutTime", "2022-02-22 20:30:00");
		json10.put("meetingName", "Java1008");
		
		jArray1.put(json10);
		
		JSONObject json11 = new JSONObject();
		json11.put("userName", "mark");
		json11.put("email","mark@gmail.com");
		json11.put("meetingDate", "2022-02-22 19:50:00");
		json11.put("loginTime", "2022-02-22 19:52:00");
		json11.put("logoutTime", "2022-02-22 20:31:00");
		json11.put("meetingName", "Java1008");
//		
//		
		jArray1.put(json11);
		json9.put("participants", jArray1);

		zoomCall = json9.toString();
		
		return zoomCall;
	}
	
	@GetMapping("/Java1009")//producer, creating a restful services
	public String getAllZoomDetails1009() {
		
		String zoomCall;
		JSONObject json12 = new JSONObject();
		json12.put("page_count", "1");
		json12.put("page_number", "1");
		json12.put("page_size", "1");
		
		JSONArray jArray2 = new JSONArray();

		JSONObject json13 = new JSONObject();
		json13.put("userName", "anirudra");
		json13.put("email","anirudra@gmail.com");
		json13.put("meetingDate", "2022-02-22 18:50:00");
		json13.put("loginTime", "2022-02-22 18:51:00");
		json13.put("logoutTime", "2022-02-22 19:30:00");
		json13.put("meetingName", "Java1007");
		jArray2.put(json13);
		
		JSONObject json14 = new JSONObject();
		json14.put("userName", "aman");
		json14.put("email","aman@gmail.com");
		json14.put("meetingDate", "2022-02-22 18:50:00");
		json14.put("loginTime", "2022-02-22 18:51:00");
		json14.put("logoutTime", "2022-02-22 19:30:00");
		json14.put("meetingName", "Java1007");
		jArray2.put(json14);
		
		JSONObject json15 = new JSONObject();
		json15.put("userName", "mita");
		json15.put("email","mita@gmail.com");
		json15.put("meetingDate", "2022-02-22 18:50:00");
		json15.put("loginTime", "2022-02-22 18:51:00");
		json15.put("logoutTime", "2022-02-22 19:30:00");
		json15.put("meetingName", "Java1007");
		jArray2.put(json15);
		
		JSONObject json16 = new JSONObject();
		json16.put("userName", "nischal");
		json16.put("email","nischal@gmail.com");
		json16.put("meetingDate", "2022-02-22 18:50:00");
		json16.put("loginTime", "2022-02-22 18:51:00");
		json16.put("logoutTime", "2022-02-22 19:30:00");
		json16.put("meetingName", "Java1007");
		jArray2.put(json16);
		
		JSONObject json17 = new JSONObject();
		json17.put("userName", "shashank");
		json17.put("email","shashank@gmail.com");
		json17.put("meetingDate", "2022-02-22 18:50:00");
		json17.put("loginTime", "2022-02-22 18:51:00");
		json17.put("logoutTime", "2022-02-22 19:30:00");
		json17.put("meetingName", "Java1007");
		jArray2.put(json17);
		
		JSONObject json18 = new JSONObject();
		json18.put("userName", "swas");
		json18.put("email","swas@gmail.com");
		json18.put("meetingDate", "2022-02-22 18:50:00");
		json18.put("loginTime", "2022-02-22 18:51:00");
		json18.put("logoutTime", "2022-02-22 19:30:00");
		json18.put("meetingName", "Java1007");
		jArray2.put(json18);
		
		JSONObject json19 = new JSONObject();
		json19.put("userName", "tika");
		json19.put("email","tika@gmail.com");
		json19.put("meetingDate", "2022-02-22 18:50:00");
		json19.put("loginTime", "2022-02-22 18:52:00");
		json19.put("logoutTime", "2022-02-22 19:31:00");
		json19.put("meetingName", "Java1007");
		jArray2.put(json19);
		
		json12.put("participants", jArray2);

		zoomCall = json12.toString();
		
		return zoomCall;
	}

}
