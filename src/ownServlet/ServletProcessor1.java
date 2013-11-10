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
			File classPath=new File(Constants.SERVLET_CLASS_PATH);
			
			//Seems OK if replace the complex way in the book
//			String repository=(new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
//			String classpathStr=classPath.getCanonicalPath()+File.separator;
//			System.out.println(classpathStr);
//			System.out.println("Respository:"+repository);
//			
//			urls[0]=new URL(null,repository,streamHandler);
			
			urls[0]=new URL("file",null,classPath.getCanonicalPath()+File.separator);
			loader=new URLClassLoader(urls);
		}catch(IOException e){
			e.printStackTrace();
			res.writer.println(e.toString());
			return;
		}
		
		@SuppressWarnings("rawtypes")
		Class myClass=null;
		try{
			System.out.println("ServletName:"+servletName);
			myClass=loader.loadClass(servletName);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			res.writer.println(e.toString());
			return;
		}
		
		Servlet servlet=null;
		
		try{
			servlet=(Servlet)myClass.newInstance();
			//By using facade, seems service method has no way to access SubRequest
			RequestFacade requestFacade=new RequestFacade(req);
			servlet.service(requestFacade, res);
		}catch(Exception e){
			e.printStackTrace();
			res.writer.println(e.toString());
			return;
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
}
