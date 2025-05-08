package up;

import up.pokemon.pokemon.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Map<StatType, StatComponent> stats = new EnumMap<>(StatType.class);
        stats.put(StatType.ATTACK, new BaseStat(StatType.ATTACK, 80));
        stats.put(StatType.SPEED, new BaseStat(StatType.SPEED, 70));

        List<PokemonType> types = Arrays.asList(PokemonType.FIRE);

        Pokemon charmeleon = new Pokemon("Charmeleon", types, stats);

        charmeleon.printStats();

        charmeleon.applyBuff(StatType.ATTACK, 15);
        charmeleon.applyMultiplier(StatType.SPEED, 1.5);

        System.out.println("\nApós aplicar modificadores:");
        charmeleon.printStats();
    }
}
