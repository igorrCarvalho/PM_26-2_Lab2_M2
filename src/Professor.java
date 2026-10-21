import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
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
 * Classe abstrata base que representa um professor, com nome, matrícula e disciplinas.
 */
public abstract class Professor {

	/**
	 * Valor da hora-aula, em reais.
	 */
	protected static final double HORA_AULA = 42;
	/**
	 * Percentual de horas extraclasse adicionais sobre as horas-aula.
	 */
	protected static final double EXTRA_CLASSE = 0.2;
	/**
	 * Quantidade média de semanas por mês.
	 */
	protected static final double SEMANAS_MES = 4.5;
	/**
	 * Nome do professor.
	 */
	private String nome;
	/**
	 * Matrícula do professor.
	 */
	private int matricula;
	/**
	 * Disciplinas ministradas pelo professor.
	 */
	protected List<EDisciplina> disciplinas;

	/**
	 * Cria um professor.
	 *
	 * Se o nome for vazio, é usado "Professor X"; se a matrícula for negativa,
	 * é gerada uma matrícula a partir do horário atual.
	 *
	 * @param nome nome do professor
	 * @param matricula matrícula do professor
	 */
	protected Professor(String nome, int matricula) {
		if(nome.length() == 0)
			nome = "Professor X";
		if(matricula < 0)
			matricula = LocalDateTime.now().get(ChronoField.MICRO_OF_SECOND);
		this.nome = nome;
		this.matricula = matricula;
		disciplinas = new LinkedList<>();
	}

	/**
	 * Calcula a carga horária atual das aulas de um professor, já incluindo o extraclasse.
	 *
	 * @return carga horária em horas.
	 */
	protected double cargaAtual(){
		double horas = 0;
		for (EDisciplina eDisciplina : disciplinas) {
			horas += eDisciplina.horasAula();
		}
		return horas * (1+EXTRA_CLASSE);
	}

	/**
	 * Inclui disciplinas para o professor.
	 *
	 * @param turmas lista de disciplinas a incluir
	 * @return quantidade de disciplinas do professor após a inclusão
	 */
	public abstract int incluirDisciplinas(List<EDisciplina> turmas);

	/**
	 * Calcula o salário mensal do professor.
	 *
	 * @return valor do salário mensal, em reais
	 */
	public abstract double salarioMensal();

	/**
	 * Retorna a representação textual do professor com nome, matrícula, carga e salário.
	 *
	 * @return texto descritivo do professor
	 */
	@Override 
	public String toString() {
		return String.format("%s (%d) - com carga %.1f e salário R$ %2.f", nome, matricula, cargaAtual(), salarioMensal());
	}

}