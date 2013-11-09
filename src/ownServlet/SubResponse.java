package ownServlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import basic.Response;

public class SubResponse extends Response implements ServletResponse {

	PrintWriter writer;
	
	public SubResponse(OutputStream output) {
		super(output);
	}
	
	public PrintWriter getWriter() throws IOException{
		writer=new PrintWriter(output,true);
		return writer;
	}

	@Override
	public void flushBuffer() throws IOException {
	}

	@Override
	public int getBufferSize() {
		
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		
		return null;
	}

	@Override
	public String getContentType() {
		
		return null;
	}

	@Override
	public Locale getLocale() {
		
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		
		return null;
	}

	@Override
	public boolean isCommitted() {
		
		return false;
	}

	@Override
	public void reset() {
		

	}

	@Override
	public void resetBuffer() {
		

	}

	@Override
	public void setBufferSize(int arg0) {
		

	}

	@Override
	public void setCharacterEncoding(String arg0) {
		

	}

	@Override
	public void setContentLength(int arg0) {
		

	}

	@Override
	public void setContentType(String arg0) {
		

	}

	@Override
	public void setLocale(Locale arg0) {
		

	}

}
