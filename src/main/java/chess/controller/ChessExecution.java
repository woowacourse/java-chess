package chess.controller;

import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

public enum ChessExecution {
    START("start", ChessExecution::start),
    MOVE("move", ChessExecution::move),
    STATUS("status", ChessExecution::status),
    END("end", ChessExecution::end),
    ;

    private static final String NO_COMMAND_FIND = "게임 실행중 명령어는 end만 입력할 수 있습니다.";
    private static final OutputView outputView = OutputView.getInstance();

    private final String value;
    private final BiConsumer<ChessGame, List<String>> consumer;

    ChessExecution(String value, BiConsumer<ChessGame, List<String>> consumer) {
        this.value = value;
        this.consumer = consumer;
    }

    private static void start(ChessGame chessGame, List<String> commands) {
        chessGame.start();
        outputView.printBoard(BoardDto.from(chessGame.getBoard()));
    }

    private static void move(ChessGame chessGame, List<String> commands) {
        chessGame.move(new MoveDto(commands.get(1), commands.get(2)));
        outputView.printBoard(BoardDto.from(chessGame.getBoard()));
    }

    private static void status(ChessGame chessGame, List<String> commands) {
        outputView.printScore(chessGame.status());
    }

    private static void end(ChessGame chessGame, List<String> commands) {
        outputView.printGameEnded(chessGame.status());
        chessGame.end();
    }

    public static ChessExecution from(String value) {
        return Arrays.stream(values())
                .filter(execution -> execution.value.equalsIgnoreCase(value.trim()))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(NO_COMMAND_FIND));
    }

    public void run(ChessGame chessGame, List<String> commands) {
        consumer.accept(chessGame, commands);
    }
}
