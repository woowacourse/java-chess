package chess.view;

import chess.model.GameCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ANNOUNCE_START_FORMAT = "> ";
    private static final String GAME_START_EXAMPLE = "게임 시작 : start";
    private static final String NEW_LINE = "\n";
    private static final String GAME_END_EXAMPLE = "게임 종료 : end";
    private static final String GAME_MOVE_EXAMPLE = "게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String MOVE_DELIMITER = " ";
    private static final String WRONG_COMMAND_ERROR_MESSAGE_FORMAT = "잘못된 게임 커맨드입니다. %s";

    public static List<String> inputStartOrEndGame() {
        System.out.println(ANNOUNCE_START_FORMAT + GAME_START_EXAMPLE + NEW_LINE
                + ANNOUNCE_START_FORMAT + GAME_END_EXAMPLE + NEW_LINE
                + ANNOUNCE_START_FORMAT + GAME_MOVE_EXAMPLE);
        String command = SCANNER.nextLine();
        checkCommandValue(command);
        return new ArrayList<>(Arrays.asList(command.split(MOVE_DELIMITER)));
    }

    private static void checkCommandValue(final String inputCommand) {
        if (inputWrongCommand(inputCommand)) {
            throw new IllegalArgumentException(String.format(WRONG_COMMAND_ERROR_MESSAGE_FORMAT, inputCommand));
        }
    }

    private static boolean inputWrongCommand(String inputCommand) {
        return Arrays.stream(GameCommand.values())
                .noneMatch(command -> command.name().toLowerCase().equals(inputCommand));
    }
}
