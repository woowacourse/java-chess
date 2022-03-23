package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Rank> board;

    public Board() {
        board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(createRank(i));
        }
    }

    private Rank createRank(int i) {
        if (i == 0) {
            return createFirstRank(Team.BLACK);
        }
        if (i == 1) {
            return createPawn(Team.BLACK);
        }
        if (i == 6) {
            return createPawn(Team.WHITE);
        }
        if (i == 7) {
            return createFirstRank(Team.WHITE);
        }
        return createBlank();
    }

    private Rank createBlank() {
        List<Piece> pieces = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            pieces.add(new Blank(Team.NONE));
        }
        return new Rank(pieces);
    }

    private Rank createPawn(Team team) {
        List<Piece> pieces = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            pieces.add(new Pawn(team));
        }
        return new Rank(pieces);
    }

    private Rank createFirstRank(Team team) {
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

    public Piece getPiece(String position) {
        int x = position.charAt(0) - 'a';
        int y = Character.getNumericValue(position.charAt(1)) - 1;
        return board.get(y).getPieces().get(x);
    }

    public List<Rank> getBoard() {
        return new ArrayList<>(board);
    }
}
