package br.com.fiap.healthtrack.user;


/**
 * @author Sid
 * Define genero conforme especificado para o healthtrack
 * Na proxima versao recebera um campo short para representacao no BD conforme MER apresentado antes
 */
public enum Genero {	
	MASCULINO("Masculino"), FEMININO("Feminino");
	
	
	private String descricao;
	//TODO: definir campo short para representacao no BD

	Genero(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	
	
}
