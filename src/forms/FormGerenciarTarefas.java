/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import beans.Tarefas;
import beans.Usuarios;
import dao.TarefasJpaController;
import emf.Emf;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class FormGerenciarTarefas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormGerenciarTarefas.class.getName());

    private Usuarios usr;
    private TarefasJpaController tarefaDAO; 
    
    public FormGerenciarTarefas() {
        initComponents();
    }
    
    public FormGerenciarTarefas(Usuarios usr) {
        initComponents();
        this.usr = usr; 
        this.tarefaDAO = new TarefasJpaController(Emf.getEmf());
        
        preenchertabela();
    }
    
    private void habilitarBtns(boolean salvar) {
        btnAlterar.setEnabled(salvar);
        btnNovo.setEnabled(!salvar);
        btnAlterar.setEnabled(!salvar);
        btnExcluir.setEnabled(!salvar);
    }
    
    private void limparCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        habilitarBtns(true);
    }
    
    private void preenchertabela(){
        DefaultTableModel tabela = (DefaultTableModel) tblTarefas.getModel();
        tabela.setNumRows(0);
        
        try {
            List<Tarefas> lista = this.tarefaDAO.ExibeTarefasByUsuario(usr.getIdUsuario());
            
            for(Tarefas t : lista){
                Object[] nvLinha = new Object[]{
                    t.getDescricao(),
                    t.getDificuldade(),
                    t.getRecompensa()
                };
                tabela.addRow(nvLinha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private int recompensa(String dificuldade){
        if(dificuldade.equals("FACIL")){
            return 15;
        }
        else if(dificuldade.equals("MEDIO")){
            return 30;
        }
        else if(dificuldade.equals("DIFICIL")){
            return 50;
        }
        return 0;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarefas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCriar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbDificuldade = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniTarefas = new javax.swing.JMenuItem();
        mniGerenciarTarefas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniLoja = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mniInventario = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mniPerfil = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciar Tarefas");

        tblTarefas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tarefas", "Dificuldade", "Recompensa "
            }
        ));
        jScrollPane1.setViewportView(tblTarefas);

        jLabel1.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jLabel1.setText("Titulo:");

        txtTitulo.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jLabel3.setText("Dificuldade:");

        btnAlterar.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        btnExcluir.setText("Excluir");

        btnCriar.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        btnCriar.setText("Salvar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Mongolian Baiti", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jLabel2.setText("ID:");

        cmbDificuldade.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N
        cmbDificuldade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACIL", "MEDIO", "DIFICIL" }));
        cmbDificuldade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDificuldadeActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Mongolian Baiti", 1, 36)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNovo.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("Tarefas");

        mniTarefas.setText("Tarefas");
        jMenu1.add(mniTarefas);

        mniGerenciarTarefas.setText("Gerenciar Tarefas");
        jMenu1.add(mniGerenciarTarefas);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Loja");

        mniLoja.setText("Loja");
        jMenu2.add(mniLoja);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Inventario");

        mniInventario.setText("Inventario");
        jMenu3.add(mniInventario);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Perfil");

        mniPerfil.setText("Perfil");
        jMenu4.add(mniPerfil);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCriar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel3))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbDificuldade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnCriar)
                    .addComponent(btnNovo)
                    .addComponent(btnAlterar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDificuldadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDificuldadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDificuldadeActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o campo!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String dificuldadeSelecionada = (String) cmbDificuldade.getSelectedItem();
        
        Tarefas t = new Tarefas();
        t.setDescricao(txtTitulo.getText());
        t.setDificuldade(dificuldadeSelecionada);
        t.setUsuarioId(usr);
        t.setRecompensa(recompensa(dificuldadeSelecionada));
        
        try {
            tarefaDAO.create(t);
            JOptionPane.showMessageDialog(this, "Tarefa cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            preenchertabela();
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCriarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int linhaSelecionada = tblTarefas.getSelectedRow();
        
        if(linhaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela");
        }
        else{
            String id = tblTarefas.getValueAt(linhaSelecionada,0).toString();
            
            try {
                Tarefas t = tarefaDAO.findTarefas(Integer.parseInt(id));
                
                txtId.setText(id);
                txtTitulo.setText(t.getDescricao());
                cmbDificuldade.setSelectedItem(t.getDificuldade());
                
                habilitarBtns(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        int id = Integer.parseInt(txtId.getText());
        String dificuldadeSelecionada = (String) cmbDificuldade.getSelectedItem();
        
        try {
            Tarefas t = tarefaDAO.findTarefas(id);
            t.setDescricao(txtTitulo.getText());
            t.setDificuldade(dificuldadeSelecionada);
            t.setRecompensa(recompensa(dificuldadeSelecionada));
            
            tarefaDAO.edit(t);
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!");
            preenchertabela();
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormGerenciarTarefas().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JComboBox<String> cmbDificuldade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mniGerenciarTarefas;
    private javax.swing.JMenuItem mniInventario;
    private javax.swing.JMenuItem mniLoja;
    private javax.swing.JMenuItem mniPerfil;
    private javax.swing.JMenuItem mniTarefas;
    private javax.swing.JTable tblTarefas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
