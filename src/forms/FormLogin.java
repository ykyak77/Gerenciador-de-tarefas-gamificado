/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import javax.swing.JOptionPane;
import beans.Usuarios;
import javax.persistence.EntityManager;
import emf.Emf;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.NoResultException;

public class FormLogin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormLogin.class.getName());

    /**
     * Creates new form FormLogin
     */
    public FormLogin() {
        initComponents();
    }

    public Usuarios buscarPorEmail(String email) {
        EntityManager em = null;
        try {
            em = emf.Emf.getEmf().createEntityManager();
            return em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email", Usuarios.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
        private String gerarHashSHA256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtEmailLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BtnLogarLogin = new javax.swing.JButton();
        BtnCriarConta = new javax.swing.JButton();
        TxtSenhaLogin = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe Print", 3, 36)); // NOI18N
        jLabel1.setText("Login");

        jLabel3.setFont(new java.awt.Font("Segoe Print", 3, 18)); // NOI18N
        jLabel3.setText("Senha:");

        TxtEmailLogin.setFont(new java.awt.Font("Segoe Script", 3, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe Print", 3, 18)); // NOI18N
        jLabel4.setText("E-mail:");

        BtnLogarLogin.setBackground(new java.awt.Color(204, 204, 204));
        BtnLogarLogin.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        BtnLogarLogin.setText("Logar");
        BtnLogarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLogarLoginActionPerformed(evt);
            }
        });

        BtnCriarConta.setBackground(new java.awt.Color(204, 255, 204));
        BtnCriarConta.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        BtnCriarConta.setText("Criar conta");
        BtnCriarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCriarContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BtnLogarLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnCriarConta))
                            .addComponent(TxtEmailLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(TxtSenhaLogin))
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TxtEmailLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtSenhaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnLogarLogin)
                    .addComponent(BtnCriarConta))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnLogarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLogarLoginActionPerformed
        String email = TxtEmailLogin.getText();
        char[] senhaChar = TxtSenhaLogin.getPassword();
        String senha = new String(senhaChar);
        String senhaHash = gerarHashSHA256(senha);
        

        Usuarios usuario = buscarPorEmail(email);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            return;
        }

        if (usuario.getSenha().equals(senhaHash)) {           
            FormTarefas tarefas = new FormTarefas(usuario);
            tarefas.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta.");
        }
    }//GEN-LAST:event_BtnLogarLoginActionPerformed

    private void BtnCriarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCriarContaActionPerformed
        new FormCadastro().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnCriarContaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FormLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCriarConta;
    private javax.swing.JButton BtnLogarLogin;
    private javax.swing.JTextField TxtEmailLogin;
    private javax.swing.JPasswordField TxtSenhaLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
