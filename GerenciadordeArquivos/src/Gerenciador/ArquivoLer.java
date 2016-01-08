package Gerenciador;

import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class ArquivoLer {

   public ArrayList<String> lerArquivo(String nomeFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(nomeFile));
            try {
                String linha = "";
                ArrayList<String> texto= new ArrayList();
                int indice=0;
                while (linha != null) {
                    linha = in.readLine();
                    if (linha != null) {
                        texto.add(linha);
                        indice++;
                    }
                }
                return texto;
                //escreverArquivo(texto);
               // in.close();
            } catch (IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
            System.exit(1);
        }
        return null;
    }
}
