/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerenciador;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import sun.awt.windows.ThemeReader;

/**
 *
 * @author User
 */
public class JBuscaArquivos extends javax.swing.JFrame {

    String endSrv1;
    String endSrv2;
    String endSrv3;
    Boolean flagSrv1;
    Boolean flagSrv2;
    Boolean flagSrv3;
    //Mantem a tabela de referência da origem do arquivo 
    ArrayList<String> tablearqorig = new ArrayList();

    public JBuscaArquivos() throws NotBoundException, MalformedURLException, RemoteException {
        initComponents();
        setLocationRelativeTo(null);
        LoadConfiguracao();
        LoadLocal();
        testeLocalHost();
        
    }

    //Carrega a table com o nome dos arquivos dos nós
    private void LoadServers() {
        try {
            if (flagSrv1 == true && flagSrv2 == false && flagSrv3 == false) {
                GerenciadorRemote arqsrv1;

                arqsrv1 = (GerenciadorRemote) Naming.lookup(endSrv1);

                DefaultTableModel dtArquivos = (DefaultTableModel) jTservidor.getModel();
                dtArquivos.setNumRows(0);
                tablearqorig.clear();
                ArrayList<String> dados = new ArrayList();
                // Junta tods os arquivos em uma única lista de exibição
                for (String temp1 : arqsrv1.getArquivos()) {
                    //Alimenta a tabela com a lista de arquivos
                    dados.add(temp1);

                }

                for (int i = 0; i < dados.size(); i++) {

                    System.out.println(dados.get(i));
                    dtArquivos.addRow(new String[]{dados.get(i)});
                   //Adiciona "cabeçalho ao nome do arquivo para identificar a origem"
                    tablearqorig.add("srv1_" + dados.get(i));
                }

           return;

            } else if (flagSrv1 == true && flagSrv2 == true && flagSrv3 == false) {
                GerenciadorRemote arqsrv1 = (GerenciadorRemote) Naming.lookup(endSrv1);
                System.out.println(endSrv2);
                GerenciadorRemote arqsrv2 = (GerenciadorRemote) Naming.lookup(endSrv2);
                DefaultTableModel dtArquivos = (DefaultTableModel) jTservidor.getModel();
                dtArquivos.setNumRows(0);
                tablearqorig.clear();
                ArrayList<String> dados = new ArrayList();
                // Junta tods os arquivos em uma única lista de exibição
                for (String temp1 : arqsrv1.getArquivos()) {
                    dados.add(temp1);
                    //Adiciona "cabeçalho ao nome do arquivo para identificar a origem"
                    tablearqorig.add("srv1_" + temp1);
                }
                for (String temp2 : arqsrv2.getArquivos()) {
                    dados.add(temp2);
                    //Adiciona "cabeçalho ao nome do arquivo para identificar a origem"
                    tablearqorig.add("Srv2_" + temp2);
                }

                //Alimenta a tabela com a lista de arquivos
                for (int i = 0; i < dados.size(); i++) {

                    System.out.println(dados.get(i));
                    dtArquivos.addRow(new String[]{dados.get(i)});
                }

                return;

            } else if (flagSrv1 == true && flagSrv2 == true && flagSrv3 == true) {

                GerenciadorRemote arqsrv1 = (GerenciadorRemote) Naming.lookup(endSrv1);
                GerenciadorRemote arqsrv2 = (GerenciadorRemote) Naming.lookup(endSrv2);
                GerenciadorRemote arqsrv3 = (GerenciadorRemote) Naming.lookup(endSrv3);
                DefaultTableModel dtArquivos = (DefaultTableModel) jTservidor.getModel();
                dtArquivos.setNumRows(0);
                tablearqorig.clear();
                ArrayList<String> dados = new ArrayList();
                // Junta tods os arquivos em uma única lista de exibição
                for (String temp1 : arqsrv1.getArquivos()) {
                    dados.add(temp1);
                    //Adiciona "cabeçalho ao nome do arquivo para identificar a origem"
                    tablearqorig.add("srv1_" + temp1);

                }
                for (String temp2 : arqsrv2.getArquivos()) {
                    dados.add(temp2);
                    //Adiciona "cabeçalho ao nome do arquivo para identificar a origem"
                    tablearqorig.add("Srv2_" + temp2);

                }
                for (String temp3 : arqsrv3.getArquivos()) {
                    dados.add(temp3);
                    //Adiciona "cabeçalho ao nome do arquivo para identificar a origem"
                    tablearqorig.add("Srv3_" + temp3);

                }

                //Alimenta a tabela com a lista de arquivos
                for (int i = 0; i < dados.size(); i++) {

                    System.out.println(dados.get(i));
                    dtArquivos.addRow(new String[]{dados.get(i)});
                }
               return;

            }
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Código 1: Erro descohecido \n" + ex.getMessage());

            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Código 2: URL Mal formada\n " + ex.getMessage());
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
            return;

        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(rootPane, "Código 3: Host(s) configurado(s) parace(m) estar offline \n" + ex.getMessage());
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        catch(ClassCastException ex){
          JOptionPane.showMessageDialog(rootPane, "Código 4: URL Mal formada\n" + ex.getMessage());
           return;
        }
        //Se chegou até aqui porque nada foi configurado
        JOptionPane.showMessageDialog(rootPane, "Nada foi configurado");
    }

    private void LoadLocal() {
        File dir = new File(".");
        ArrayList<String> nameFile = new ArrayList();
        File[] files = dir.listFiles();
        System.out.println(files.length);
        for (int i = 0; i < files.length; ++i) {
            File pathname = files[i];
            String nm = pathname.getName();

            System.out.println(nm);
            //Verificar se a extensão é .txt
            if (nm.endsWith(".txt")) {
                nameFile.add(pathname.getName());

            }


        }
        DefaultTableModel dtArquivos = (DefaultTableModel) jTLocal.getModel();
        dtArquivos.setNumRows(0);
        ArrayList<String> dados = new ArrayList();
        // Junta tods os arquivos em uma única lista de exibição
        for (int i = 0; i < nameFile.size(); i++) {

            System.out.println(nameFile.get(i));
            dtArquivos.addRow(new String[]{nameFile.get(i)});
        }

    }

    //Carrega configuração para abertura inicial
    private void LoadConfiguracao() {
        jRadioConfig.setSelected(false);
        jRadiosrv1.setSelected(true);
        jRadioSrv2.setSelected(false);
        jRadioSrv3.setSelected(false);
        jTextSrv1.setText("//localhost/Arquivo");
        jTextSrv1.setEnabled(false);
        jTextSrv2.setEnabled(false);
        jTextSrv3.setEnabled(false);
        jRadiosrv1.setEnabled(false);
        jRadioSrv2.setEnabled(false);
        jRadioSrv3.setEnabled(false);
        endSrv1 = jTextSrv1.getText();
        endSrv2 = jTextSrv2.getText();
        endSrv3 = jTextSrv3.getText();
        flagSrv1 = jRadiosrv1.isSelected();
        flagSrv2 = jRadioSrv2.isSelected();
        flagSrv3 = jRadioSrv3.isSelected();

    }

    private boolean testeLocalHost() {
        try {
            

                GerenciadorRemote arqsrv1 = (GerenciadorRemote) Naming.lookup(endSrv1);
                if (arqsrv1.isAlive()) {
                    LoadServers();
                    return true;
                    
                } 
           
            
        } catch (NotBoundException ex) {
            Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(rootPane, "URL Mal formada");
            Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            flagSrv1=false; jRadiosrv1.setSelected(false);
            JOptionPane.showMessageDialog(rootPane, "O Localhost é o padrão, mas não foi iniciado, ou esta inacessivel."
                    + "     \n A aplicação será aberta.\n Configure o(s) nó(s).");
            

        }
        catch(ClassCastException ex){
          JOptionPane.showMessageDialog(rootPane, "URL Mal formada\n" + ex.getMessage());
        
        }

        return true;
    }

    private String LoadSrvArq(int indice) {

        //for (String nameFile : tablearqorig) {

        if (tablearqorig.get(indice).startsWith("srv1_")) {
            return endSrv1;
        }
        if (tablearqorig.get(indice).startsWith("Srv2_")) {
            return endSrv2;
        }
        if (tablearqorig.get(indice).startsWith("Srv3_")) {
            return endSrv3;
        }

        //}
        return "";
    }
    //Verifica qual nó pode ser enviado, é eleito o que tive a menor quantidade de arquivos

    public String selecionaNoEnvio() {
        try {
            if (flagSrv1 == true && flagSrv2 == false && flagSrv3 == false) {
                return endSrv1;
            } else if (flagSrv1 == true && flagSrv2 == true && flagSrv3 == false) {
                GerenciadorRemote arqsrv1;

                arqsrv1 = (GerenciadorRemote) Naming.lookup(endSrv1);

                GerenciadorRemote arqsrv2 = (GerenciadorRemote) Naming.lookup(endSrv2);
                if (arqsrv1.CountFile(".") < arqsrv2.CountFile(".")) {
                    return endSrv1;
                } else {
                    return endSrv2;
                }


            } else if (flagSrv1 == true && flagSrv2 == true && flagSrv3 == true) {

                GerenciadorRemote arqsrv1 = (GerenciadorRemote) Naming.lookup(endSrv1);
                GerenciadorRemote arqsrv2 = (GerenciadorRemote) Naming.lookup(endSrv2);
                GerenciadorRemote arqsrv3 = (GerenciadorRemote) Naming.lookup(endSrv3);
                if (arqsrv1.CountFile(".") < arqsrv2.CountFile(".") && arqsrv1.CountFile(".") < arqsrv3.CountFile(".")) {
                    return endSrv1;
                } else if (arqsrv1.CountFile(".") > arqsrv2.CountFile(".") && arqsrv2.CountFile(".") < arqsrv3.CountFile(".")) {
                    return endSrv2;
                } else {
                    return endSrv3;
                }

            }
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro descohecdo" + ex.getMessage());

            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(rootPane, "URL Mal formada" + ex.getMessage());
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);

        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(rootPane, "Host(s) configurado(s) parace(parecem) estar offline \n" + ex.getMessage());
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassCastException ex){
          JOptionPane.showMessageDialog(rootPane, "URL Mal formada\n" + ex.getMessage());
        
        }
        return "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTLocal = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTservidor = new javax.swing.JTable();
        jButtonEnviar = new javax.swing.JButton();
        jButtondownload = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioConfig = new javax.swing.JRadioButton();
        jRadiosrv1 = new javax.swing.JRadioButton();
        jRadioSrv2 = new javax.swing.JRadioButton();
        jRadioSrv3 = new javax.swing.JRadioButton();
        jTextSrv1 = new javax.swing.JTextField();
        jTextSrv2 = new javax.swing.JTextField();
        jTextSrv3 = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jTLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Arquivos local"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTLocal);
        jTLocal.getColumnModel().getColumn(0).setResizable(false);

        jTservidor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Arquivos Remotos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTservidor);
        jTservidor.getColumnModel().getColumn(0).setResizable(false);

        jButtonEnviar.setText("<<<");
        jButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarActionPerformed(evt);
            }
        });

        jButtondownload.setText(">>>");
        jButtondownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondownloadActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, null));

        jRadioConfig.setText("Configuração");
        jRadioConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioConfigActionPerformed(evt);
            }
        });

        jRadiosrv1.setText("Servidor 1");

        jRadioSrv2.setText("Servidor 2");
        jRadioSrv2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioSrv2ActionPerformed(evt);
            }
        });

        jRadioSrv3.setText("Servidor 3");
        jRadioSrv3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioSrv3ActionPerformed(evt);
            }
        });

        jButtonSave.setText("Atualizar");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioConfig)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jRadiosrv1)
                        .addGap(76, 76, 76)
                        .addComponent(jRadioSrv2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jTextSrv1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jTextSrv2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioSrv3)
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextSrv3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadiosrv1)
                    .addComponent(jRadioSrv2)
                    .addComponent(jRadioSrv3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextSrv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextSrv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextSrv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButtonSave)
                .addContainerGap())
        );

        jLabel2.setText("Enviar");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Download");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButtonEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtondownload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEnviar)
                        .addGap(47, 47, 47)
                        .addComponent(jButtondownload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Enviar arquivos para um nó
    private void jButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarActionPerformed
        try {
            String nameFile = "Nada Selecionado";
            if (jTLocal.getSelectedRow() != -1) {
                nameFile = (String) jTLocal.getValueAt(jTLocal.getSelectedRow(), 0);
                ArquivoLer enviar = new ArquivoLer();
                //Realizar o sorteio para saber para qual nó sera enviado
                GerenciadorRemote arquivos = (GerenciadorRemote) Naming.lookup(selecionaNoEnvio());
                //Realiza a leitura do arquivo local e enviar para o no
                arquivos.escreverArquivo(nameFile, enviar.lerArquivo(nameFile));
                DefaultTableModel dtArquivos = (DefaultTableModel) jTLocal.getModel();
                
                
                JOptionPane.showMessageDialog(rootPane, nameFile + " Tranferido com Sucesso");
            } else {
                JOptionPane.showMessageDialog(rootPane, nameFile);
            }


        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro descohecdo" + ex.getMessage());
            return;
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(rootPane, "URL Mal formada");
            return;
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);

        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(rootPane, "Host(s) configurado(s) parace(parecem) estar offline \n" + ex.getMessage());
            return;
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassCastException ex){
          JOptionPane.showMessageDialog(rootPane, "URL Mal formada\n" + ex.getMessage());
        
        }
        //Se chegou até aqui porque ocorreu tudo bem 
        LoadServers();

    }//GEN-LAST:event_jButtonEnviarActionPerformed

    private void jButtondownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondownloadActionPerformed
        try {
            String nameFile = "Nada Selecionado";
            if (jTservidor.getSelectedRow() != -1) {
                nameFile = (String) jTservidor.getValueAt(jTservidor.getSelectedRow(), 0);

                //Consulta a tabela para saber de quem é o arquivo, e cria uma instância remota
                GerenciadorRemote arquivos = (GerenciadorRemote) Naming.lookup(LoadSrvArq(jTservidor.getSelectedRow()));
                //Pega o arquivo no sevidor e copia na maquina local
                ArquivoEscrever copia = new ArquivoEscrever();
                copia.escreveArquivo(nameFile, arquivos.lerArquivo(nameFile));
                DefaultTableModel dtArquivos = (DefaultTableModel) jTLocal.getModel();
                dtArquivos.addRow(new String[]{nameFile});
                
                JOptionPane.showMessageDialog(rootPane, nameFile + " Tranferido com Sucesso");
            } else {
                JOptionPane.showMessageDialog(rootPane, nameFile);
            }


        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Erro descohecdo" + ex.getMessage());
            return;
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(rootPane, "URL Mal formada");
            return;
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);

        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(rootPane, "Host(s) configurado(s) parace(parecem) estar offline \n" + ex.getMessage());
            return;
            //Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassCastException ex){
          JOptionPane.showMessageDialog(rootPane, "URL Mal formada\n" + ex.getMessage());
        
        }
      //Se chegou até aqui porque ocorreu tudo bem  
      LoadLocal();
    }//GEN-LAST:event_jButtondownloadActionPerformed

    private void jRadioConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioConfigActionPerformed
        jRadioConfig.setSelected(true);
        jTextSrv1.setEnabled(true);
        jRadiosrv1.setEnabled(true);
        jRadioSrv2.setEnabled(true);
        jRadioSrv3.setEnabled(true);
    }//GEN-LAST:event_jRadioConfigActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        jRadioConfig.setSelected(false);
        jTextSrv1.setEnabled(false);
        jTextSrv2.setEnabled(false);
        jTextSrv3.setEnabled(false);
        jRadiosrv1.setEnabled(false);
        jRadioSrv2.setEnabled(false);
        jRadioSrv3.setEnabled(false);
        endSrv1 = jTextSrv1.getText();
        endSrv2 = jTextSrv2.getText();
        endSrv3 = jTextSrv3.getText();
        flagSrv1 = jRadiosrv1.isSelected();
        flagSrv2 = jRadioSrv2.isSelected();
        flagSrv3 = jRadioSrv3.isSelected();
        LoadServers();

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jRadioSrv2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioSrv2ActionPerformed
        jRadiosrv1.setEnabled(false);
        jRadiosrv1.setSelected(true);
        jTextSrv2.setEnabled(true);


    }//GEN-LAST:event_jRadioSrv2ActionPerformed

    private void jRadioSrv3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioSrv3ActionPerformed
        jRadiosrv1.setEnabled(false);
        jRadiosrv1.setSelected(true);
        jRadioSrv2.setEnabled(false);
        jRadioSrv2.setSelected(true);
        //jRadioSrv3.setSelected(true);
        jTextSrv2.setEnabled(true);
        jTextSrv3.setEnabled(true);
    }//GEN-LAST:event_jRadioSrv3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JBuscaArquivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JBuscaArquivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JBuscaArquivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JBuscaArquivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JBuscaArquivos().setVisible(true);
                } catch (NotBoundException ex) {
                    Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RemoteException ex) {
                    Logger.getLogger(JBuscaArquivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviar;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtondownload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioConfig;
    private javax.swing.JRadioButton jRadioSrv2;
    private javax.swing.JRadioButton jRadioSrv3;
    private javax.swing.JRadioButton jRadiosrv1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTLocal;
    private javax.swing.JTextField jTextSrv1;
    private javax.swing.JTextField jTextSrv2;
    private javax.swing.JTextField jTextSrv3;
    private javax.swing.JTable jTservidor;
    // End of variables declaration//GEN-END:variables
}
