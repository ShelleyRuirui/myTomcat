package ownServlet;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

public class ServletProcessor1 {

	public void process(SubRequest req,SubResponse res){
		String uri=req.getUri();
		String servletName=uri.substring(uri.lastIndexOf("/")+1);
		URLClassLoader loader=null;
		try{
			URL[] urls=new URL[1];
			URLStreamHandler streamHandler=null;
			File classPath=new File(Constants.WEB_ROOT);
			String repository=(new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
			
			urls[0]=new URL(null,repository,streamHandler);
			loader=new URLClassLoader(urls);
		}catch(IOException e){
			e.printStackTrace();
			res.writer.println(e.toString());
			return;
		}
		
		@SuppressWarnings("rawtypes")
		Class myClass=null;
		try{
			myClass=loader.loadClass(servletName);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			res.writer.println(e.toString());
			return;
		}
		
		Servlet servlet=null;
		
		try{
			servlet=(Servlet)myClass.newInstance();
			servlet.service(req, res);
		}catch(Exception e){
			e.printStackTrace();
			res.writer.println(e.toString());
			return;
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
}
