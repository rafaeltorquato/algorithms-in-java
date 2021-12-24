package br.com.torquato.algorithms.challenges;

import java.util.List;

import static java.lang.System.out;

public class CircularTrackGasStations {

    private final List<Input> inputs;

    public CircularTrackGasStations(List<Input> inputs) {
        this.inputs = inputs;
    }

    public void start() {
        for (int inputIndex = 0; inputIndex < inputs.size(); inputIndex++) {
            Input input = inputs.get(inputIndex);
            int possiblePosition = -1;
            int totalGasAvailable = 0;
            for (int i = 0; i < input.numberOfGasStations; i++) {
                int currentPosition = i + 1;

                Integer gasAvailable = input.gallonsPerStation.get(i);
                Integer gasNeeded = input.gallonsNeededToMoveClockwise.get(i);
                int gasDiff = gasAvailable - gasNeeded;
                totalGasAvailable += gasDiff;
                // Can start from here because there is gas to go to next clockwise station
                // and there is no previous possible position
                if (gasDiff >= 0 && possiblePosition == -1) {
                    possiblePosition = currentPosition;
                }
                boolean isLast = currentPosition == input.numberOfGasStations;
                boolean isPrevious = possiblePosition < currentPosition;
                // gas out, clear before possible position because previous index will
                // stop here without gas or if it is the last position and there is no gas
                if (totalGasAvailable < 0 && (isLast || isPrevious)) {
                    possiblePosition = -1;
                }
            }
            int inputNumber = inputIndex + 1;
            if (possiblePosition == -1) {
                out.println("Case " + inputNumber + ": Not Possible");
            } else {
                out.println("Case " + inputNumber + ": Possible from station " + possiblePosition);
            }
        }

    }

    public record Input(
            Integer numberOfGasStations, //N
            List<Integer> gallonsPerStation, //p
            List<Integer> gallonsNeededToMoveClockwise //q
    ) {}

    public static void main(String[] args) {

        Input case1 = new Input(
                5,
                List.of(1, 1, 1, 1, 1),
                List.of(1, 1, 2, 1, 1)
        ); //T
        Input case2 = new Input(
                7,
                List.of(1, 1, 1, 10, 1, 1, 1),
                List.of(2, 2, 2, 2, 2, 2, 2)
        ); //T
        //T = 2
        new CircularTrackGasStations(List.of(case1, case2)).start();
    }

}
