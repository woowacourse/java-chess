package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public class PieceFactory {
    public static Piece create(String symbol, Position position) {
        if (symbol.equals("B")) {
            return new Bishop(Team.BLACK, position);
        }
        if (symbol.equals("b")) {
            return new Bishop(Team.WHITE, position);
        }
        if (symbol.equals("K")) {
            return new King(Team.BLACK, position);
        }
        if (symbol.equals("k")) {
            return new King(Team.WHITE, position);
        }
        if (symbol.equals("N")) {
            return new Knight(Team.BLACK, position);
        }
        if (symbol.equals("n")) {
            return new Knight(Team.WHITE, position);
        }
        if (symbol.equals("P")) {
            return new Pawn(Team.BLACK, position);
        }
        if (symbol.equals("p")) {
            return new Pawn(Team.WHITE, position);
        }
        if (symbol.equals("Q")) {
            return new Queen(Team.BLACK, position);
        }
        if (symbol.equals("q")) {
            return new Queen(Team.WHITE, position);
        }
        if (symbol.equals("R")) {
            return new Rook(Team.BLACK, position);
        }
        if (symbol.equals("r")) {
            return new Rook(Team.WHITE, position);
        }
        if (symbol.equals(".")) {
            return new Blank(Team.NONE, position);
        }
        throw new IllegalArgumentException("해당되는 말이 없습니다." + symbol);
    }
}
