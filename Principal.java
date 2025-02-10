import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        // 3.1 - Criar lista de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), 
                new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), 
                new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), 
                new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), 
                new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), 
                new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), 
                new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), 
                new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), 
                new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), 
                new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), 
                new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover João
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("\n--- Lista de Funcionários ---");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumento de 10%
        funcionarios.forEach(f -> 
            f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));

        // 3.5 e 3.6 - Agrupar e imprimir por função
        System.out.println("\n--- Funcionários por Função ---");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(System.out::println);
        });

        // 3.8 - Aniversariantes de outubro e dezembro
        System.out.println("\n--- Aniversariantes de Outubro e Dezembro ---");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || 
                           f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        // 3.9 - Funcionário mais velho
        Funcionario maisVelho = funcionarios.stream()
                .max(Comparator.comparing(f -> f.getDataNascimento()))
                .orElse(null);
        
        if (maisVelho != null) {
            System.out.println("\nFuncionário mais velho:");
            System.out.printf("Nome: %s, Idade: %d anos%n", 
                    maisVelho.getNome(), maisVelho.getIdade());
        }

        // 3.10 - Lista por ordem alfabética
        System.out.println("\n--- Funcionários em Ordem Alfabética ---");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // 3.11 - Total dos salários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        System.out.println("\nTotal dos salários: " + 
                NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(totalSalarios));

        // 3.12 - Salários mínimos por funcionário
        System.out.println("\n--- Salários Mínimos por Funcionário ---");
        funcionarios.forEach(f -> 
            System.out.printf("%s: %.2f salários mínimos%n", 
                    f.getNome(), f.getSalariosMinimos()));
    }
} 