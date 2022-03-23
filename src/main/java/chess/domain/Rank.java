package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    List<Piece> pieces;

    private Rank(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public static Rank createBlank() {
        List<Piece> pieces = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            pieces.add(new Blank(Team.NONE));
        }
        return new Rank(pieces);
    }

    public static Rank createPawn(Team team) {
        List<Piece> pieces = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            pieces.add(new Pawn(team));
        }
        return new Rank(pieces);
    }

    public static Rank createFirstRank(Team team) {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Rook(team));
        pieces.add(new Knight(team));
        pieces.add(new Bishop(team));
        pieces.add(new Queen(team));
        pieces.add(new King(team));
        pieces.add(new Bishop(team));
        pieces.add(new Knight(team));
        pieces.add(new Rook(team));
        return new Rank(pieces);
    }

    public Piece getPiece(int position) {
        return pieces.get(position);
    }
}
