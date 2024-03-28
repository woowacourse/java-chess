package view;

import java.util.Arrays;
import java.util.List;
import util.FileConverter;
import util.RankConverter;

public enum GameCommand {
    START("start"), END("end"), MOVE("move");

    public static final int SOURCE = 1;
    public static final int FILE_INDEX = 0;
    public static final int RANK_INDEX = 1;
    public static final int DESTINATION = 2;
    private String command;

    GameCommand(String command) {
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
        char file = inputCommand.get(SOURCE)
                .charAt(FILE_INDEX);
        return FileConverter.from(file).getValue();
    }

    public static int toSourceRankValue(List<String> inputCommand) {
        char rank = inputCommand.get(SOURCE)
                .charAt(RANK_INDEX);
        return RankConverter.from(rank).getValue();
    }

    public static int toDestinationFileValue(List<String> inputCommand) {
        char file = inputCommand.get(DESTINATION)
                .charAt(FILE_INDEX);
        return FileConverter.from(file).getValue();
    }

    public static int toDestinationRankValue(List<String> inputCommand) {
        char rank = inputCommand.get(DESTINATION)
                .charAt(RANK_INDEX);
        return RankConverter.from(rank).getValue();
    }
}
