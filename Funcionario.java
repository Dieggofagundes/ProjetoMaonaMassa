import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.text.NumberFormat;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getSalarioFormatado() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(salario);
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    @SuppressWarnings("deprecation")
	public BigDecimal getSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        return salario.divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s",
                nome, getDataNascimentoFormatada(), getSalarioFormatado(), funcao);
    }
} 