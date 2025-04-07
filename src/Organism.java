public class Organism {
    private int[] cells;
    private final int controlValue;
    public Organism(int[] cells) {
        this.cells = cells.clone();
        this.controlValue = calculateHash(cells);
    }
    ///Хэш-функция
    private int calculateHash(int[] array) {
        int hash = 0;
        int prime = 50; // Простое число для хэширования
        for (int i = 0; i < array.length; i++) {
            hash = prime * hash + array[i];
        }
        return hash;
    }

    ///Геттер для контрольного значения

    public int getControlValue() {
        return controlValue;
    }
}