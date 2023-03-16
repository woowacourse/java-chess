package chess.domain;

import java.util.Objects;

public class ChessGame {

    private final ChessBoard chessBoard;

    private ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessGame startNewGame(final String command) {
        validateStartCommand(command);
        ChessBoard chessBoard = ChessBoardFactory.create();
        return new ChessGame(chessBoard);
    }

    private static void validateStartCommand(final String command) {
        if (Objects.isNull(command)||!command.equals("start")) {
            throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
