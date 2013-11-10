package ownConnector.http;

import java.io.OutputStream;
import java.net.Socket;

import javax.servlet.ServletException;

import ownConnector.io.SocketInputStream;

public class HttpProcessor {

	HttpConnector connector;
	HttpRequest request;
	HttpResponse response;
	HttpRequestLine requestLine;
	
//	protected StringManager sm=StringManager
	
	public HttpProcessor(HttpConnector conn){
		connector=conn;
	}
	
	public void process(Socket socket){
		SocketInputStream input=null;
		OutputStream output=null;
		try{
			input=new SocketInputStream(socket.getInputStream(),2048);
			output=socket.getOutputStream();
			
			request=new HttpRequest(input);
			
			response=new HttpResponse(output);
			response.setRequest(request);
			
			parseRequest(input,output);
			parseHeaders(input);
			
			if(request.getRequestURI().startsWith("/servlet")){
				ServletProcessor processor=new ServletProcessor();
				processor.process(request, response);
			}else{
				StaticResourceProcessor processor=new StaticResourceProcessor();
				processor.process(request, response);
			}
			
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void parseRequest(SocketInputStream input,OutputStream output) throws ServletException{
		input.readRequestLine(requestLine);
		String method=new String(requestLine.method,0,requestLine.methodEnd);
		String uri=null;
		String protocol=new String(requestLine.protocol,0,requestLine.protocolEnd);
		
		if(method.length()<1)
			throw new ServletException("Missing HTTP request method");
		else if(requestLine.uriEnd<1)
			throw new ServletException("Missing HTTP request URI");
		
		int question=re
	}
	
	public void parseHeaders(SocketInputStream input){
		//TODO
	}
}
