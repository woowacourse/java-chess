package chess.view;

import chess.controller.GameCommand;
import chess.dto.ChessRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int COMMAND_INDEX = 0;
    private static final int SKIP_COMMAND_INDEX = 1;
    private static final String DELIMITER = " ";

    public ChessRequest readGameCommand() {
        List<String> list = Arrays.stream(scanner.nextLine().split(DELIMITER))
                .collect(Collectors.toList());
        return new ChessRequest(parseToCommand(list.get(COMMAND_INDEX)), list.subList(SKIP_COMMAND_INDEX, list.size()));
    }

    private GameCommand parseToCommand(String input) {
        if ("start".equalsIgnoreCase(input)) {
            return GameCommand.START;
        }
        if ("move".equalsIgnoreCase(input)) {
            return GameCommand.MOVE;
        }
        if ("end".equalsIgnoreCase(input)) {
            return GameCommand.END;
        }
        if ("status".equalsIgnoreCase(input)) {
            return GameCommand.STATUS;
        }
        if ("clear".equalsIgnoreCase(input)) {
            return GameCommand.CLEAR;
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력했습니다.");
    }
}
