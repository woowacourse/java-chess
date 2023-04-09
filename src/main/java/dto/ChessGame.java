package dto;

import dao.Movement;
import domain.Board;
import domain.Turn;
import domain.piece.Piece;

import java.util.List;

public class ChessGame {
    private final String id;
    private final Board board;

    public ChessGame(String id, Board board) {
        this.id = id;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public List<List<Piece>> findCurrentStatus() {
        return board.findCurrentStatus();
    }

    public void move(Movement movement, Turn turn) {
        board.move(movement, turn);
    }
}
