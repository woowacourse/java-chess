package chess.domain.command;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.utils.PieceInitializer;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum Command {
    START("start", Command::start),
    END("end", Command::end),
    MOVE("move", Command::move),
    STATUS("status", Command::status);

    private final String command;
    BiConsumer<ChessGame, Commands> function;

    Command(String command, BiConsumer<ChessGame, Commands> function) {
        this.command = command;
        this.function = function;
    }

    public static Command of(String input) {
        return Arrays.stream(values())
                .filter(value -> input.equals(value.command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 명령어 입니다."));
    }

    public void apply(ChessGame chessGame, Commands commands) {
        function.accept(chessGame, commands);
    }

    public boolean isStart() {
        return START.equals(this);
    }

    public boolean isStatus() {
        return STATUS.equals(this);
    }

    public boolean isMove() {
        return MOVE.equals(this);
    }

    private static void start(ChessGame chessGame, Commands commands) {
        chessGame.initBoard(Board.of(PieceInitializer.pieceInfo()));
    }

    private static void move(ChessGame chessGame, Commands commands) {
        if (chessGame.isReady() || chessGame.isEnd()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았거나, 종료되지 않았습니다.");
        }
        chessGame.moveAs(commands);
    }

    private static void status(ChessGame chessGame, Commands commands) {
        if (chessGame.isReady()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았습니다.");
        }
    }

    private static void end(ChessGame chessGame, Commands commands) {
        chessGame.endGame();
    }
}
