package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public void send() throws IOException, InterruptedException{
		Socket socket=new Socket("www.baidu.com",80);
		OutputStream os=socket.getOutputStream();
		boolean autoFlush=false;
		PrintWriter out=new PrintWriter(socket.getOutputStream(),autoFlush);
		BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		out.println("GET / HTTP/1.1");
		out.println("Host: localhost");
		out.println("Connection: Close");
		out.println();
		out.flush();
		
		boolean loop=true;
		StringBuffer sb=new StringBuffer(8096);
		while(loop){
			//if(in.ready()){   Cannot detect exceptions
				int i=0;
				while(i!=-1){
					i=in.read();
					sb.append((char)i);
				}
				loop=false;
			//}
			Thread.currentThread().sleep(50);
		}
		
		System.out.println(sb.toString());
		socket.close();
	}
	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		Client client=new Client();
		try {
			client.send();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
