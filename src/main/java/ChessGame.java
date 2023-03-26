import domain.board.Board;
import domain.board.ChessAlignment;
import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;

import java.util.Map;

public final class ChessGame {
    private final Board board;

    private ChessGame(final Board board) {
        this.board = board;
    }

    public static ChessGame initGame(final ChessAlignment alignment) {
        return new ChessGame(Board.create(alignment));
    }

    public static ChessGame loadGame(Board board) {
        return new ChessGame(board);
    }

    public void move(Position source, Position destination) {
        board.move(source, destination);
    }

    public boolean winnerNotExist() {
        return board.isWhiteKingExist() && board.isBlackKingExist();
    }

    public Team getWinner() {
        return board.getWinner();
    }

    public Map<Position, Piece> getBoard() {
        return board.getPieces();
    }

    public double getBlackScore() {
        return board.getCurrentBlackScore();
    }

    public double getWhiteScore() {
        return board.getCurrentWhiteScore();
    }
}
