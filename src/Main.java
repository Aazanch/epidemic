import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        // 3.1. Создание 12 организмов (по одному на каждый месяц)
        List<Organism> organisms = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            int month = i;
            int[] cells = {month / 10, month % 10};  // Будет [0,1] до [1,2]
            organisms.add(new Organism(cells));
        }
        // 3.2. Определение, какие организмы будут заражены (2 вариант -
        // Фильтрация: в зоне поражения организмы с нечётным месяцем в дате рождения.
        // Сортировка: заражению больше подвержены наименее молодые организмы.)
        // Фильтр: организмы с нечетными номерами месяцев (1, 3, 5, 7, 9, 11)
        List<Organism> potentialInfected = new ArrayList<>();
        for (int i = 0; i < organisms.size(); i++) {
            if ((i + 1) % 2 != 0) {
                potentialInfected.add(organisms.get(i));
            }
        }
        // Сортировка: сначала наименее молодые организмы (ранние месяцы идут первыми)
        potentialInfected.sort(Comparator.comparingInt(organisms::indexOf));
        // Первые 3 организма
        List<Organism> infectedOrganisms = potentialInfected.subList(0, Math.min(3, potentialInfected.size()));
        // 3.3. Создание экземпляра вируса и заражение выбранных организмов
        Virus virus = new Virus();
        for (int i = 0; i < infectedOrganisms.size(); i++) {
            virus.ApplyInfectAlgorithm(infectedOrganisms.get(i), i + 1);
        }
        // 3.4. Создание экземпляра антивируса и лечение зараженныч организмов
        Antivirus antivirus = new Antivirus();
        for (Organism organism : infectedOrganisms) {
            boolean cured = antivirus.ApplCureAlgorithm(organism, 0);
            System.out.println("Организм с контрольным значением " +
                    organism.getControlValue() + " вылечен: " + cured);
        }
    }
}