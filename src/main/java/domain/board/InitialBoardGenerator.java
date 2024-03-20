package domain.board;

import domain.piece.Bishop;
import domain.piece.Color;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
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
            board.put(new Position(new File(i), new Rank(2)), new Pawn(Color.WHITE));
            board.put(new Position(new File(i), new Rank(7)), new Pawn(Color.BLACK));
        }
    }

    private void placeRooks(Map<Position, Piece> board) {
        board.put(new Position(new File(1), new Rank(1)), new Rook(Color.WHITE));
        board.put(new Position(new File(8), new Rank(1)), new Rook(Color.WHITE));
        board.put(new Position(new File(1), new Rank(8)), new Rook(Color.BLACK));
        board.put(new Position(new File(8), new Rank(8)), new Rook(Color.BLACK));
    }

    private void placeKnights(Map<Position, Piece> board) {
        board.put(new Position(new File(2), new Rank(1)), new Knight(Color.WHITE));
        board.put(new Position(new File(7), new Rank(1)), new Knight(Color.WHITE));
        board.put(new Position(new File(2), new Rank(8)), new Knight(Color.BLACK));
        board.put(new Position(new File(7), new Rank(8)), new Knight(Color.BLACK));
    }

    private void placeBishops(Map<Position, Piece> board) {
        board.put(new Position(new File(3), new Rank(1)), new Bishop(Color.WHITE));
        board.put(new Position(new File(6), new Rank(1)), new Bishop(Color.WHITE));
        board.put(new Position(new File(3), new Rank(8)), new Bishop(Color.BLACK));
        board.put(new Position(new File(6), new Rank(8)), new Bishop(Color.BLACK));
    }

    private void placeQueens(Map<Position, Piece> board) {
        board.put(new Position(new File(4), new Rank(1)), new Queen(Color.WHITE));
        board.put(new Position(new File(4), new Rank(8)), new Queen(Color.BLACK));
    }

    private void placeKings(Map<Position, Piece> board) {
        board.put(new Position(new File(5), new Rank(8)), new Queen(Color.BLACK));
        board.put(new Position(new File(5), new Rank(1)), new Queen(Color.WHITE));
    }
}
