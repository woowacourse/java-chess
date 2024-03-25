package model;

import java.util.Map;
import model.piece.Piece;
import model.position.Moving;
import model.position.Position;

public class ChessGame {

    private static final Camp STARTING_CAMP = Camp.WHITE;

    private final Board board;
    private Camp camp;

    private ChessGame(final Board board) {
        this.board = board;
        this.camp = STARTING_CAMP;
    }

    public static ChessGame setupStartingPosition() {
        return new ChessGame(Board.create());
    }

    public void move(final Moving moving) {
        board.validate(moving, camp);
        board.move(moving);
        camp = camp.toggle();
    }

    public Map<Position, Piece> getBoard() {
        return board.getPieces();
    }

    public Camp getCamp() {
        return camp;
    }
}
