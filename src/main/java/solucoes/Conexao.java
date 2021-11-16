package solucoes;

import java.sql.Connection;
import java.sql.DriverManager;

	
	public class Conexao{
		private final String SERVIDOR = "localhost";
		private final String PORTA = "3306";
		private final String NOME_DB = "Produto";
		private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + NOME_DB;
		
		private final String USUARIO="root";
		private final String SENHA="lucc@002";
		
		private Connection conexao;
		
		public Conexao() throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			conexao.setAutoCommit(true);
		}
		
		public Connection getConexao() {
			return conexao;
		}
		
	

}
