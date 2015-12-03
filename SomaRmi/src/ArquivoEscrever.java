/*##############################################################################
  #   Programa que escreve um arquivo no disco a partir de entradas do usuário #
  ##############################################################################
  #                Bruno Silvério Costa    -   FTC  -  29/03/2007              #
  ##############################################################################*/
import java.io.*;
import javax.swing.*;

public class ArquivoEscrever
{
	public static void main(String args[])
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter("teste.txt"));
			try
			{
				String linha="";
				while(!linha.equals("sair"))
				{
					linha=JOptionPane.showInputDialog("Entre com uma linha de texto(Digite \"sair\" para finalizar)");
					if((!linha.equals("sair"))&&(!linha.equals(null)))
						out.write(linha+"\n");
				}
				out.close();
			}
			catch(FileNotFoundException e)
			{
				System.err.println(e);
			}
		}
		catch(IOException e)
		{
			System.err.println(e);
			System.exit(1);
		}
	}
}
