/*################################################################
  #   Programa que lê um arquivo no disco e exibe o seu conteúdo #
  ################################################################
  #        Bruno Silvério Costa    -   FTC  -  29/03/2007        #
  ################################################################*/
import java.io.*;
import javax.swing.*;

public class ArquivoLer
{
	public static void main(String args[])
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("teste.txt"));
			try
			{
				String linha="",texto="";
				while(linha!=null)
				{
					linha = in.readLine();
					if(linha!=null)
						texto=texto+"\n"+linha;
				}
				JOptionPane.showMessageDialog(null,texto);
				in.close();
			}
			catch( IOException e )
			{
				System.err.println(e);
				System.exit(1);
			}
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,"Arquivo não encontrado");
			System.exit(1);
		}
	}

}

