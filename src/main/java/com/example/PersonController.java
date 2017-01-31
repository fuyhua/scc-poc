package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
	@RequestMapping(method=RequestMethod.GET,
					path="/person",
					produces="application/json")
	@ResponseBody
	public String getPerson() {
		String response = new String();
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("file/person.json").getFile());
		String filePath = file.getAbsolutePath().replace("%20", " ");		
		//String filePath = "D:/Files/person.json";
		
		JSONParser parser = new JSONParser();
        try
        {
            Object object = parser
                    .parse(new FileReader(filePath));
            
            JSONObject jsonObject = (JSONObject)object;
            
            response = jsonObject.toString();
        }
        catch(FileNotFoundException fe)
        {
            fe.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }	
		
		return response;
	}
}
