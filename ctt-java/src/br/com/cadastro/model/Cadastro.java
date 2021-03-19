package br.com.cadastro.model;


import java.util.Date;

public class Cadastro {
	
	private int id;
	private String Nome;
	private String Email;
	private String Senha;
	private String Data_Cadastro;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getData_Cadastro() {
		return Data_Cadastro;
	}
	public void setData_Cadastro(String data_Cadastro) {
		Data_Cadastro = data_Cadastro;
	}

	
	

}
