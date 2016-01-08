package Gerenciador;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GerenciadorServer{
	public static void main(String args[])
	{
		try{
                        Registry registry = LocateRegistry.createRegistry(1099); 
			Naming.bind("Arquivo", new Gerenciador());
			System.out.println("O servidor esta rodando");
		}
		catch(Exception e){
			System.out.println("Problemas! N�o foi poss�vel inicializar o servidor!");
			e.printStackTrace();
		}
	}
}

