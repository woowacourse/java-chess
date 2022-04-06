package chess.controller;

import chess.domain.ChessGame;
import chess.view.Output;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (input, chessGame) -> {
        chessGame.start();
        Output.printBoard(chessGame.getBoard());
    }),
    MOVE("move", (input, chessGame) -> {
        chessGame.move(convertToMoveInput(input));
        Output.printBoard(chessGame.getBoard());
    }),
    STATUS("status", (input, chessGame) -> {
        chessGame.status();
        Output.printStatus(chessGame.status());
    }),
    END("end", (input, chessGame) -> chessGame.end()),
    ;

    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final int MOVE_INPUT_WORD_COUNT = 2;

    private final String value;
    private final BiConsumer<String, ChessGame> consumer;

    Command(final String value, final BiConsumer<String, ChessGame> consumer) {
        this.value = value;
        this.consumer = consumer;
    }

    public static void execute(final String input, final ChessGame chessGame) {
        Arrays.stream(Command.values())
                .filter(command -> input.contains(command.value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령어 입니다."))
                .consumer
                .accept(input, chessGame);
    }

    private static String[] convertToMoveInput(final String moveInput) {
        final var positions = moveInput.replaceAll(MOVE.value + BLANK, EMPTY).split(BLANK);
        checkMoveOrder(positions);
        return positions;
    }

    private static void checkMoveOrder(final String[] positions) {
        if (positions.length != MOVE_INPUT_WORD_COUNT) {
            throw new IllegalArgumentException("시작 위치와 도착 위치를 입력해주세요.");
        }
        if (Arrays.stream(positions).distinct().count() != MOVE_INPUT_WORD_COUNT) {
            throw new IllegalArgumentException("중복된 위치값은 사용될 수 없습니다.");
        }
    }
}
