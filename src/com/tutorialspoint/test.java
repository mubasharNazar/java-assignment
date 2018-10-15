package com.tutorialspoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.processing.FilerException;
import javax.json.*;
import javax.json.stream.JsonParser;


public class test extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		File f = new File("jsondata1.txt");
		if (f.exists()){
			InputStream fis = new FileInputStream("jsondata1.txt");
			JsonReader reader = Json.createReader(fis);
	        JsonArray arr = reader.readArray();
	        resp.getWriter().println("Data Stored:");
	        resp.getWriter().println(arr);
        }else{
        	resp.getWriter().println("No record found:");
        }
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String name = req.getParameter("username");
		String age = req.getParameter("age");
		
		JsonObject obj = Json.createObjectBuilder()
        		.add("id", id)
        		.add("username", name)
        		.add("age", age)
        		.build();
		
		try{
			int a = Integer.parseInt(age);
			int b = Integer.parseInt(id);
			int c = age.length();
			if (name.length()<6 || (a>=0) || (b>0)){
				Integer.parseInt("exception will be thrown with this");
			}
				
			File f = new File("jsondata1.txt");
			
			if(f.exists()){
				InputStream fis = new FileInputStream("jsondata1.txt");
				JsonReader reader = Json.createReader(fis);
		        JsonArray arr = reader.readArray();
		        
		        
		        try(FileWriter fw = new FileWriter("jsondata1.txt");
					    JsonWriter jsonWriter = Json.createWriter(fw);) {

		        	
		        	JsonArrayBuilder builder1 = Json.createArrayBuilder();
		        	int v = 0;
			        for (int i=0; i<arr.size(); i++){
			        	JsonObject ob = (JsonObject) arr.get(i);
			        	if(ob.getString("id").equals(id)){
			        		v=1;
			        		
			        		builder1.add(obj);
			        	}
			        	else{
			        		builder1.add(ob);
			        	}
			        }
			        if (v==0){
			        	
		        		builder1.add(obj);
			        	
			        }
			        JsonArray val1 = builder1.build();
					
					jsonWriter.writeArray(val1);
					jsonWriter.close();
					    }
			}
			else{
				try(FileWriter fw = new FileWriter("jsondata1.txt");
					    JsonWriter jsonWriter = Json.createWriter(fw);) {

					
					JsonArray value = Json.createArrayBuilder()
						     .add(obj)
						     .build();
					jsonWriter.writeArray(value);
					jsonWriter.close();
					    }
				
			}
			//resp.sendRedirect("/Usermanagement");
			resp.getWriter().println(obj);
			
		}
		catch(Exception e){
			resp.getWriter().println("Id must a digit greater then 0, Username should not be less then 6 characters and Age must be a digit non negative");
		}
		
				


	}
}
