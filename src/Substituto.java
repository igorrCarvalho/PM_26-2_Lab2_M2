
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
 * Representa um professor substituto, contratado temporariamente com contrato renovável.
 */
public class Substituto extends Professor {

	/**
	 * Quantidade mínima de horas-aula do substituto.
	 */
	private static final double MIN_AULAS = 8;
	/**
	 * Quantidade máxima de horas-aula do substituto.
	 */
	private static final double MAX_AULAS = 20;
	/**
	 * Tempo mínimo, em anos, para a renovação do contrato.
	 */
	private static final int TEMPO_RENOVACAO = 2;

	/**
	 * Data da última renovação (ou da contratação) do contrato.
	 */
	private LocalDate ultimaRenovacao;

	/**
	 * Cria um professor substituto.
	 *
	 * Se a data de contratação for posterior à data atual, a data atual é utilizada.
	 *
	 * @param nome nome do professor
	 * @param matricula matrícula do professor
	 * @param contratacao data de contratação do professor
	 */
	public Substituto(String nome, int matricula, LocalDate contratacao) {
		super(nome, matricula);
		if(contratacao.isAfter(LocalDate.now()))
			contratacao = LocalDate.now();
		ultimaRenovacao = contratacao;

	}

	/**
	 * Renova o contrato se já se passaram ao menos {@code TEMPO_RENOVACAO} anos desde a última renovação.
	 *
	 * @return {@code true} se o contrato foi renovado; {@code false} caso contrário
	 */
	public boolean renovarContrato() {
		long tempoContrato = ultimaRenovacao.until(LocalDate.now(), ChronoUnit.YEARS);
		boolean podeRenovar = (tempoContrato >= TEMPO_RENOVACAO);
		if(podeRenovar)
			ultimaRenovacao = LocalDate.now();
		return  podeRenovar;
	}

	/**
	 * Inclui disciplinas na lista de disciplinas do substituto.
	 *
	 * @param turmas lista de disciplinas a serem incluídas
	 * @return quantidade de disciplinas do substituto após a inclusão
	 */
	public int incluirDisciplinas(List<EDisciplina> turmas) {
		disciplinas = super.disciplinas;
		if (cargaAtual() <= MAX_AULAS) return disciplinas.size();
		for (EDisciplina d : turmas) {
			disciplinas.add(d);
		}
		return disciplinas.size();
	}

	/**
	 * Calcula o salário mensal do substituto com base na carga atual, hora-aula e semanas por mês.
	 *
	 * @return valor do salário mensal, em reais
	 */
	public double salarioMensal() {
		return cargaAtual() * HORA_AULA * SEMANAS_MES;
	}
}