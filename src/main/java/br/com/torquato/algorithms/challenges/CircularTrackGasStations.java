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
            for (int stationIndex = 0; stationIndex < input.numberOfGasStations; stationIndex++) {
                int currentPosition = stationIndex + 1;

                Integer gasAvailable = input.gallonsPerStation.get(stationIndex);
                Integer gasNeeded = input.gallonsNeededToMoveClockwise.get(stationIndex);
                int gasDiff = gasAvailable - gasNeeded;
                totalGasAvailable += gasDiff;
                // Can start from here because we have gas to go to the next clockwise station
                // and there is no previous possible position
                if (gasDiff >= 0 && possiblePosition == -1) {
                    possiblePosition = currentPosition;
                }
                boolean isLast = currentPosition == input.numberOfGasStations;
                boolean isPrevious = possiblePosition < currentPosition;
                // gas was depleted, clear previous possible position because it will
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
                5, //N
                List.of(1, 1, 1, 1, 1), //p
                List.of(1, 1, 2, 1, 1) //q
        ); //T
        Input case2 = new Input(
                7, //N
                List.of(1, 1, 1, 10, 1, 1, 1), //p
                List.of(2, 2, 2, 2, 2, 2, 2) //q
        ); //T
        //T = 2
        new CircularTrackGasStations(List.of(case1, case2)).start();
    }

}
