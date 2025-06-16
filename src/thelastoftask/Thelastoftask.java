/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thelastoftask;

import beans.Tarefas;
import beans.Usuarios;
import emf.Emf;
import dao.UsuariosJpaController;
import dao.TarefasJpaController;

public class Thelastoftask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Usuarios  u = new Usuarios();
//        u.setNome("Josias");
//        u.setUsername("jojo");
//        u.setEmail("josias1233@gmail.com");
//        u.setSenha("1234");
//        
//      UsuariosJpaController userDAO = new UsuariosJpaController(Emf.getEmf());

        UsuariosJpaController userDAO = new UsuariosJpaController(Emf.getEmf());
        Usuarios u = null;

        try {
            // Supondo que o ID do usuário existente seja 1
            u = userDAO.findUsuarios(1);
            if (u == null) {
                System.out.println("Usuário não encontrado no banco!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
            return;
        }


        Tarefas d = new Tarefas();
        d.setUsuarioId(u);
        d.setDescricao("Arrumar a cama");
        d.setRecompensa(25);
        d.setDificuldade("FACIL");
        d.setConcluido(true);
        
        TarefasJpaController tarefaDAO = new TarefasJpaController(Emf.getEmf());

        try {
            tarefaDAO.create(d);
            System.out.println("Sucesso");
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
    
}
