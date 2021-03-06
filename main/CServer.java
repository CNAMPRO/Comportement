package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Vector; 

public class CServer
{ 

	private int m_nbClient =0 ; //nb Client Connecter
	private Vector m_TabClient = new Vector(); //TabClient
	
	public static void main(String args[]) 
	{ 
		CServer server = new CServer();
		
		try
		{
			Integer port = 40000;
			
			new CommandesServ(server);
			ServerSocket ss = null;
			try
			{
				ss = new ServerSocket(port);
				printBienvenue(port);
			} catch (UnknownHostException e) 
			{
			    e.printStackTrace();
			} catch (IOException e) 
			{
			   e.printStackTrace();
			}
			
			while(true)
			{
				new ServerThread(ss.accept(),server);
			}
		}
		catch(Exception e) {System.out.println(e);}
	}
	
	static private void printBienvenue(Integer port)
	{
		System.out.println("Ouai Bienvenue");
	}
	
	synchronized public void sendAll(String message,String sLast)
	{
		PrintWriter out;
		for(int i = 0; i<m_TabClient.size();i++)
		{
			out = (PrintWriter) m_TabClient.elementAt(i);
			if(out != null)
			{
				out.print(message + sLast);
				out.flush();
			}
		}
	}
	
	
	  synchronized public void delClient(int i)
	  {
		  m_nbClient--; // del client
	    if (m_TabClient.elementAt(i) != null) 
	    {
	    	m_TabClient.removeElementAt(i);
	    }
	  }

	  synchronized public int addClient(PrintWriter out)
	  {
		  m_nbClient++; 
		  m_TabClient.addElement(out); 
	    return m_TabClient.size()-1; // on retourne le num�ro du client ajout� (size-1)
	  }

	  synchronized public int getNbClients()
	  {
	    return m_nbClient; // retourne le nombre de clients connect�s
	  }
	
} 
