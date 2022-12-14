import java.util.List;
import java.util.Objects;

public class Zoo {
    private final List<Animal> animals;
    private final List<Cell> cells;

    public Zoo(List<Animal> animals, List<Cell> cells) {
        this.animals = animals;
        this.cells = cells;
    }

    public void addAnimal(Animal animal) {
        if (isExist(animal)) {
            Cell temp = getCell(animal);
            cells.remove(temp);
            animal.setCell(temp);
            this.animals.add(animal);
            Objects.requireNonNull(temp).addAnimalToCell(animal);
            temp.setLength(temp.getLength() + 1);
            temp.setFree();
            cells.add(temp);
            System.out.println(animal.getAnimalType()
                    + " " + animal.getName()
                    + " помещен в клетку "
                    + temp.getNumber());
            System.out.println();
        } else {
            System.out.println("Нет свободного места\n");
        }
    }

    private Cell getCell(Animal animal) {
        return cells
                .stream()
                .filter(Cell::isFree)
                .filter(x -> x.getCellType() == animal.getTypeOfCell())
                .findAny().orElse(null);
    }

    private boolean isExist(Animal animal) {
        return cells
                .stream()
                .filter(Cell::isFree)
                .anyMatch(x -> x.getCellType() == animal.getTypeOfCell());
    }

    public void removeAnimal(String name) {
        if (isPresent(name)) {
            Animal animal = getAnimal(name);
            Cell tempCell = getTempCell(animal);
            cells.remove(tempCell);
            animals.remove(animal);
            Objects.requireNonNull(tempCell).setLength(tempCell.getLength() - 1);
            Objects.requireNonNull(tempCell).setFree();
            tempCell.removeAnimalFromCell(animal.getName());
            cells.add(tempCell);
        } else {
            System.out.println("Животного с таким именем не было найдено\n");
        }
    }

    private Cell getTempCell(Animal animal) {
        return cells
                .stream()
                .filter(x -> x.equals(Objects.requireNonNull(animal).getCell()))
                .findAny().orElse(null);
    }

    private Animal getAnimal(String name) {
        return this.animals
                .stream()
                .filter(x -> x.getName().equals(name))
                .findAny().orElse(null);
    }

    private boolean isPresent(String name) {
        return this.animals
                .stream()
                .anyMatch(x -> x.getName().equals(name));
    }

    public void showAnimalInCell(int numberOfCell) {
        Cell cellToShow = cells
                .stream()
                .filter(cell -> cell.getNumber() == numberOfCell)
                .findAny().orElse(null);
        Objects.requireNonNull(cellToShow).getAnimals().forEach(System.out::println);
    }

    public void showFreeCells(CellType cellType) {
        cells
                .stream()
                .filter(correct -> correct.getCellType() == cellType)
                .filter(Cell::isFree)
                .forEach(x -> System.out.println(x.getNumber() + "ая свободна\n"));
    }
}
