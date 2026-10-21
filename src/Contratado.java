import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/** 
 * MIT License
 *
 * Copyright(c) 2026 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 * @author Joao Caram @joaocaram (caram@pucminas.br)
 * Utilização do @Copilot para geração de documentação JavaDoc, revisada.
 * 
 */

/**
 * Representa um professor contratado, que pode acumular disciplinas
 * e outras atividades, respeitando a carga horária máxima semanal.
 */
public class Contratado extends Professor {

	/**
	 * Carga horária semanal máxima permitida para o contratado.
	 */
	private static final int MAX_HORAS = 40;
	/**
	 * Percentual de adicional salarial concedido por tempo de casa.
	 */
	private static final double ADICIONAL = 0.05;
	/**
	 * Quantidade de anos de contratação necessários para receber o adicional.
	 */
	private static final int ANOS_ADICIONAL = 5;
	/**
	 * Data de contratação do professor.
	 */
	private LocalDate dataContratacao;
	/**
	 * Lista de atividades extras exercidas pelo contratado.
	 */
	private List<EAtividade> outrasFuncoes;

	/**
	 * Cria um professor contratado.
	 *
	 * Se a data de contratação for posterior à data atual, a data atual é utilizada.
	 *
	 * @param nome nome do professor
	 * @param matricula matrícula do professor
	 * @param contratacao data de contratação do professor
	 */
	public Contratado(String nome, int matricula, LocalDate contratacao) {
		super(nome, matricula);
		if(contratacao.isAfter(LocalDate.now()))
			contratacao = LocalDate.now();
		dataContratacao = contratacao;
		outrasFuncoes = new LinkedList<>();
	}

	/**
	 * Calcula a carga horária semanal atual do contratado.
	 *
	 * @return carga horária atual, em horas
	 */
	@Override 
	protected double cargaAtual(){
		double horas = 0;
		for (EDisciplina eDisciplina : disciplinas) {
			horas += eDisciplina.horasAula();
		}
		for (EAtividade eAtividade : outrasFuncoes) {
			horas += eAtividade.cargaHoraria();
		}
		return horas * (1+EXTRA_CLASSE);
	}
	

	/**
	 * Inclui disciplinas na lista de disciplinas do contratado.
	 *
	 * @param turmas lista de disciplinas a serem incluídas
	 * @return quantidade de disciplinas do contratado após a inclusão
	 */
	public int incluirDisciplinas(List<EDisciplina> turmas) {
		disciplinas = super.disciplinas;
		if (cargaAtual() >= MAX_HORAS) return disciplinas.size();
		for (EDisciplina d : turmas) {
			disciplinas.add(d);
		}
		return disciplinas.size();
	}

	/**
	 * Inclui uma atividade extra, desde que a carga horária total não atinja o máximo permitido.
	 *
	 * @param funcao atividade a ser incluída; se {@code null}, nada é feito
	 * @return quantidade de atividades extras do contratado após a operação
	 */
	public int incluirAtividade(EAtividade funcao) {		
		if(funcao != null){
			if(cargaAtual() + funcao.cargaHoraria() < MAX_HORAS){
				outrasFuncoes.add(funcao);
			}
		}
		return  outrasFuncoes.size();		
	}

	/**
	 * Calcula o salário mensal do contratado.
	 *
	 * @return valor do salário mensal, em reais
	 */
	public double salarioMensal() {
		// valor da h/a, multiplicado pela carga horária * 4.5
		// 5% a cada 5 anos
		double resultadoSalario = cargaAtual() * HORA_AULA * SEMANAS_MES;
		int ano = (LocalDate.now().getYear() - dataContratacao.getYear()) / ANOS_ADICIONAL;
		for (int i = 0; i < ano; i++) {
			resultadoSalario = resultadoSalario * (1 + ADICIONAL);
		}

		return resultadoSalario;
	}
}