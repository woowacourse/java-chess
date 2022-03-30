package chess.domain;

public class ChessGame {
    private final ChessBoard chessBoard;

    private ChessGame() {
        this.chessBoard = ChessBoard.initialize();
    }

    public static ChessGame create() {
        return new ChessGame();
    }
}
