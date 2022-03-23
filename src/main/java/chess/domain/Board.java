package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<List<Piece>> board;

    public Board() {
        board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(createRank(i));
        }
    }

    private List<Piece> createRank(int i) {
        if (i == 0) {
            return createFirstRank("White");
        }
        if (i == 7) {
            return createFirstRank("Black");
        }
        if (i == 1 || i == 6) {
            return createPawn(i);
        }
        return createBlank();
    }

    private List<Piece> createBlank() {
        List<Piece> pieces = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            pieces.add(new Blank("Black"));
        }
        return pieces;
    }

    private List<Piece> createPawn(int i) {
        List<Piece> pieces = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            pieces.add(new Pawn("Black"));
        }
        return pieces;
    }

    private List<Piece> createFirstRank(String team) {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Rook(team));
        pieces.add(new Knight(team));
        pieces.add(new Bishop(team));
        pieces.add(new Queen(team));
        pieces.add(new King(team));
        pieces.add(new Bishop(team));
        pieces.add(new Knight(team));
        pieces.add(new Rook(team));
        return pieces;
    }


    public Piece getPiece(String position) {
        int x = position.charAt(0) - 'a';
        int y = Character.getNumericValue(position.charAt(1)) - 1;
        return board.get(y).get(x);
    }
}
