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

    public static ChessGame initGame(ChessAlignment alignment) {
        return new ChessGame(Board.create(alignment));
    }

    public void move(Position source, Position destination) {
        board.move(source, destination);
    }

    public boolean isWin() {
        return !board.isWhiteKingExist() || !board.isBlackKingExist();
    }

    public Team getWinner() {
        if (!isWin()) {
            return Team.NOTHING;
        }

        if (board.isBlackKingExist()) {
            return Team.BLACK;
        }
        return Team.WHITE;
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
