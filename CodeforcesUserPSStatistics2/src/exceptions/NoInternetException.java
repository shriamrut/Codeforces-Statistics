package exceptions;
import java.net.*;
public class NoInternetException extends Exception{
	private static final long serialVersionUID = 1L;
	NoInternetException(String s)
	{
		super(s);
	}
	public static void checkForInternet() throws NoInternetException
	{
		 try { 
	            URL url = new URL("http://codeforces.com/"); 
	            URLConnection connection = url.openConnection(); 
	            connection.connect(); 
	            System.out.println("Connection Successful"); 
	        } 
	        catch (Exception e) { 
	        	throw new NoInternetException("NoInternetException");
	            //System.out.println("Internet Not Connected"); 
	        } 
	}
}
