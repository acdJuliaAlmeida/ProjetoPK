package up.pokemon.pokemon;

public class BaseStat implements StatComponent {
    private final int value;
    private final StatType statType;

    public BaseStat(StatType statType, int value) {
        this.statType = statType;
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return statType + ": " + value;
    }

    @Override
    public StatType getStatType() {
        return statType;
    }
}
