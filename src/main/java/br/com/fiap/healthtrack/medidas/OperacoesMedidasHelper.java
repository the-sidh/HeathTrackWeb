package br.com.fiap.healthtrack.medidas;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.fiap.healthtrack.medidas.peso.IMC;
import br.com.fiap.healthtrack.medidas.pressao.SituacaoPressao;


/**
 * @author Sid
 * Classe auxiliar, executada como singleton, com métodos de conveniencia para operações do healthtrack
 */
public class OperacoesMedidasHelper {

	private static OperacoesMedidasHelper instance;

	public static OperacoesMedidasHelper getInstance() {
		if (instance == null) {
			instance = new OperacoesMedidasHelper();
		}
		return instance;
	}

	/**
	 * @author Sid
	 * Retorna a situação da pressao , de acordo com regra de negócio definida no caso de uso
	 * RN4 – para registros acima de 140/90mmHg a situação é considerada “Elevada”. Abaixo de 120/80mmHg é “Abaixo do normal”. Entre
	 * estes valores é “Normal”
	 *@param	sistolica	a medida da pressao sistolica
	 *@param	diastolica	a medida da pressao diastolica
	 */
	public SituacaoPressao getSituacaoPressao(int sistolica, int diastolica ) {
		SituacaoPressao situacao;
		if(sistolica>=140 ||diastolica>=90) {
			situacao = SituacaoPressao.ELEVADA;
		}else if(sistolica <=120 || diastolica <=80) {
			situacao = SituacaoPressao.ABAIXO_DO_NORMAL;
		}else {
			situacao = SituacaoPressao.NORMAL;
		}
		return situacao;
	}
	
	/**
	 * Retorna o IMC atual
	 * Retorna a situação do imc de acordo com regra de negocio definida no caso de uso
	 *RN4 – A situação do IMC deve seguir o critério seguinte, estabelecido pela OMS (Organização Mundial da Saúde)
	 *IMC Classificação do IMC
     *< 16 Magreza grave
     *16 a < 17Magreza moderada
     *17 a < 18,5 Magreza leve
     *18,5 a < 25 Saudável
     *25 a < 30Sobrepeso
     *30 a < 35Obesidade Grau I
     *35 a < 40Obesidade Grau II (severa)
     *@param	altura	a altura do usuário
     *@paran	peso	o peso do usuário
     *@return		O IMC do usuario
	 */
	public IMC getIMC(double altura, double peso) {
		IMC IMC = null;
		BigDecimal squareHeigth=new BigDecimal(altura).multiply(new BigDecimal(altura));
		BigDecimal imcCalc = new BigDecimal(peso).divide(squareHeigth,RoundingMode.HALF_UP);
		if(imcCalc.compareTo(new BigDecimal(16))<=0) {
			IMC = IMC.MAGREZA_GRAVE;
		}else if(imcCalc.compareTo(new BigDecimal(16))>0 && imcCalc.compareTo(new BigDecimal(17))<=0) {
			IMC = IMC.MAGREZA_MODERADA;
		}else if(imcCalc.compareTo(new BigDecimal(17))>0 && imcCalc.compareTo(new BigDecimal(18.5))<=0) {
			IMC = IMC.MAGREZA_LEVE;
		}else if(imcCalc.compareTo(new BigDecimal(18.5))>0 && imcCalc.compareTo(new BigDecimal(25))<=0) {
			IMC = IMC.SAUDAVEL;
		}else if(imcCalc.compareTo(new BigDecimal(25))>0 && imcCalc.compareTo(new BigDecimal(30))<=0) {
			IMC = IMC.SOBREPESO;
		}else if(imcCalc.compareTo(new BigDecimal(30))>0 && imcCalc.compareTo(new BigDecimal(35))<=0) {
			IMC = IMC.OBESIDADE_1;
		}else if(imcCalc.compareTo(new BigDecimal(35))>0) {
			IMC = IMC.OBESIDADE_2;
		}		
		return IMC;
		
	}
	
}
