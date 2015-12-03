package SomaRmi;
import javax.swing.*;
import java.rmi.*;

public class SomaClient{
	public static void main(String args[])
	{
		try{
			String number1, number2;
			int num1, num2;	
		
			number1 = JOptionPane.showInputDialog("Digite um numero");
			number2 = JOptionPane.showInputDialog("Digite outro numero");
			
			num1 = Integer.parseInt(number1);
			num2 = Integer.parseInt(number2);	
					
			SomaRemote soma = (SomaRemote) Naming.lookup("//localhost/Soma");
			JOptionPane.showMessageDialog(null, "A soma �: " + soma.operacao(num1, num2),"Adi��o de numeros cliente/servidor",
			JOptionPane.INFORMATION_MESSAGE);
			//System.out.println(soma.operacao());
		}
		catch(Exception e)
		{
			System.out.println("SomaClient exception:" + e);
		}
	}
}