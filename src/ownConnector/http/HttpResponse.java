package ownConnector.http;

import java.io.OutputStream;

public class HttpResponse {

	OutputStream output;
	HttpRequest request;
	
	public HttpResponse(OutputStream output){
		this.output=output;
	}
	
	public void setRequest(HttpRequest request){
		this.request=request;
	}
	
	public void setHeader(String name,String value){
		//TODO
	}
}
