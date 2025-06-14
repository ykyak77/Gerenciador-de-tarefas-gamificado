/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thelastoftask;

import beans.Usuarios;
import emf.Emf;
import dao.UsuariosJpaController;

public class Thelastoftask {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuarios  u = new Usuarios();
        u.setNome("oioio");
        u.setUsername("oioi");
        u.setEmail("xxxx");
        u.setSenha("555");
        
        UsuariosJpaController userDAO = new UsuariosJpaController(Emf.getEmf());
        
        //        try {
//            depDAO.create(dep);
//            System.out.println("Departamento salvo com sucesso");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        try {
            userDAO.create(u);
            System.out.println("Sucesso");
        } catch (Exception e) {
            System.out.println("Erro");
        }
    }
    
}
