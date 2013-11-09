package basic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Basic server that hands out static resource or 404 not found
 */

public class HttpServer {

	public static final String WEB_ROOT=System.getProperty("user.dir")+File.separator+
			"webroot";
	
	protected static final String SHUTDOWN_COMMAND="/SHUTDOWN";
	
	protected boolean shutdown=false;
	
	public void await(){
		ServerSocket serverSocket=null;
		int port=8080;
		try{
			serverSocket=new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		while(!shutdown){
			Socket socket=null;
			InputStream input=null;
			OutputStream output=null;
			
			try{
				socket=serverSocket.accept();
				input=socket.getInputStream();
				output=socket.getOutputStream();
				
				Request request=createReq(input);
				request.parse();
				
				Response response=createRes(output);
				response.setRequest(request);
				
				dispatch(request,response);
				
				socket.close();
				
				shutdown=request.getUri().equals(SHUTDOWN_COMMAND);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	protected void dispatch(Request req,Response response) throws IOException{
		response.sendStaticResource();
	}
	
	protected Request createReq(InputStream input){
		return new Request(input);
	}
	
	protected Response createRes(OutputStream output){
		return new Response(output);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpServer server=new HttpServer();
		server.await();
	}

}
