public interface PizzaIngredientFactory {
    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Pepperoni createPepperoni();
    public Clam createClam();
}

interface Dough { }
class ThinCrustDough implements Dough { public String toString() { return "Massa fina crocante"; } }
class ThickCrustDough implements Dough { public String toString() { return "Massa grossa estilo Chicago"; } }

interface Sauce { }
class MarinaraSauce implements Sauce { public String toString() { return "Molho Marinara"; } }
class PlumTomatoSauce implements Sauce { public String toString() { return "Molho de tomate estilo Chicago"; } }

interface Cheese { }
class ReggianoCheese implements Cheese { public String toString() { return "Queijo Reggiano"; } }
class MozzarellaCheese implements Cheese { public String toString() { return "Mussarela"; } }

interface Veggies { }
class Garlic implements Veggies { public String toString() { return "Alho"; } }
class Onion implements Veggies { public String toString() { return "Cebola"; } }
class Mushroom implements Veggies { public String toString() { return "Cogumelos"; } }
class RedPepper implements Veggies { public String toString() { return "Pimentão vermelho"; } }
class Spinach implements Veggies { public String toString() { return "Espinafre"; } }
class BlackOlives implements Veggies { public String toString() { return "Azeitonas pretas"; } }
class Eggplant implements Veggies { public String toString() { return "Berinjela"; } }

interface Pepperoni { }
class SlicedPepperoni implements Pepperoni { public String toString() { return "Calabresa fatiada"; } }

interface Clam { }
class FreshClam implements Clam { public String toString() { return "Mariscos frescos"; } }
class FrozenClam implements Clam { public String toString() { return "Mariscos congelados"; } }

class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() {
        return new ThinCrustDough();
    }
    public Sauce createSauce() {
        return new MarinaraSauce();
    }
    public Cheese createCheese() {
        return new ReggianoCheese();
    }
    public Veggies[] createVeggies() {
        Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
        return veggies;
    }
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }
    public Clam createClam() {
        return new FreshClam();
    }
}

class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    public Dough createDough() {
        return new ThickCrustDough();
    }
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }
    public Veggies[] createVeggies() {
        Veggies veggies[] = { new BlackOlives(), new Spinach(), new Eggplant() };
        return veggies;
    }
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }
    public Clam createClam() {
        return new FrozenClam();
    }
}

abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clam clam;

    abstract void prepare();

    void bake() {
        System.out.println("Assar por 25 minutos a 350°");
    }

    void cut() {
        System.out.println("Cortar a pizza em fatias diagonais");
    }

    void box() {
        System.out.println("Colocar a pizza na caixa oficial da pizzaria");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("---- " + name + " ----\n");
        if (dough != null) result.append(dough + "\n");
        if (sauce != null) result.append(sauce + "\n");
        if (cheese != null) result.append(cheese + "\n");
        if (veggies != null) {
            for (Veggies v : veggies) result.append(v + "\n");
        }
        if (clam != null) result.append(clam + "\n");
        if (pepperoni != null) result.append(pepperoni + "\n");
        return result.toString();
    }
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        System.out.println("Preparando " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}

class ClamPizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        System.out.println("Preparando " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        clam = ingredientFactory.createClam();
    }
}

abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        System.out.println("--- Fazendo uma " + pizza.getName() + " ---");
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    protected abstract Pizza createPizza(String item);
}

class NYStylePizzaStore extends PizzaStore {
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if (item.equals("queijo")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("Pizza de Queijo Estilo Nova York");
        } else if (item.equals("mariscos")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("Pizza de Mariscos Estilo Nova York");
        }
        return pizza;
    }
}

class ChicagoStylePizzaStore extends PizzaStore {
    protected Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new ChicagoPizzaIngredientFactory();
        if (item.equals("queijo")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("Pizza de Queijo Estilo Chicago");
        } else if (item.equals("mariscos")) {
            pizza = new ClamPizza(ingredientFactory);
            pizza.setName("Pizza de Mariscos Estilo Chicago");
        }
        return pizza;
    }
}

public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYStylePizzaStore();
        PizzaStore chicagoStore = new ChicagoStylePizzaStore();

        Pizza pizza1 = nyStore.orderPizza("queijo");
        System.out.println(pizza1 + "\n");

        Pizza pizza2 = chicagoStore.orderPizza("mariscos");
        System.out.println(pizza2);
    }
}
