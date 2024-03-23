package domain.board;

import domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class InitialBoardGenerator implements BoardGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        placePawns(board);
        placeRooks(board);
        placeKnights(board);
        placeBishops(board);
        placeQueens(board);
        placeKings(board);
        return board;
    }

    private void placePawns(Map<Position, Piece> board) {
        for (int i = 1; i <= 8; i++) {
            board.put(Position.of(i, 2), new Pawn(Color.WHITE));
            board.put(Position.of(i, 7), new Pawn(Color.BLACK));
        }
    }

    private void placeRooks(Map<Position, Piece> board) {
        board.put(Position.of(1, 1), new Rook(Color.WHITE));
        board.put(Position.of(8, 1), new Rook(Color.WHITE));
        board.put(Position.of(1, 8), new Rook(Color.BLACK));
        board.put(Position.of(8, 8), new Rook(Color.BLACK));
    }

    private void placeKnights(Map<Position, Piece> board) {
        board.put(Position.of(2, 1), new Knight(Color.WHITE));
        board.put(Position.of(7, 1), new Knight(Color.WHITE));
        board.put(Position.of(2, 8), new Knight(Color.BLACK));
        board.put(Position.of(7, 8), new Knight(Color.BLACK));
    }

    private void placeBishops(Map<Position, Piece> board) {
        board.put(Position.of(3, 1), new Bishop(Color.WHITE));
        board.put(Position.of(6, 1), new Bishop(Color.WHITE));
        board.put(Position.of(3, 8), new Bishop(Color.BLACK));
        board.put(Position.of(6, 8), new Bishop(Color.BLACK));
    }

    private void placeQueens(Map<Position, Piece> board) {
        board.put(Position.of(4, 1), new Queen(Color.WHITE));
        board.put(Position.of(4, 8), new Queen(Color.BLACK));
    }

    private void placeKings(Map<Position, Piece> board) {
        board.put(Position.of(5, 8), new King(Color.BLACK));
        board.put(Position.of(5, 1), new King(Color.WHITE));
    }
}
