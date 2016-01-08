package Gerenciador;
import java.io.File;
import java.rmi.*;
import java.util.ArrayList;

public interface GerenciadorRemote extends Remote
{
	public ArrayList<String> getArquivos() throws RemoteException;
        public boolean escreverArquivo(String nameFile,ArrayList<String> file)throws RemoteException;
        public ArrayList<String> lerArquivo(String nomeFile)throws RemoteException;
        public int CountFile(String local)throws RemoteException;
        public boolean isAlive()throws RemoteException;
}      
