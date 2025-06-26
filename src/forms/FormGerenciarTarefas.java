/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import beans.Tarefas;
import beans.Usuarios;
import dao.TarefasJpaController;
import dao.UsuariosJpaController;
import emf.Emf;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class FormGerenciarTarefas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormGerenciarTarefas.class.getName());

    private Usuarios usr;
    private TarefasJpaController tarefaDAO; 
    private UsuariosJpaController usuarioDAO;
    
    public FormGerenciarTarefas() {
        initComponents();
    }
    
    public FormGerenciarTarefas(Usuarios usr) {
        initComponents();
        habilitarBtns(true);
        
        this.usr = usr; 
        this.tarefaDAO = new TarefasJpaController(Emf.getEmf());
        this.usuarioDAO = new UsuariosJpaController(Emf.getEmf());
        
        preenchertabela();
    }
    
    private void habilitarBtns(boolean salvar) {
        btnCriar.setEnabled(salvar);
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
                    t.getIdTarefas(),
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
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciar Tarefas");

        tblTarefas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tblTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tarefas", "Dificuldade", "Recompensa "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCriar.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        btnCriar.setText("Salvar");
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N

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

        btnVoltar.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N
        btnVoltar.setText("VOLTAR");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVoltar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
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
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(btnNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCriar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVoltar)
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
        setLocationRelativeTo(null);
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
        t.setConcluido(false);
        
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
            JOptionPane.showMessageDialog(this, "Selecione uma linha da tabela", "Aviso", JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(this, e.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Atualizado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            preenchertabela();
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int id = Integer.parseInt(txtId.getText());
        
        int opcao = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir " + txtTitulo.getText() +"?", "Aviso", JOptionPane.WARNING_MESSAGE);
        
        if(opcao != 1){
            try {
                tarefaDAO.destroy(id);
                JOptionPane.showMessageDialog(this, "Excluido com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                preenchertabela();
                limparCampos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        Usuarios user = usuarioDAO.findUsuarios(usr.getIdUsuario());
        
        FormTarefas tarefas = new FormTarefas(user);
        tarefas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarActionPerformed

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
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmbDificuldade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTarefas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
