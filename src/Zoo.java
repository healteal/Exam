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
        boolean isExist = cells
                .stream()
                .filter(Cell::isFree)
                .anyMatch(x -> x.getCellType() == animal.getTypeOfCell());
        if (isExist) {
            Cell temp = cells
                    .stream()
                    .filter(Cell::isFree)
                    .filter(x -> x.getCellType() == animal.getTypeOfCell())
                    .findAny().orElse(null);
            cells.remove(temp);
            animal.setCell(temp);
            this.animals.add(animal);
            Objects.requireNonNull(temp).addAnimal(animal);
            temp.setFree(false);
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

    public void removeAnimal(String name) {
        boolean isPresent = this.animals
                .stream()
                .anyMatch(x -> x.getName().equals(name));
        if (isPresent) {
            Animal animal = this.animals
                    .stream()
                    .filter(x -> x.getName().equals(name))
                    .findAny().orElse(null);
            Cell tempCell = cells
                    .stream()
                    .filter(x -> x.equals(Objects.requireNonNull(animal).getCell()))
                    .findAny().orElse(null);
            cells.remove(tempCell);
            animals.remove(animal);
            Objects.requireNonNull(tempCell).setFree(true);
            tempCell.removeAnimalFromCell(animal.getName());
            cells.add(tempCell);
        } else {
            System.out.println("Животного с таким именем не было найдено\n");
        }
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
