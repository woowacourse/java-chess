package chess.domain.piece;

import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class PieceFactory {

    private final static Map<String, BiFunction<Team, Position, Piece>> pieceSupplier = new HashMap<>() {{
        put(PieceType.ROOK.getName(), Rook::create);
        put(PieceType.KNIGHT.getName(), Knight::create);
        put(PieceType.BISHOP.getName(), Bishop::create);
        put(PieceType.QUEEN.getName(), Queen::create);
        put(PieceType.KING.getName(), King::create);
    }};

    public static Piece createPiece(String team, String pieceType, Position position, boolean isFirstTurn) {
        if (pieceType.equals(PieceType.BLANK.getName())) {
            return new Blank(position);
        }
        if (pieceType.equals(PieceType.PAWN.getName())) {
            return findPawn(team, position, isFirstTurn);
        }
        return pieceSupplier.getOrDefault(pieceType, (t, p) -> new Blank(p))
                .apply(Team.valueOf(team), position);
    }

    private static Pawn findPawn(String team, Position position, boolean isFirstTurn) {
        if (team.equals(Team.BLACK.name())) {
            return Pawn.createBlack(position, isFirstTurn);
        }
        return Pawn.createWhite(position, isFirstTurn);
    }
}
