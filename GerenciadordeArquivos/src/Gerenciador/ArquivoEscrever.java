package Gerenciador;

import java.io.*;
import java.util.ArrayList;

public class ArquivoEscrever {

      public boolean escreveArquivo(String nameFile,ArrayList<String> file) {
        /*
         ArquivoEscrever arqEsc= new ArquivoEscrever();
        
         while (true) {
            
         arqEsc = new ArquivoEscrever();
         arqEsc.setNomeArquivo("copia_"+File);
         arqEsc.criarArquivo();
         while (!File.equals("null")) {
                
         arqEsc.gravarLinha(File);
               
         }
         arqEsc.fecharArquivo();
           
         /*if (arquivoOrigem.equals("sair")) {
         break;
         }
         */

        try {
            //Realiza a leitura do arquivo na variável in
            //BufferedReader in = new BufferedReader(new FileReader(File));
            //Cria um novo arquivo pegando o File concatenando com Copia_
            BufferedWriter out = new BufferedWriter(new FileWriter("Copia_" + nameFile));
            try {
                
                String linha = "";
                for (int i=0; i<file.size(); i++) {
                       linha=file.get(i);
                        out.append(linha+"\r\n");
                    
                }
                out.close();
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        return true;
}
}
