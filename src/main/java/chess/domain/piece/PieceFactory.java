package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PieceFactory {

    private final static Map<String, Function<Position, Piece>> blackPieceSupplier = new HashMap<>() {{
        put("pawn", Pawn::createBlack);
        put("rook", Rook::createBlack);
        put("knight", Knight::createBlack);
        put("bishop", Bishop::createBlack);
        put("queen", Queen::createBlack);
        put("king", King::createBlack);
    }};

    private final static Map<String, Function<Position, Piece>> whitePieceSupplier = new HashMap<>() {{
        put("pawn", Pawn::createWhite);
        put("rook", Rook::createWhite);
        put("knight", Knight::createWhite);
        put("bishop", Bishop::createWhite);
        put("queen", Queen::createWhite);
        put("king", King::createWhite);
    }};

    public static Piece createPiece(String team, String pieceType, Position position) {
        if (pieceType.equals("blank")) {
            return new Blank(position);
        }
        if (team.equals("black")) {
            return findBlackPiece(pieceType, position);
        }
        return findWhitePiece(pieceType, position);
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
