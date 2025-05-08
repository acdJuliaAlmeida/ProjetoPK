package up.pokemon.pokemon;

public class StatBuff extends StatDecorator {
    private final int buff;

    public StatBuff(StatComponent wrapped, int buff) {
        super(wrapped);
        this.buff = buff;
    }

    @Override
    public int getValue() {
        return wrapped.getValue() + buff;
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + buff(" + buff + ")";
    }
}
