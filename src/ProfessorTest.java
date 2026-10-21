import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ProfessorTest {
    @Test
    public void calculaSalario() {
        Contratado contratado = new Contratado("Jose", 3213131, LocalDate.now());
        List<EDisciplina> disciplinas = new LinkedList<>();
        disciplinas.add(EDisciplina.ARQCOMP);
        disciplinas.add(EDisciplina.DIW);
        disciplinas.add(EDisciplina.PROGMOD);
        contratado.incluirDisciplinas(disciplinas);
        double salario = contratado.salarioMensal();
        assertEquals(14*1.2*42*4.5, salario, 0.1);

    }
}