package up.pokemon.scraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonScraper {

    static class Pokemon {
        private String number;
        private String name;
        private int hp;
        private int attack;
        private int defense;
        private int speed;
        private int special;
        private int total;
        private double average;

        public Pokemon(String number, String name, int hp, int attack, int defense,
                       int speed, int special, int total, double average) {
            this.number = number;
            this.name = name;
            this.hp = hp;
            this.attack = attack;
            this.defense = defense;
            this.speed = speed;
            this.special = special;
            this.total = total;
            this.average = average;
        }
    }

    public static void main(String[] args) {
        String url = "https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_base_stats_in_Generation_I";
        List<Pokemon> pokemons = new ArrayList<>();

        System.out.println("Diretório de trabalho atual: " + System.getProperty("user.dir"));
        try {
            Document doc = Jsoup.connect(url).get();
            Elements tables = doc.select("table.sortable");

            for (Element table : tables) {
                Elements headers = table.select("th");
                boolean hasPokedexNumber = headers.stream().anyMatch(h -> h.text().trim().equals("#"));

                if (hasPokedexNumber) {
                    Elements rows = table.select("tr");
                    for (int i = 1; i < rows.size(); i++) {
                        Elements coisa = rows.get(i).select("a");
                        Elements cols = rows.get(i).select("td");
                        if (cols.size() >= 9) {
                            try {
                                String number = cols.get(0).text().trim().replaceAll("[^0-9]", "");
                                String name = coisa.get(1).text().trim();
                                String hpText = cols.get(3).text().trim().replaceAll("[^0-9]", "");
                                String attackText = cols.get(4).text().trim().replaceAll("[^0-9]", "");
                                String defenseText = cols.get(5).text().trim().replaceAll("[^0-9]", "");
                                String speedText = cols.get(6).text().trim().replaceAll("[^0-9]", "");
                                String specialText = cols.get(7).text().trim().replaceAll("[^0-9]", "");
                                String totalText = cols.get(8).text().trim().replaceAll("[^0-9]", "");
                                String averageText = cols.get(9).text().trim().replaceAll("[^0-9.]+", "");

                                if (number.isEmpty() || attackText.isEmpty() ||
                                        defenseText.isEmpty() || speedText.isEmpty() || specialText.isEmpty() ||
                                        totalText.isEmpty() || averageText.isEmpty()) {

                                    System.out.println("Linha vazia");
                                    continue; // pula linha se algum campo estiver vazio após limpeza

                                }

                                int hp = Integer.parseInt(hpText);
                                int attack = Integer.parseInt(attackText);
                                int defense = Integer.parseInt(defenseText);
                                int speed = Integer.parseInt(speedText);
                                int special = Integer.parseInt(specialText);
                                int total = Integer.parseInt(totalText);
                                double average = Double.parseDouble(averageText);

                                pokemons.add(new Pokemon(number, name, hp, attack, defense, speed, special, total, average));
                            } catch (NumberFormatException e) {
                                System.err.println("Erro de parse na linha " + (i + 1) + ": " + e.getMessage());
                            }
                        }
                    }

                    break; // Tabela já encontrada e processada
                }
            }

            System.out.println("Pokémons encontrados: " + pokemons.size());
            for (Pokemon p : pokemons) {
                System.out.println(p);
            }

            // Serializa e salva no arquivo JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter("generation1_stats.json")) {
                gson.toJson(pokemons, writer);
                System.out.println("Arquivo 'generation1_stats.json' criado com sucesso.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
