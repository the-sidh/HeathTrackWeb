package br.com.fiap.healthtrack.user;

import java.util.Date;

/**
 * @author Sid Representacao do usuario do healthtrack
 */
public class User {

	private String nome;
	private Date dataNasc;
	private Genero genero;
	private double altura;
	private String email;
	private String password;
	private int id;
	private String _id;

	public User() {
		super();
	}

	/**
	 * @param nome
	 * @param dataNasc
	 * @param genero
	 * @param altura
	 * @param email
	 * @param password
	 */
	public User(String nome, Date dataNasc, Genero genero, float altura, String email, String password) {
		super();
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.genero = genero;
		this.altura = altura;
		this.email = email;
		this.password = password;
	}

	/**
	 * Este construtor é usado pela primeira página de cadastro
	 * 
	 * @param nome
	 * @param dataNasc
	 * @param genero
	 * @param altura
	 */
	public User(String nome, Date dataNasc, Genero genero, float altura) {
		super();
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.genero = genero;
		this.altura = altura;
	}

	/**
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return
	 */
	public Date getDataNasc() {
		return dataNasc;
	}

	/**
	 * @param dataNasc
	 */
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	/**
	 * @return
	 */
	public Genero getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 */
	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	/**
	 * @return
	 */
	public double getAltura() {
		return altura;
	}

	/**
	 * @param altura
	 */
	public void setAltura(float altura) {
		this.altura = altura;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "User [nome=" + nome + ", dataNasc=" + dataNasc + ", genero=" + genero + ", altura=" + altura
				+ ", email=" + email + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
