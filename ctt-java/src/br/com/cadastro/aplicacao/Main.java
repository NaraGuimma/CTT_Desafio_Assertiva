package br.com.cadastro.aplicacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import br.com.cadastro.dao.CadastroDAO;
import br.com.cadastro.model.Cadastro;

public class Main {

	public static void main(String[] args) {
		
		
		Cadastro cadastro = new Cadastro();
		CadastroDAO cadastroDao = new CadastroDAO();
		Scanner sc = new Scanner(System.in);
		
		int rodarAgain = 1;
		
		while (rodarAgain == 1) {
			System.out.println("Bem vindo(a) ao Menu do CRUD\n");
			
			System.out.println("##############################################################");
			
			System.out.println("       1 - Inserir                       2 - Atualizar");
			System.out.println("       3 - Consultar                     4 - Deletar");
			
			
			
			System.out.println("##############################################################");
			
			int opcaoMenu = sc.nextInt();
			
			
			switch (opcaoMenu){
	        case 1:
	        	
	        	System.out.println("\nNome Completo:");
	            cadastro.setNome(sc.next());
	        	sc.nextLine();
	        	
	            System.out.println("\nE-mail:");
	            cadastro.setEmail(sc.next());
	            sc.nextLine();
	            
	            System.out.println("\nSenha:");
	            cadastro.setSenha(sc.next());
	            sc.nextLine();
	            
	            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	            LocalDateTime now = LocalDateTime.now();
	            System.out.println(dtf.format(now));
	            
	        	cadastro.setData_Cadastro(dtf.format(now));
	        	
	        	
	        	if (cadastro.getNome() == "" || cadastro.getEmail() == "" || cadastro.getSenha() == "") {
	        		System.out.println("Campos vazios, favor preenche-los corretamente");
	        	} else {
	        		cadastroDao.save(cadastro);
	        	}
				
	        	
	        	break;
	          
	        case 2:
	        	
	        	String coluna;
	        	
	        	System.out.println("\nQual registro vocë deseja alterar? Digite o id do mesmo\n ");
	        	int idToUpdate = sc.nextInt();
	        	
	        	System.out.println("\nQual campo você deseja alterar? \n1- Nome \n2 - E-mail \n3 - Senha\n ");
	        	int campo = sc.nextInt();
	        	
	        	System.out.println("Valor do novo campo " + campo);
	        	String valor = sc.next();
	        	
	        	if (campo == 1) {
	        		coluna = "Nome";
	        	} else if (campo ==2) {
	        		coluna = "Email";
	        	} else {
	        		coluna = "Senha";
	        	}
	        	
	        	
	        	cadastroDao.update(coluna, valor, idToUpdate);
	        	break;
	          
	          
	        case 3:

	        	
	        	
	        	for(Cadastro c: cadastroDao.getCadastros()) {
	        		
	        		
					System.out.println("Nome: "+c.getNome());
					System.out.println("Id: "+c.getId());
					System.out.println("E-mail: "+c.getEmail());
					System.out.println("Senha: "+c.getSenha());
					System.out.println("Data de Cadastro: "+c.getData_Cadastro());
					
					System.out.println("\n#############################\n");
					
					
				}
		        break;
		          
	        case 4:
	        	cadastroDao.delete();
	        	break;
	    
	        default:
	        	System.out.println("Número inválido!!!\n");
	      }
			
			
			
			System.out.println("\nDeseja executar novamente? 1 - Sim");
			rodarAgain = sc.nextInt();
			
		}
		
		System.out.println("\nObrigada pela sua visita. Até a próxima!");
		
		
		sc.close();

	}

}
