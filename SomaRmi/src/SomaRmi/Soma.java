package SomaRmi;
import java.rmi.*;
import java.rmi.server.*;

public class Soma extends UnicastRemoteObject implements SomaRemote
{
	
	private int c;
	
	public Soma() throws RemoteException{}
	public int operacao(int a,int b ) throws RemoteException
	{
		return a + b;
	}
}