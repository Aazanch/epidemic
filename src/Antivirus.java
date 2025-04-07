import java.lang.reflect.Field;
import java.util.Arrays;

public class Antivirus {
    /*
    * - метод излечения. Производит лечение - восстановление исходной последовательности клеток.
    *  Для этого анализируются возможные алгоритмы заражения
    *  и для каждого случая сравнивается значение по хэш-функции с контрольным.
    *  Организм считается излеченным, если значения хэш-функции и контрольное совпадают!
    *  Найденный результат записывается в массив клеток.
     * */
    public boolean ApplCureAlgorithm(Organism organism, int algorithmIndex){
        try {
            /// Получение доступа к слеткам организма через рефлексию
            Field cellsField = Organism.class.getDeclaredField("cells");
            cellsField.setAccessible(true);
            int[] currentCells = (int[]) cellsField.get(organism);
            /// Создание копии текущего состояния клеток
            int[] originalCells = Arrays.copyOf(currentCells, currentCells.length);
            for (int algorithm = 1; algorithm <= 3; algorithm++) {
                // Восстановление исходного состояния перед каждой попыткой
                System.arraycopy(originalCells, 0, currentCells, 0, currentCells.length);
                switch (algorithm) {
                    case 1: mirrorAlgorithm(currentCells); break;
                    case 2: negateAlgorithm(currentCells); break;
                    case 3: decrementAlgorithm(currentCells); break;
                }
                // Проверка контрольного значения
                if (checkHash(organism, currentCells)) {
                    cellsField.set(organism, currentCells.clone());
                    return true;
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось заразить организм", e);
        }

        return false;
    }
    //роверка хэша
    private boolean checkHash(Organism organism, int[] cells) {
        // Вычисляем хэш так же, как это делает Organism
        int testHash = 0;
        int prime = 50;
        for (int num : cells) {
            testHash = prime * testHash + num;
        }
        return testHash == organism.getControlValue();
    }
    /// 1й алгоритм излечения.
    private void mirrorAlgorithm(int[] cells) {
        for (int i = 0; i < cells.length / 2; i++) {
            int temp = cells[i];
            cells[i] = cells[cells.length - 1 - i];
            cells[cells.length - 1 - i] = temp;
        }
    }
    /// 2й алгоритм излечения.
    private void negateAlgorithm(int[] cells) {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = -cells[i];
        }
    }
    /// 3й алгоритм излечения.
    private void decrementAlgorithm(int[] cells) {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = (cells[i] - 1 + 10) % 10; // Обработка переполнения (0 → 9)
        }
    }
}