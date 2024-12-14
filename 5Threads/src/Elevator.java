
import java.util.concurrent.*;

class Elevator implements Runnable {
    private static final int TIME_TO_MOVE = 2000;
    private int currFloor;
    private final int Id;
    private final ConcurrentLinkedQueue<Request> requests;

    public Elevator(int elevatorId) {
        this.Id = elevatorId;
        this.currFloor = 0;
        this.requests = new ConcurrentLinkedQueue<>();
    }

    public void requestElevator(Request request) {
        requests.add(request);
        System.out.println("Лифт№" + Id + " вызван на " + request.getFrom() + " этаж");
    }


    private void movingToFloor(int targetFloor) {
        System.out.println("Лифт№" + Id + " планирует двигаться с " + currFloor + " этажа на " + targetFloor + " этаж");
        while (currFloor != targetFloor) {
            if (currFloor < targetFloor) {
                currFloor += 1;
            } else {
                currFloor -= 1;
            }
            System.out.println("Лифт№" + Id + " на " + currFloor + " этаже");
            try {
                Thread.sleep(TIME_TO_MOVE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            checkingIntermediateRequests();
        }
    }

    private void checkingIntermediateRequests() {
        for (Request request : requests) {
            if (request.getFrom() == currFloor) {
                takePassenger(request);
                requests.remove(request);


            }

        }
    }
    private void takePassenger(Request request) {
        System.out.println("Лифт№" + Id + " забрал пассажира с " + request.getFrom()+" этажа");
    }

    public int getCurrFloor() {
        return currFloor;
    }

    @Override
    public void run() {
        while (true) {
            Request request = requests.poll();
            if (request != null) {
                movingToFloor(request.getFrom());
                takePassenger(request);
                movingToFloor(request.getTo());
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }


        }



    }


}