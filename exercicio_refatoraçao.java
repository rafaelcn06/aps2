public class Cliente {
    private String nome;
    private List<Aluguel> alugueis = new ArrayList<>();

    public Cliente(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void adicionaAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public String extrato() {
        double valorTotal = 0;
        int pontosDeAlugadorFrequente = 0;
        StringBuilder resultado = new StringBuilder("Registro de Alugueis de " + getNome() + "\n");

        for (Aluguel aluguel : alugueis) {
            double valorCorrente = aluguel.calculaValor();
            int pontos = aluguel.calculaPontos();

            pontosDeAlugadorFrequente += pontos;
            resultado.append("\t").append(aluguel.getFita().getTitulo()).append("\t").append(valorCorrente).append("\n");
            valorTotal += valorCorrente;
        }

        resultado.append("Valor total devido: ").append(valorTotal).append("\n");
        resultado.append("Você ganhou ").append(pontosDeAlugadorFrequente).append(" pontos de alugador frequente");
        return resultado.toString();
    }
}

public class Aluguel {
    private int diasAlugada;
    private Fita fita;

    public Aluguel(Fita fita, int diasAlugada) {
        this.fita = fita;
        this.diasAlugada = diasAlugada;
    }

    public Fita getFita() {
        return fita;
    }

    public int getDiasAlugada() {
        return diasAlugada;
    }

    public double calculaValor() {
        double valorCorrente = 0;
        switch (fita.getCodigoDePreco()) {
            case Fita.NORMAL:
                valorCorrente += 2;
                if (diasAlugada > 2)
                    valorCorrente += (diasAlugada - 2) * 1.5;
                break;
            case Fita.LANCAMENTO:
                valorCorrente += diasAlugada * 3;
                break;
            case Fita.INFANTIL:
                valorCorrente += 1.5;
                if (diasAlugada > 3)
                    valorCorrente += (diasAlugada - 3) * 1.5;
                break;
        }
        return valorCorrente;
    }

    public int calculaPontos() {
        int pontos = 1;
        if (fita.getCodigoDePreco() == Fita.LANCAMENTO && diasAlugada > 1) {
            pontos++;
        }
        return pontos;
    }
}