package chess.view.input;

import chess.domain.game.ChessCommandType;
import chess.controller.dto.ChessCommandDto;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final String MOVE_DELIMITER = " ";
    private static final String MOVE_COMMAND_SIZE_ERROR = "이동 명령은 명령어를 포함하여 시작점과 도착점을 입력해야 합니다. 예. move b2 b3.";
    private static final Scanner scanner = new Scanner(System.in);


    public ChessCommandDto readCommands() {
        List<String> commands = Arrays.stream(scanner.nextLine().split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        ChessCommandType gameCommand = ChessCommandInput.from(commands.get(COMMAND_INDEX));
        if (gameCommand == ChessCommandType.MOVE) {
            return moveCommand(commands, gameCommand);
        }

        return ChessCommandDto.of(gameCommand);
    }

    private static ChessCommandDto moveCommand(List<String> commands, ChessCommandType gameCommand) {
        if (commands.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException(MOVE_COMMAND_SIZE_ERROR);
        }

        return ChessCommandDto.of(
                gameCommand,
                commands.get(FROM_POSITION_INDEX),
                commands.get(TO_POSITION_INDEX)
        );
    }
}
