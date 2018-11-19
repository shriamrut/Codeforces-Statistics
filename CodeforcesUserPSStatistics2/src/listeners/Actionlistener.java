package listeners;
import java.awt.event.*;
import javax.swing.JOptionPane;
import org.json.*;
import exceptions.NoInternetException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import guis.GUI1;
import guis.PieChart;
import org.jfree.ui.RefineryUtilities;
public class Actionlistener implements ActionListener {
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Get Statistics"))
		{
			try
			{
				NoInternetException.checkForInternet();
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(GUI1.frame, "Not Connected to the Internet");
				System.out.println(e1.getMessage());
				return;
			}
			try {
				dataRetrivalForStatistics(GUI1.textField.getText());
			}
			catch(IOException io)
			{
				JOptionPane.showMessageDialog(GUI1.frame, "Invalid User id");
				return;
			}
			catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	public static void dataRetrivalForStatistics(String usr) throws Exception
	{
		int start=1,total=0;
		HashMap<String,Integer>tg=new HashMap<String,Integer>();
		while(true)
		{
			String url=" http://codeforces.com/api/user.status?handle="+usr+"&from="+start+"&count=1000";
			URL obj=new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			int rc=con.getResponseCode();
			System.out.println(rc);
			BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));;
			  String inputLine;
			     StringBuilder response = new StringBuilder();
			     while ((inputLine = br.readLine()) != null) {
			     	response.append(inputLine);
			     }
			     br.close();
			 String x=response.toString();
			 JSONObject json = new JSONObject(x);
			 JSONArray arr=json.getJSONArray("result");
			 for(int i=0;i<arr.length();i++)//arr.length
			 {
				 if(arr.getJSONObject(i).getString("verdict").equals("OK"))
				 {
					 total+=1;
					 JSONObject json1=(JSONObject)arr.getJSONObject(i).get("problem");
					 JSONArray arr2=json1.getJSONArray("tags");
					 for(int j=0;j<arr2.length();j++)
					 {
						 String y=arr2.getString(j);
						 if(tg.containsKey(y))
							 tg.put(y,tg.get(y)+1);
					     else
					    	 tg.put(y,1);
							
					 }
				}
			 }
			 if(arr.length()<1000)
				 break;
			 else
				 {
				 System.out.println("Fetching more");
				 start+=1000;
				 }
		}
		System.out.println("Total problemes solved by the user: "+total);
		for(String s:tg.keySet())
			System.out.println(s+" "+tg.get(s));
		PieChart pc=new PieChart("Codeforces Problem Solving Statistics of user "+usr,tg,total);
		pc.setSize(560,367);
		RefineryUtilities.centerFrameOnScreen(pc); 
		pc.setVisible(true);
		
	}

}
