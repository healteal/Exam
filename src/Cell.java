import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final CellType cellType;
    private final int number;
    private boolean isFree;
    private final List<Animal> animals = new ArrayList<>();

    public Cell(CellType cellType, int number) {
        this.cellType = cellType;
        this.number = number;
        this.isFree = true; //???????????????????????????
    }

    public CellType getCellType() {
        return cellType;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getNumber() {
        return number;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal) {
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
