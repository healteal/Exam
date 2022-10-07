public class Animal {
    private final AnimalType animalType;
    private final String name;
    private final CellType typeOfCell;
    private Cell cell;

    public Animal(AnimalType animalType, String name, CellType typeOfCells) {
        this.animalType = animalType;
        this.name = name;
        this.typeOfCell = typeOfCells;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public String getName() {
        return name;
    }

    public CellType getTypeOfCell() {
        return typeOfCell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Тип животного: " + animalType +
                ", имя: " + name  +
                ", тип клетки: " + typeOfCell +
                ", номер клетки:" + cell.getNumber();
    }
}
