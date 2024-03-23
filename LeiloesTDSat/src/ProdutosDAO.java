/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    public void cadastrarProduto(ProdutosDTO produto) {

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement("INSERT INTO produtos ( nome, valor, status) VALUES ( ?, ?, ?) ")) {
            prep.setString(1, produto.getNome());
            prep.setFloat(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "cadastro realizado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro no cadastro do produto");
            e.printStackTrace();
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try (Connection conn = new conectaDAO().connectDB(); Statement stm = conn.createStatement(); ResultSet resultset = stm.executeQuery("select * from produtos")) {
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return listagem;
    }

    public void venderProduto(Integer id){

        try (Connection conn = new conectaDAO().connectDB(); PreparedStatement prep = conn.prepareStatement("UPDATE produtos set status = 'Vendido' where id = ?")) {
            prep.setInt(1, id);
            
            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto atuializado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na atualização do status do produto");
            e.printStackTrace();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosendidos() {
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        try (Connection conn = new conectaDAO().connectDB(); Statement stm = conn.createStatement(); ResultSet resultset = stm.executeQuery("select * from produtos where status = 'Vendido'")) {
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return listagem;
    }

}
