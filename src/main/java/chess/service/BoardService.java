package chess.service;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.exceptions.InvalidInputException;

public class BoardService {
    private Board board;

    public BoardService(final Board board) {
        this.board = board;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    public boolean move(String from, String to) {
        try {
            board.move(Position.of(from), Position.of(to));
            return true;
        } catch (InvalidInputException e) {
            return false;
        }
    }
}
