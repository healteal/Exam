import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final CellType cellType;
    private final int number;
    private boolean isFree;
    private final int MAX_LENGTH;
    private int length = 1;
    private final List<Animal> animals = new ArrayList<>();

    public Cell(CellType cellType, int number) {
        this.cellType = cellType;
        MAX_LENGTH = lengthForCell(cellType);
        this.number = number;
        this.isFree = true;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public CellType getCellType() {
        return cellType;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree() {
        this.isFree = length <= MAX_LENGTH;
    }

    public int getNumber() {
        return number;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    private int lengthForCell(CellType cellType) {
        if (cellType == CellType.SINGLE) {
            return 1;
        } else if (cellType == CellType.DOUBLE) {
            return 2;
        } else {
            return 3;
        }

    }

    public void addAnimalToCell(Animal animal) {
        if (isFree) {
            animals.add(animal);
        } else {
            System.out.println("В клетке нет места");
        }
    }

    public void removeAnimalFromCell(String name) {
        Animal animalToRemove = animals
                .stream()
                .filter(animal -> animal.getName().equals(name))
                .findAny().orElse(null);
        boolean isRemoved = animals.remove(animalToRemove);
        if (isRemoved) {
            System.out.println("Животное убрано из клетки");
        } else {
            System.out.println("Животного найдено не было в клетке");
        }
    }
}
