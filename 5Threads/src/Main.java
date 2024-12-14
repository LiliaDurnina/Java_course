import java.util.Scanner;


public class Main {
    private static final int NUMBER_OF_ELEVATORS = 2;
    private static final int MAX_NUMBER_OF_FLOORS = 25;
    private static final int MIN_NUMBER_OF_FLOORS = 1;

    public static void main(String[] args) {

        Elevator[] elevators = new Elevator[NUMBER_OF_ELEVATORS];
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < NUMBER_OF_ELEVATORS; i++) {
            elevators[i] = new Elevator(i + 1);
            new Thread(elevators[i]).start();
        }

        while (true) {
            try {
                System.out.print("Введите через пробел 2 этажа: ОТКУДА вызываем лифт и КУДА едем" +
                        " или stop для завершения программы: ");
                String input = scan.nextLine();
                if ("stop".equals(input.toLowerCase())) {
                    System.out.println("Программа завершена");
                    break;
                }
                String[] parts = input.split(" ");


                if (parts.length != 2) {
                    throw new IllegalArgumentException("!Необходимо ввести ДВА этажа!");
                }

                int fromFloor = Integer.parseInt(parts[0]);
                int toFloor = Integer.parseInt(parts[1]);

                if (fromFloor < MIN_NUMBER_OF_FLOORS || toFloor < MIN_NUMBER_OF_FLOORS) {
                    throw new IllegalArgumentException("!Значение этажа меньше, чем минимальный этаж здания!");
                }
                if (fromFloor > MAX_NUMBER_OF_FLOORS || toFloor > MAX_NUMBER_OF_FLOORS) {
                    throw new IllegalArgumentException("!Значение этажа превышает высоту здания!");
                }

                Request request = new Request(fromFloor, toFloor);

                Elevator closestElevator = elevators[0];

                for (Elevator elevator : elevators) {
                    if (Math.abs(elevator.getCurrFloor() - fromFloor) < Math.abs(closestElevator.getCurrFloor() - fromFloor)) {
                        closestElevator = elevator;
                    }
                }
                closestElevator.requestElevator(request);

            } catch (NumberFormatException e) {
                System.err.println("Ошибка: !введите 2 ЧИСЛА через пробел!");
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }
}


