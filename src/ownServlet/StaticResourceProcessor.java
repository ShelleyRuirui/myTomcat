package ownServlet;

import java.io.IOException;

import basic.Request;
import basic.Response;

public class StaticResourceProcessor {

	public void process(Request req,Response res){
		try{
			res.sendStaticResource();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
