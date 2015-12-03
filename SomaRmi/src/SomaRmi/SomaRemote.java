package SomaRmi;
import java.rmi.*;

public interface SomaRemote extends Remote
{
	public int operacao(int a, int b) throws RemoteException;
}
