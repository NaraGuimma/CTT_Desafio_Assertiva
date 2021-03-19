package br.com.cadastro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import br.com.cadastro.model.Cadastro;
import br.com.cadatro.factory.ConnectionFactory;

public class CadastroDAO {
	
	String valida = "OK";
	
	public void save(Cadastro cadastro) {
		String sql = "INSERT INTO table_desafio(Nome, Email, Senha, Data_Cadastro) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, cadastro.getNome());
			pstm.setString(2, cadastro.getEmail());
			pstm.setString(3, cadastro.getSenha());
			pstm.setString(4, cadastro.getData_Cadastro());
			
			
			
			pstm.execute();
			
			System.out.println("\nRegistro salvo com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!= null) {
					conn.close();
				}
			} catch (Exception e) {
				valida = e.getMessage(); 
				e.printStackTrace();
			}
		}
		
	}

	
	
	
	public List<Cadastro> getCadastros(){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nQual nome que deseja buscar ");
    	String NomeBusca = sc.next();
		
		String sql = "SELECT * FROM table_desafio WHERE Nome LIKE '%" + NomeBusca + "%';";
		
		System.out.println("\n#######################   Resultado da sua busca   ###################\n");
		
		List<Cadastro> cadastros = new ArrayList<Cadastro>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		
		try {
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			
			while (rset.next()) {
				Cadastro cadastro = new Cadastro();
				
				cadastro.setId(rset.getInt("id"));
				
				cadastro.setNome(rset.getString("Nome"));
				
				cadastro.setEmail(rset.getString("Email"));
				
				cadastro.setSenha(rset.getString("Senha"));
				
				cadastro.setData_Cadastro(rset.getString("Data_Cadastro"));
				
				cadastros.add(cadastro);
			}
		}catch (Exception e) {
				valida = e.getMessage(); 
				e.printStackTrace();
		}finally {
			try {
				if (rset!=null) {
					rset.close();
				}
				
				if(pstm!=null) {
					pstm.close();
				}
				
				if (conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		
		
		}

		return cadastros;
		
		
		
		
		
	}


	public void delete() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nQual registro vocë deseja excluir? Digite o id do mesmo ");
    	int idToDelete = sc.nextInt();
		
		
		String sql = "DELETE FROM table_desafio WHERE id = " + idToDelete + ";";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			int rs = pstm.executeUpdate();
			

			if (rs != 0) {
				System.out.println("\nRegistro excluído com sucesso!");
            } else {
            	System.out.println("\nRegistro não existente!");
            }
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!= null) {
					conn.close();
				}
			} catch (Exception e) {
				valida = e.getMessage(); 
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
	
	public void update(String coluna, String valor, int id) {
		
    	
    	
		String sql = "UPDATE table_desafio SET " + coluna + " = '" + valor + "' WHERE id = " + id + ";";
		
		String sql2 = "SELECT * FROM table_desafio WHERE id = " + id + ";";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		ResultSet rset = null;
		
		try {
			
			conn = ConnectionFactory.creatConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			int rs = pstm.executeUpdate();
			
			if (rs != 0) {
				System.out.println("\nRegistro alterado com sucesso!\n");
				
				conn = ConnectionFactory.creatConnectionToMySQL();
				
				pstm = (PreparedStatement) conn.prepareStatement(sql2);
				
				rset = pstm.executeQuery();
				
				
				while (rset.next()) {
					String nome = rset.getString("Nome");
					String email = rset.getString("Email");
					String senha = rset.getString("Senha");
					String data_cadastro = rset.getString("Data_Cadastro");
					  
					System.out.println("Nome: "+nome);
					System.out.println("Id: "+id);
					System.out.println("E-mail: "+email);
					System.out.println("Senha: "+senha);
					System.out.println("Data de Cadastro: "+data_cadastro);

					  
					}
				
			
		
			
			
			} else {
            	System.out.println("\nRegistro não existente!");
            }
			
			

			
			
			
		} catch (Exception e) {
			valida = e.getMessage(); 
			e.printStackTrace();
			
		} 
		
		
	}
	
	public String getValida() {
		return valida;
	}


}
