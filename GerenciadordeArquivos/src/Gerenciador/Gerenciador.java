package Gerenciador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Gerenciador extends UnicastRemoteObject implements GerenciadorRemote {

    private int[] x = new int[40];

    public Gerenciador() throws RemoteException {
    }

    @Override
    public ArrayList<String> getArquivos() throws RemoteException {

        String[] x = null;
        ArrayList<String> arquivos = new ArrayList<String>();
        for (String file : findFile(".")) {
            arquivos.add(file);
        }
        return arquivos;
    }

    @Override
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

    @Override
    public boolean escreverArquivo(String nameFile,ArrayList<String> file) {
        /*
         ArquivoEscrever arqEsc= new ArquivoEscrever();
        
         while (true) {
            
         arqEsc = new ArquivoEscrever();
         arqEsc.setNomeArquivo("copia_"+nameFile);
         arqEsc.criarArquivo();
         while (!nameFile.equals("null")) {
                
         arqEsc.gravarLinha(nameFile);
               
         }
         arqEsc.fecharArquivo();
           
         /*if (arquivoOrigem.equals("sair")) {
         break;
         }
         */

        try {
            //Realiza a leitura do arquivo na variável in
            BufferedReader in = new BufferedReader(new FileReader(nameFile));
            //Cria um novo arquivo pegando o nameFile concatenando com Copia_
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
     //Metodo que busca o arquivo por extensão .txt
    public ArrayList<String> findFile(String local) {

        File dir = new File(local);
        ArrayList nameFile = new ArrayList();
        File[] files = dir.listFiles();
        System.out.println(files.length);
        for (int i = 0; i < files.length; ++i) {
            File pathname = files[i];
            String nm = pathname.getName();

            //Verificar se a extensão é .txt
            if (nm.endsWith(".txt")) {
                nameFile.add(pathname.getName());

            }
        }
        return nameFile;
    }
   //Método que realizar a contagem de arquivos .txt
    public int CountFile(String local) {

        File dir = new File(local);
        ArrayList nameFile = new ArrayList();
        File[] files = dir.listFiles();
        int totalFiles=0;
        System.out.println(files.length);
        for (int i = 0; i < files.length; ++i) {
            File pathname = files[i];
            String nm = pathname.getName();
           //Realizar a contagem de arquivos .txt
            if (nm.endsWith(".txt")) {
              totalFiles++;  
             
            }
        }
        return totalFiles;
    }

    @Override
    public boolean isAlive() throws RemoteException {
        return true;
    }

   

}