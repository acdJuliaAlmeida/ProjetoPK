package up.pokemon.pokemon;

import java.util.List;
import java.util.Map;

public class Pokemon {
    private final String name;
    private final List<PokemonType> types;
    private final Map<StatType, StatComponent> stats;

    public Pokemon(String name, List<PokemonType> types, Map<StatType, StatComponent> stats) {
        this.name = name;
        this.types = types;
        this.stats = stats;
    }

    public void printStats() {
        System.out.println("Stats de " + name + ":");
        for (StatType type : StatType.values()) {
            StatComponent stat = stats.get(type);
            if (stat != null) {
                System.out.println(" - " + stat.getDescription() + " : " + stat.getValue());
            }
        }
    }

    public void applyBuff(StatType statType, int buffAmount) {
        StatComponent stat = stats.get(statType);
        if (stat != null) {
            stats.put(statType, new StatBuff(stat, buffAmount));
        }
    }

    public void applyMultiplier(StatType statType, double multiplier) {
        StatComponent stat = stats.get(statType);
        if (stat != null) {
            stats.put(statType, new StatMultiplier(stat, multiplier));
        }
    }
}