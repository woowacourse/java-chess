package chess.domain.piece;

import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private final static Map<String, Function<Position, Piece>> blackPieceSupplier = new HashMap<>() {{
        put(PieceType.ROOK.getName(), Rook::createBlack);
        put(PieceType.KNIGHT.getName(), Knight::createBlack);
        put(PieceType.BISHOP.getName(), Bishop::createBlack);
        put(PieceType.QUEEN.getName(), Queen::createBlack);
        put(PieceType.KING.getName(), King::createBlack);
    }};

    private final static Map<String, Function<Position, Piece>> whitePieceSupplier = new HashMap<>() {{
        put(PieceType.ROOK.getName(), Rook::createWhite);
        put(PieceType.KNIGHT.getName(), Knight::createWhite);
        put(PieceType.BISHOP.getName(), Bishop::createWhite);
        put(PieceType.QUEEN.getName(), Queen::createWhite);
        put(PieceType.KING.getName(), King::createWhite);
    }};

    public static Piece createPiece(String team, String pieceType, Position position, boolean isFirstTurn) {
        if (pieceType.equals(PieceType.BLANK.getName())) {
            return new Blank(position);
        }
        if (pieceType.equals(PieceType.PAWN.getName())) {
            return findPawn(team, position, isFirstTurn);
        }
        if (team.equals(Team.BLACK.getName())) {
            return findBlackPiece(pieceType, position);
        }
        return findWhitePiece(pieceType, position);
    }

    private static Pawn findPawn(String team, Position position, boolean isFirstTurn) {
        if (team.equals(Team.BLACK.getName())) {
            return Pawn.createBlack(position, isFirstTurn);
        }
        return Pawn.createWhite(position, isFirstTurn);
    }

    private static Piece findBlackPiece(String pieceType, Position position) {
        return blackPieceSupplier.getOrDefault(pieceType, Blank::new)
                .apply(position);
    }

    private static Piece findWhitePiece(String pieceType, Position position) {
        return whitePieceSupplier.getOrDefault(pieceType, Blank::new)
                .apply(position);
    }
}
