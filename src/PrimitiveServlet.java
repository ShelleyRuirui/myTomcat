import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import ownServlet.SubRequest;

public class PrimitiveServlet implements Servlet {

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		if(req instanceof SubRequest)
			System.out.println("This is of SubRequest");
		
		System.out.println("from service");
		PrintWriter out=res.getWriter();
		out.println("Hello. Roses are red.");
		out.print("Violets are blue");
	}

}
