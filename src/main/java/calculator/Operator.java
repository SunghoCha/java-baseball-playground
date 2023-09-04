package calculator;


import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operator {

    PLUS("+") {
        public double apply(double x, double y) {return x + y;}
    },
    MiNUS("-") {
        public double apply(double x, double y) {return x - y;}
    },
    TIMES("*") {
        public double apply(double x, double y) {return x * y;}
    },
    DIVIDE("/") {
        public double apply(double x, double y) {return x / y;}
    };

    private final String symbol;

    public String getSymbol() {return symbol;}

    Operator(String symbol) { this.symbol = symbol;}
    public abstract double apply(double x, double y);

    private static final Map<String, Operator> operatorMap =
            Stream.of(values()).collect(Collectors.toMap(Operator::getSymbol, e -> e));

    public static Operator findBySymbol(String symbol) {
        return operatorMap.get(symbol);
    }
}
