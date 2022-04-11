package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public class PieceFactory {
    public static Piece create(String symbol) {
        if (symbol.equals("B")) {
            return new Bishop(Team.BLACK);
        }
        if (symbol.equals("b")) {
            return new Bishop(Team.WHITE);
        }
        if (symbol.equals("K")) {
            return new King(Team.BLACK);
        }
        if (symbol.equals("k")) {
            return new King(Team.WHITE);
        }
        if (symbol.equals("N")) {
            return new Knight(Team.BLACK);
        }
        if (symbol.equals("n")) {
            return new Knight(Team.WHITE);
        }
        if (symbol.equals("P")) {
            return new Pawn(Team.BLACK);
        }
        if (symbol.equals("p")) {
            return new Pawn(Team.WHITE);
        }
        if (symbol.equals("Q")) {
            return new Queen(Team.BLACK);
        }
        if (symbol.equals("q")) {
            return new Queen(Team.WHITE);
        }
        if (symbol.equals("R")) {
            return new Rook(Team.BLACK);
        }
        if (symbol.equals("r")) {
            return new Rook(Team.WHITE);
        }
        if (symbol.equals(".")) {
            return new Blank(Team.NONE);
        }
        throw new IllegalArgumentException("해당되는 말이 없습니다." + symbol);
    }
}
