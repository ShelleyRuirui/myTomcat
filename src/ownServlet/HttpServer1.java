package ownServlet;

import java.io.InputStream;
import java.io.OutputStream;

import basic.HttpServer;
import basic.Request;
import basic.Response;

public class HttpServer1 extends HttpServer {

	protected void dispatch(Request req,Response res){
		if(req.getUri().startsWith("/servlet/")){
			ServletProcessor1 processor=new ServletProcessor1();
			processor.process((SubRequest)req,(SubResponse)res);
		}else{
			StaticResourceProcessor processor=new StaticResourceProcessor();
			processor.process(req, res);
		}
	}
	
	protected Request createReq(InputStream input){
		return new SubRequest(input);
	}
	
	protected Response createRes(OutputStream output){
		return new SubResponse(output);
	}
	
	public static void main(String[] args) {
		HttpServer1 server=new HttpServer1();
		server.await();
	}
}
