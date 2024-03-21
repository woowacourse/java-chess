package view;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    START("start"), END("end"), MOVE("move");

    private String command;

    private GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand from(List<String> inputCommand) {
        String command = inputCommand.get(0);
        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력하신 Command가 없습니다."));
    }

    public static int toSourceFileValue(List<String> inputCommand) {
        char file = inputCommand.get(1)
                .charAt(0);
        return Character.getNumericValue(file - 48);
    }

    public static int toSourceRankValue(List<String> inputCommand) {
        char rank = inputCommand.get(1)
                .charAt(1);
        return Character.getNumericValue(rank);
    }

    public static int toDestinationFileValue(List<String> inputCommand) {
        char file = inputCommand.get(2)
                .charAt(0);
        return Character.getNumericValue(file - 48);
    }

    public static int toDestinationRankValue(List<String> inputCommand) {
        char rank = inputCommand.get(2)
                .charAt(1);
        return Character.getNumericValue(rank);
    }
}
