package up.pokemon.pokemon;

public abstract class StatDecorator implements StatComponent {
    protected final StatComponent wrapped;

    public StatDecorator(StatComponent wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public StatType getStatType() {
        return wrapped.getStatType();
    }
}
