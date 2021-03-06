package br.com.lojagames.dao;

import br.com.lojagames.models.Filial;
import br.com.lojagames.models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pablo.santana
 */
public class ProdutoDAO implements IDao{

    public boolean salvar(Object object) {
        Produto produto = (Produto) object;
        Connection connection = null;
        boolean retorno = false;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("INSERT INTO Produto "
                    + "(Nome, TipoProduto, QntEstoque, ValorUnitario, IdFilial) "
                    + "VALUES (?, ?, ?, ?, ?);");
            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getTipo());
            comando.setDouble(3, produto.getQtdProduto());
            comando.setDouble(4, produto.getVlrUnitario());
            comando.setInt(5, produto.getFilial().getId());

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                return true;
            } else {
                retorno = false;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            retorno = false;
        } catch (ClassNotFoundException ex) {
            retorno = false;
        }

        DbConnectionDAO.closeConnection(connection);
        return retorno;
    }

    public ArrayList<Produto> pesquisarProduto(String nome) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM Produto WHERE Nome LIKE ?");
            comando.setString(1, "%" + nome + "%");
            ResultSet rs = comando.executeQuery();
            
            FuncionarioDAO fDao = new FuncionarioDAO();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("IdProduto"));
                produto.setNome(rs.getString("Nome"));
                produto.setTipoProduto(rs.getString("TipoProduto"));
                produto.setQtdProduto(rs.getDouble("QntEstoque"));
                produto.setVlrUnitario(rs.getDouble("ValorUnitario"));
                Filial filial = fDao.getFilialPorId(rs.getInt("IdFilial"));
                produto.setFilial(filial);

                produtos.add(produto);
            }

            DbConnectionDAO.closeConnection(connection);
            return produtos;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public ArrayList<Produto> pesquisarProdutos(int id) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM Produto WHERE IdFilial = ?; ");
             comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();
            FuncionarioDAO fDao = new FuncionarioDAO();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("IdProduto"));
                produto.setNome(rs.getString("Nome"));
                produto.setTipoProduto(rs.getString("TipoProduto"));
                produto.setQtdProduto(rs.getDouble("QntEstoque"));
                produto.setVlrUnitario(rs.getDouble("ValorUnitario"));
                Filial filial = fDao.getFilialPorId(rs.getInt("IdFilial"));
                produto.setFilial(filial);

                produtos.add(produto);
            }

            DbConnectionDAO.closeConnection(connection);
            return produtos;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public boolean excluir(int id) {
        Connection connection = null;
        boolean retorno = false;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("DELETE FROM Produto "
                    + "WHERE IdProduto = ?");
            comando.setInt(1, id);

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            System.out.println(ex);
            retorno = false;
        }

        DbConnectionDAO.closeConnection(connection);
        return retorno;

    }

    public Produto pesquisarPorId(int id) {

        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("SELECT * FROM Produto WHERE IdProduto = ?");
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();

            Produto produto = new Produto();
            FuncionarioDAO fDao = new FuncionarioDAO();

            while (rs.next()) {
                Filial filial = fDao.getFilialPorId(rs.getInt("IdFilial"));
                produto.setId(rs.getInt("IdProduto"));
                produto.setNome(rs.getString("Nome"));
                produto.setTipoProduto("TipoProduto");
                produto.setQtdProduto(rs.getDouble("QntEstoque"));
                produto.setVlrUnitario(rs.getDouble("ValorUnitario"));
                produto.setFilial(filial);
            }
            DbConnectionDAO.closeConnection(connection);
            return produto;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public boolean editar(Object object) {
        Produto produto = (Produto) object;
        Connection connection = null;
        boolean retorno = false;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement("UPDATE Produto "
                    + "SET Nome = ?, TipoProduto = ?, QntEstoque = ?, ValorUnitario = ?, "
                    + "IdFilial = ? WHERE IdProduto = ?");

            comando.setString(1, produto.getNome());
            comando.setString(2, produto.getTipo());
            comando.setDouble(3, produto.getQtdProduto());
            comando.setDouble(4, produto.getVlrUnitario());
            comando.setInt(5, produto.getFilial().getId());
            comando.setInt(6, produto.getId());

            int linhasAfetadas = comando.executeUpdate();

            if (linhasAfetadas > 0) {

                retorno = true;

            } else {
                retorno = false;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;

        } catch (SQLException ex) {
            System.out.println(ex);
            retorno = false;
        }

        DbConnectionDAO.closeConnection(connection);
        return retorno;

    }
    
    public ArrayList<Produto> combustivelPorFilial(int id) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        Connection connection = null;

        try {
            connection = DbConnectionDAO.openConnection();
            PreparedStatement comando = connection.prepareStatement(
                    "SELECT IdProduto, Nome, TipoProduto, QntEstoque, " 
                  + "ValorUnitario, IdFilial FROM Produto WHERE " 
                  + "TipoProduto LIKE 'Combustivel' AND IdFilial = ?");
            comando.setInt(1, id);
            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("IdProduto"));
                produto.setNome(rs.getString("Nome"));
                produto.setTipoProduto(rs.getString("TipoProduto"));
                produto.setQtdProduto(rs.getDouble("QntEstoque"));
                produto.setVlrUnitario(rs.getDouble("ValorUnitario"));

                produtos.add(produto);
            }

            DbConnectionDAO.closeConnection(connection);
            return produtos;

        } catch (ClassNotFoundException ex) {
            return null;

        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
