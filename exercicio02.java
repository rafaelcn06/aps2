package exercicio2;

import java.util.ArrayList;
import java.util.List;

// ================= Interface =================
interface FiguraGeometrica { // 🔹 sem "public"
    double calculaArea();

    String getNomeFigura();
}

// ================= Classe Triangulo =================
class Triangulo implements FiguraGeometrica {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calculaArea() {
        return (this.base * this.altura) / 2;
    }

    @Override
    public String getNomeFigura() {
        return "Triângulo";
    }
}

// ================= Classe Losango =================
class Losango implements FiguraGeometrica {
    private double diagonal1;
    private double diagonal2;

    public Losango(double diagonal1, double diagonal2) {
        this.diagonal1 = diagonal1;
        this.diagonal2 = diagonal2;
    }

    @Override
    public double calculaArea() {
        return (this.diagonal1 * this.diagonal2) / 2;
    }

    @Override
    public String getNomeFigura() {
        return "Losango";
    }
}

// ================= Classe GerenteDeFiguras =================
class GerenteDeFiguras {
    private List<FiguraGeometrica> figuras;

    public GerenteDeFiguras() {
        figuras = new ArrayList<FiguraGeometrica>();
    }

    public void adicionaFigura(FiguraGeometrica figura) {
        figuras.add(figura);
    }

    public void imprimeFiguras() {
        for (FiguraGeometrica f : figuras) {
            System.out.println(f.getNomeFigura());
        }
    }

    public double getMaiorAreaDeFigura() {
        double maior = 0;
        for (FiguraGeometrica f : figuras) {
            double area = f.calculaArea();
            if (area > maior) {
                maior = area;
            }
        }
        return maior;
    }

    public double calculaAreaTotal() {
        double total = 0;
        for (FiguraGeometrica f : figuras) {
            total += f.calculaArea();
        }
        return total;
    }
}

// ================= Classe Principal =================
public class ProgramaDasFiguras {
    public static void main(String[] args) {
        GerenteDeFiguras gerente = new GerenteDeFiguras();

        Triangulo t1 = new Triangulo(2.0, 3.0);
        gerente.adicionaFigura(t1);

        Losango l1 = new Losango(4.0, 6.0);
        Losango l2 = new Losango(5.0, 3.0);
        gerente.adicionaFigura(l1);
        gerente.adicionaFigura(l2);

        System.out.println("Figuras cadastradas:");
        gerente.imprimeFiguras();

        System.out.println("Área total de todas as figuras: " + gerente.calculaAreaTotal());
        System.out.println("Maior área entre as figuras: " + gerente.getMaiorAreaDeFigura());
    }
}
