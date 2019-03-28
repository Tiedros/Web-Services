package com.tiedros.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;



public class Driver {

	public static void main(String[] args) {
		
		try {
			
			// create object mapper
			ObjectMapper mapper=new ObjectMapper();
			
			// read json and map/convert to java POJO
			// data/sample-lite.json
			Student theStudent=mapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print firstName and lastName
			System.out.println("FirstName: "+ theStudent.getFirstName());
			System.out.println("LastName: "+ theStudent.getLastName());
			
			// print out: street and city
			
			Address theAddress= theStudent.getAddress();
			System.out.println("street: "+ theAddress.getStreet());
			System.out.println("city: "+ theAddress.getCity());
			
			
			// print out: languages 
			for(String language : theStudent.getLanguages()) {
				System.out.println(language);
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}

	}

}
