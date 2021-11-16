package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Produto;
import solucoes.Conexao;

public class ProdutoDao {

	public void inserir(Produto p) {
		try {
			Conexao conexao = new Conexao();
			String sql = "INSERT INTO produto(nome,quantidade,preco,dataValidade,dataCadastro) VALUES(?,?,?,?,?) ";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQuantidade());
			ps.setDouble(3, p.getPreco());
			ps.setDate(4, (Date) p.getDataValidade());
			ps.setDate(5, (Date) p.getDataCadastro());

			ps.execute();

			System.out.println("Produto cadastrado");

		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}

	public List<Produto> listaDB() {
		List<Produto> produtos = new ArrayList<>();
		try {
			Conexao conexao = new Conexao();
			String sql = "SELECT * FROM produto";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setNome(rs.getString("nome"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setDataValidade(rs.getDate("dataValidade"));
				prod.setDataCadastro(rs.getDate("dataCadastro"));

				produtos.add(prod);

			}

		} catch (Exception e) {
			System.out.println("Erro ao lista: " + e.getMessage());
		}

		return produtos;

	}

	public List<Produto> consultaNome() {
		Scanner sc = new Scanner(System.in);

		List<Produto> produtos = new ArrayList<>();
		try {

			Conexao conexao = new Conexao();
			String nome;
			System.out.println("Qual o nome: ");
			nome = sc.nextLine();
			String sql = "SELECT * FROM produto Where nome LIKE '" + nome + "%'";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setNome(rs.getString("nome"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setDataValidade(rs.getDate("dataValidade"));
				prod.setDataCadastro(rs.getDate("dataCadastro"));

				produtos.add(prod);

			}

		} catch (Exception e) {
			System.out.println("Erro não existi: " + e.getMessage());
		}

		return produtos;

	}

	public void editar(Produto p, int id) {

		String sql = "UPDATE produto SET  nome = ?, quantidade = ?, preco = ?, dataValidade = ?, dataCadastro =? WHERE id =? ";

		try {

			Conexao conexao = new Conexao();

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQuantidade());
			ps.setDouble(3, p.getPreco());
			ps.setDate(4, (Date) p.getDataValidade());
			ps.setDate(5, (Date) p.getDataCadastro());
			ps.setInt(6, id);

			ps.execute();

			System.out.println("Produto com ID:" + id + " - Atualizado");

		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}

	public List<Produto> mostrarTodos() {

		List<Produto> produtos = new ArrayList<>();
		try {

			Conexao conexao = new Conexao();
			String sql = "SELECT * FROM produto";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto prod = new Produto();

				prod.setId(rs.getInt("id"));
				prod.setNome(rs.getString("nome"));
				prod.setQuantidade(rs.getInt("quantidade"));
				prod.setPreco(rs.getDouble("preco"));
				prod.setDataValidade(rs.getDate("dataValidade"));
				prod.setDataCadastro(rs.getDate("dataCadastro"));

				produtos.add(prod);

			}

		} catch (Exception e) {
			System.out.println("Erro não existi: " + e.getMessage());
		}

		return produtos;

	}

	public static void excluir(int id) {
		try {

			Conexao conexao = new Conexao();
			String sql = "DELETE FROM produto WHERE id =? ;";

			PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
			ps.setInt(1, id);

			ps.execute();
			System.out.println("Id excluido.....");

		} catch (Exception e) {
			System.out.println("Erro não existi: " + e.getMessage());
		}

	}

}