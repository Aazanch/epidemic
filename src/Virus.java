import java.lang.reflect.Field;
public class Virus {
    /*- метод заражения.
    Принимают экземпляр класса Организм и индекс алгоритма заражения.
    По указанному алгоритму заражает (меняет) клетки организма.
    */
    void ApplyInfectAlgorithm(Organism organism, int algorithmIndex){
        try {
            /// Получение доступа к слеткам организма через рефлексию
            Field cellsField = Organism.class.getDeclaredField("cells");
            cellsField.setAccessible(true);
            int[] cells = (int[]) cellsField.get(organism);
            switch (algorithmIndex) {
                case 1 -> mirrorAlgorithm(cells);
                case 2 -> negateAlgorithm(cells);
                case 3 -> incrementAlgorithm(cells);
                default -> throw new IllegalArgumentException("Неизвестный алгоритм заражения: " + algorithmIndex);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось заразить организм", e);
        }
    }
    /// 1й алгоритм заражения. последовательность становится зеркальным отражением исходной.
    private void mirrorAlgorithm(int[] cells) {
        for (int i = 0; i < cells.length / 2; i++) {
            int temp = cells[i];
            cells[i] = cells[cells.length - 1 - i];
            cells[cells.length - 1 - i] = temp;
        }
    }
    /// 2й алгоритм заражения. все числа становятся отрицательными.
    private void negateAlgorithm(int[] cells) {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = -cells[i];
        }
    }
    /// 3й алгоритм заражения. к каждому числу прибавляется 1. Если было 9, станет 0
    private void incrementAlgorithm(int[] cells) {
        for (int i = 0; i < cells.length; i++) {
            cells[i] = (cells[i] + 1) % 10;
        }
    }
}