package chess.view.input;

import chess.view.input.command.ChessCommand;
import chess.view.input.command.ChessCommandDto;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String MOVE_DELIMITER = " ";

    public ChessCommand readStartGame() {
        List<String> commands = Arrays.stream(scanner.nextLine().split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        return ChessCommand.getStart(commands.get(COMMAND_INDEX));
    }


    public ChessCommandDto readRunningCommand() {
        List<String> commands = Arrays.stream(scanner.nextLine().split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        ChessCommand chessCommand = ChessCommand.getPlayingCommand(commands.get(COMMAND_INDEX));
        if (chessCommand.isEnd()) {
            return ChessCommandDto.of(chessCommand);
        }

        return ChessCommandDto.of(
                chessCommand,
                commands.get(FROM_POSITION_INDEX),
                commands.get(TO_POSITION_INDEX)
        );
    }
}
