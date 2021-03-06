package br.com.lojagames.dao;

import br.com.lojagames.models.BackOffice;
import br.com.lojagames.models.Diretor;
import br.com.lojagames.models.GerenteVendas;
import br.com.lojagames.models.Rh;
import br.com.lojagames.models.Usuario;
import br.com.lojagames.models.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author victor
 */
public class UsuarioDAO {
    
    private DbConnectionDAO dbConnection = new DbConnectionDAO();
    
    public static Usuario pesquisaPorEmail(String email) {
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT u.email, u.senha, f.tipo FROM "
                    + "Usuario u INNER JOIN Funcionario f ON f.IdFuncionario = u.IdFuncionario WHERE email LIKE ?");
            comando.setString(1, email);
            ResultSet rs = comando.executeQuery();

            Usuario usuario = null;

            while (rs.next()) {
                if (rs.getString("tipo").equals("Rh")) {
                    usuario = new Rh();
                } else if (rs.getString("tipo").equals("BackOffice")) {
                    usuario = new BackOffice();
                } else if (rs.getString("tipo").equals("Diretor")) {
                    usuario = new Diretor();
                } else if (rs.getString("tipo").equals("Vendedor")){
                    usuario = new Vendedor();
                } else {
                    usuario = new GerenteVendas();
                }

    
                usuario.setEmail(rs.getString("Email"));
                usuario.setSenha(rs.getString("Senha"));
            }

            DbConnectionDAO.closeConnection(connection);
            return usuario;

        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
}
