package up.pokemon.pokemon;

public class StatMultiplier extends StatDecorator {
    private final double multiplier;

    public StatMultiplier(StatComponent wrapped, double multiplier) {
        super(wrapped);
        this.multiplier = multiplier;
    }

    @Override
    public int getValue() {
        return (int) (wrapped.getValue() * multiplier);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " * multiplier(" + multiplier + ")";
    }
}
