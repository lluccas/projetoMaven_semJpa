package solucoes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.ProdutoDao;
import modelo.Produto;

public class Testar_Conexao {

	public static void main(String[] args) {
		testar();

		System.out.println("***********************");

		
		menu();
	}

	public static void testar() {
		try {
			Conexao c = new Conexao();

			c.getConexao();
			System.out.println("Banco Conectado");

		} catch (Exception e) {
			System.out.println("Erro!!!!!" + e.getMessage());

		}
	}

	public static void inserirDados() {
		Produto p = new Produto();

		p.setNome("Arroz1");
		p.setQuantidade(200);
		p.setPreco(20.99);

		String dataValidade = "2025-02-10";
		Date dataV = Date.valueOf(dataValidade);
		p.setDataValidade(dataV);

		p.setDataCadastro(new Date(System.currentTimeMillis()));

		ProdutoDao dao = new ProdutoDao();

		dao.inserir(p);

	}

	

	public static void status() {
		List<Produto> produtos = new ArrayList<>();

		ProdutoDao dao = new ProdutoDao();

		produtos = dao.listaDB();

		for (int i = 0; i < produtos.size(); i++) {

			System.out.println("**************************************************************************");
			System.out.println("ID do Produto: " + produtos.get(i).getId());
			System.out.println("Nome do Produto: " + produtos.get(i).getNome());
			System.out.println("Quantidade do Produto: " + produtos.get(i).getQuantidade());
			System.out.println("Preço do Produto: " + produtos.get(i).getPreco());
			System.out.println("Data de Validade do Produto: " + produtos.get(i).getDataValidade());
			System.out.println("Data do Cadastro do Produto: " + produtos.get(i).getDataCadastro());
			System.out.println("**************************************************************************");

		}

	}

	public static void menu() {
		int op, opp;
		System.out.println("*******************************************");
		Scanner s = new Scanner(System.in);

		do {
			System.out.println("MENU DIGITE A OPÇÃO QUE DESEJA ABAIXO ");
			System.out.println("|1 para Cadastrar Produto|" + "\n" + "|2 para Verificar lista de produtos|" + "\n"
					+ "|3 buscar por nome|" + "\n" + "|4 editar produto por ID|" + "\n" + "|5 para deletar ID|" + "\n" + "|6 para sair|");
			op = s.nextInt(); s.nextLine();
		
			if (op != 1 && op != 2 && op != 3 && op != 4 && op != 5 && op != 6) {
				System.out.println("Opção errada tente denovo!!!");
				System.out.println("|1 para Cadastrar Produto|" + "\n" + "|2 para Verificar lista de produtos|" + "\n"
						+ "|3 buscar por nome|" + "\n" + "|4 editar produto por ID|" + "\n" + "|5 para deletar ID|" + "\n" + "|6 para sair|");
				op = s.nextInt();  s.nextLine();
			}

			switch (op) {

			case 1:
				cadastrarProduto();
				break;

			case 2:
				listarProdutos();
				break;

			case 3:
				buscaNome();
				break;
			case 4:
				editar1();
				break;

			case 5:
				excluir();
				break;
				
			case 6:
				System.out.println("Saindo do sistema......");

			}
			System.out.println("Voltar ao menu digite 1 ou 2 para sair");
			opp = s.nextInt(); 
			if (opp == 2)
				System.out.println("Saindo do sitema.......");
		} while (opp == 1);

	}

	public static void cadastrarProduto() {
		Scanner leia = new Scanner(System.in);

		Produto p = new Produto();
		System.out.println("Digite a quantidade de produtos que deseja cadastrar");
		int qtd = leia.nextInt();
		leia.nextLine();
		for (int i = 0; i < qtd; i++) {
			System.out.println("Nome Produto: ");
			p.setNome(leia.nextLine());
			System.out.println("Quantidade Produto: ");
			p.setQuantidade(leia.nextInt());
			leia.nextLine();
			System.out.println("Preco Produto: ");
			p.setPreco(leia.nextDouble());
			System.out.println("data de validade produto: ");
			String dataValidade = leia.next();
			Date dataV = Date.valueOf(dataValidade);
			p.setDataValidade(dataV);

			p.setDataCadastro(new Date(System.currentTimeMillis()));

			ProdutoDao dao = new ProdutoDao();

			dao.inserir(p);
			
		}
		
	}

	public static void listarProdutos() {

		List<Produto> produtos = new ArrayList<>();

		ProdutoDao dao = new ProdutoDao();

		produtos = dao.listaDB();
		int cont = 0;
		for (int i = 0; i < produtos.size(); i++) {

			System.out.println("**************************************************************************");
			System.out.println("ID do Produto: " + produtos.get(i).getId());
			System.out.println("Nome do Produto: " + produtos.get(i).getNome());
			System.out.println("Quantidade do Produto: " + produtos.get(i).getQuantidade());
			System.out.println("Preço do Produto: " + produtos.get(i).getPreco());
			System.out.println("Data de Validade do Produto: " + produtos.get(i).getDataValidade());
			System.out.println("Data do Cadastro do Produto: " + produtos.get(i).getDataCadastro());
			System.out.println("**************************************************************************");
			cont++;

		}
		System.out.println("O total de produtos listados é: " + cont);

	}

	public static void buscaNome() {
		List<Produto> produtos = new ArrayList<>();

		ProdutoDao dao = new ProdutoDao();

		produtos = dao.consultaNome();
		int cont = 0;
		for (int i = 0; i < produtos.size(); i++) {

			System.out.println("**************************************************************************");
			System.out.println("ID do Produto: " + produtos.get(i).getId());
			System.out.println("Nome do Produto: " + produtos.get(i).getNome());
			System.out.println("Quantidade do Produto: " + produtos.get(i).getQuantidade());
			System.out.println("Preço do Produto: " + produtos.get(i).getPreco());
			System.out.println("Data de Validade do Produto: " + produtos.get(i).getDataValidade());
			System.out.println("Data do Cadastro do Produto: " + produtos.get(i).getDataCadastro());
			System.out.println("**************************************************************************");
			cont++;
		}
		System.out.println("Totatl de Produtos encontrados é: " + cont);
	}

	public static void editarID() {
		Produto p = new Produto();
		Scanner s = new Scanner(System.in);

		System.out.println("Digite o id que deseja editar: ");
		int id = s.nextInt();
		s.nextLine();

		System.out.println("Nome Produto: ");
		p.setNome(s.next());
		System.out.println("Quantidade Produto: ");
		p.setQuantidade(s.nextInt());
		s.nextLine();
		System.out.println("Preco Produto: ");
		p.setPreco(s.nextDouble());
		System.out.println("data de validade produto: ");
		String dataValidade = s.next();
		Date dataV = Date.valueOf(dataValidade);
		p.setDataValidade(dataV);

		p.setDataCadastro(new Date(System.currentTimeMillis()));

		ProdutoDao dao = new ProdutoDao();
		dao.editar(p, id);

	}
	
	public static void editar1() {
		Scanner s = new Scanner(System.in);

		List<Produto> produtos = new ArrayList<>();

		ProdutoDao dao = new ProdutoDao();

		produtos = dao.mostrarTodos();
		
		Produto p = new Produto();

		
		String opc;
		
		System.out.println("Digite o ID a ser atualizado: ");
		int id = s.nextInt(); s.nextLine();
		
		
		
		System.out.println("Deseja alterar o nome: " + produtos.get(id - 1).getNome()+ "  [s] ou [n]?");
		opc = s.nextLine();
		
		if(opc.equalsIgnoreCase("s")) {
			System.out.println("Qual o novo nome");
			p.setNome(s.nextLine());
		}else {
			p.setNome(produtos.get(id-1).getNome());
		}
		
		System.out.println("Deseja alterar a quantidade: " + produtos.get(id - 1).getQuantidade()+ "  [s] ou [n]?");
		opc = s.next();
		if(opc.equalsIgnoreCase("s")) {
			System.out.println("Qual a nova quantidade: ");
			p.setQuantidade(s.nextInt()); 
		}else {
			p.setQuantidade(produtos.get(id-1).getQuantidade());
		}
		
		System.out.println("Deseja alterar o preço: " + produtos.get(id - 1).getPreco()+ "  [s] ou [n]?");
		opc = s.next();
		if(opc.equalsIgnoreCase("s")) {
			System.out.println("Qual o novo preço: ");
			p.setPreco(s.nextDouble());
		}else {
			p.setPreco(produtos.get(id-1).getPreco());
		}
		
		System.out.println("Deseja alterar a data de validade: " + produtos.get(id - 1).getDataValidade()+ "  [s] ou [n]?");
		opc = s.next();
		if(opc.equalsIgnoreCase("s")) {
			String dataValidade = s.next();
			Date dataV = Date.valueOf(dataValidade);
			p.setDataValidade(dataV);
		}else {
			p.setDataValidade(produtos.get(id-1).getDataValidade());
		}
		
		p.setDataCadastro(produtos.get(id-1).getDataCadastro());
		
		ProdutoDao editdao = new ProdutoDao();
		editdao.editar(p, id);
	}
	
	public static  void excluir(){
		Scanner s = new Scanner(System.in);
		Produto p = new Produto();

		ProdutoDao dao = new ProdutoDao();
		System.out.println("Informe o id da linha a ser excluido: ");
		int id = s.nextInt();s.nextLine();
		dao.excluir(id);
	}
	
}


