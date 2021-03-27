package chess.domain;

import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum Commands {

    START("start", (chessBoard, list) -> {
        OutputView.printBoard(chessBoard.getChessBoard());
    }),
    MOVE("move", (chessBoard, list) -> {
        chessBoard.move(list.get(1), list.get(2));
        OutputView.printBoard(chessBoard.getChessBoard());
    }),
    STATUS("status", (chessBoard, list) -> {
        OutputView.printScore(chessBoard.result());
    }),
    END("end",
        (chessBoard, strings) -> chessBoard.terminate()
    );

    private final String command;
    private final BiConsumer<ChessBoard, List<String>> consumer;

    Commands(String command, BiConsumer<ChessBoard, List<String>> consumer) {
        this.command = command;
        this.consumer = consumer;
    }

    public static void run(ChessBoard chessBoard, List<String> value) {
        Arrays.stream(Commands.values())
            .filter(command -> command.command.equals(value.get(0)))
            .forEach(command -> command.runCommand(chessBoard, value));
    }

    public void runCommand(ChessBoard chessBoard, List<String> input) {
        consumer.accept(chessBoard, input);
    }
}
