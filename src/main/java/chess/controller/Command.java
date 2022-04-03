package chess.controller;

import chess.domain.ChessGame;
import chess.dto.BoardDto;
import chess.view.Output;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (commands, chessGame) -> chessGame.start()),
    END("end", (commands, chessGame) -> chessGame.end()),
    MOVE("move", (commands, chessGame) -> {
        checkMoveCommandLength(commands);
        chessGame.move(commands);
    }),
    STATUS("status", (commands, chessGame) -> chessGame.status(Output::printScore)),
    ;

    private static final int COMMAND_LOCATION = 0;
    private static final int MOVE_COMMAND_LENGTH = 3;
    private static final String DELIMITER = " ";

    private final String command;
    private final BiConsumer<String[], ChessGame> operate;

    Command(final String command, final BiConsumer<String[], ChessGame> operate) {
        this.command = command;
        this.operate = operate;
    }

    public static void execute(final String input, final ChessGame chessGame) {
        String[] commands = input.split(DELIMITER);
        operate(chessGame, commands);
        printBoard(chessGame);
    }

    private static void operate(final ChessGame chessGame, final String[] commands) {
        Arrays.stream(Command.values())
                .filter(command -> commands[COMMAND_LOCATION].equals(command.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어 입니다."))
                .operate
                .accept(commands, chessGame);
    }

    private static void printBoard(final ChessGame chessGame) {
        if (!chessGame.isEnded()) {
            Output.printBoard(BoardDto.from(chessGame.getBoard()));
        }
    }

    private static void checkMoveCommandLength(final String[] commands) {
        if (commands.length != MOVE_COMMAND_LENGTH) {
            throw new IllegalArgumentException("'move source위치 target위치' 의 형식으로 입력해주세요.");
        }
    }
}
