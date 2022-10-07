import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class General {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static int choice;

    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(new Cell(CellType.SINGLE, 1));
        cells.add(new Cell(CellType.DOUBLE, 2));
        cells.add(new Cell(CellType.BIG_DOUBLE, 3));
        cells.add(new Cell(CellType.SINGLE, 4));
        Zoo zoo = new Zoo(animals, cells);
        while (true) {
            System.out.println(
                    "1. Завести животное в зоопарк\n" +
                            "2. Выселить животное\n" +
                            "3. Информация о животных по номеру клетки\n" +
                            "4. Поиск информации о свободных клетках по типу\n" +
                            "5. Выход");
            choice = inputInt();
            switch (choice) {
                case 1: {
                   zoo.addAnimal(createAnimal());
                    break;
                }

                case 2: {
                    System.out.println("Введите имя:");
                    try {
                        zoo.removeAnimal(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 3: {
                    System.out.println("Введите номер клетки:");
                    try {
                        zoo.showAnimalInCell(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 4: {
                    System.out.println("Выберите тип клетки: одиночная, двойная, тройная.");
                    CellType typeCell;
                    String cellType = inputString();
                    outPut:
                    while (true) {
                        switch (Objects.requireNonNull(cellType)) {
                            case "одиночная": {
                                typeCell = CellType.SINGLE;
                                break outPut;
                            }
                            case "двойная": {
                                typeCell = CellType.DOUBLE;
                                break outPut;
                            }
                            case "тройная": {
                                typeCell = CellType.BIG_DOUBLE;
                                break outPut;
                            }
                            default: {
                                System.out.println("Неверный ввод.");
                                break;
                            }
                        }
                    }
                    zoo.showFreeCells(typeCell);
                    break;
                }
                case 5: {
                    System.exit(1);
                }
                default: {
                    System.out.println("Неверный ввод");
                }
            }
        }
    }

    static int inputInt() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException | IOException e) {
                System.out.println("Неверный ввод");
            }
        }
        return choice;
    }

    static String inputString() {
        try {
            return reader.readLine();
        } catch (NullPointerException | IOException e) {
            return null;
        }
    }

    static Animal createAnimal() {
        System.out.println("Выберите тип животного: слон, медведь или лев");
        String type = inputString();
        AnimalType animalType;
        switch (Objects.requireNonNull(type)) {
            case "слон": {
                animalType = AnimalType.ELEPHANT;
                break;
            }
            case "медведь": {
                animalType = AnimalType.BEAR;
                break;
            }
            case "лев": {
                animalType = AnimalType.LION;
                break;
            }
            default: {
                System.out.println("Неверный ввод");
                return null;
            }
        }
        System.out.println("Введите имя животного:");
        String name = inputString();
        CellType cellTypeSet;
        String typeCell;
            System.out.println("Выберите тип клетки: одиночная, двойная, тройная.");
            typeCell = inputString();
            switch (Objects.requireNonNull(typeCell)) {
                case "одиночная": {
                    cellTypeSet = CellType.SINGLE;
                    break;
                }
                case "двойная": {
                    cellTypeSet = CellType.DOUBLE;
                    break;
                }
                case "тройная": {
                    cellTypeSet = CellType.BIG_DOUBLE;
                    break;
                }
                default: {
                    System.out.println("Неверный ввод");
                return null;
            }
        }
        return new Animal(animalType, name, cellTypeSet);
    }
}
