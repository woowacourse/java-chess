package chess.console.controller;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.Color;
import chess.console.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Command {

    START("start", (chessGame, arguments) -> {
        chessGame.start();
        Board board = chessGame.board();
        OutputView.printChessBoard(board.getValue());
    }),

    END("end", (chessGame, arguments) -> {
        chessGame.end();
    }),

    MOVE("move", (chessGame, arguments) -> {
        chessGame.move(arguments.get(0), arguments.get(1));
        Board board = chessGame.board();
        OutputView.printChessBoard(board.getValue());
    }),

    STATUS("status", (chessGame, arguments) -> {
        double whiteScore = chessGame.score(Color.WHITE);
        double blackScore = chessGame.score(Color.BLACK);
        OutputView.printStatusMessage(whiteScore, blackScore);
    });

    private final String type;
    private final BiConsumer<ChessGame, List<String>> consumer;

    Command(String type, BiConsumer<ChessGame, List<String>> consumer) {
        this.type = type;
        this.consumer = consumer;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(command -> input.equals(command.type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 명령입니다."));
    }

    public void accept(ChessGame chessGame, String source, String target) {
        consumer.accept(chessGame, List.of(source, target));
    }
}
