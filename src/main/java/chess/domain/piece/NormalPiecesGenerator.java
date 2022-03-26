package chess.domain.piece;

import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class NormalPiecesGenerator implements PiecesGenerator {

    public static final  Map<Position, Piece> pieces = new HashMap<>();

    static {
        createKing(pieces);
        createQueen(pieces);
        createRook(pieces);
        createBishop(pieces);
        createKnight(pieces);
        createPawn(pieces);
    }

    private static void createKing(Map<Position, Piece> pieces) {
        pieces.put(King.BLACK_INIT_LOCATION, new King(Color.BLACK));
        pieces.put(King.WHITE_INIT_LOCATION, new King(Color.WHITE));
    }

    private static void createQueen(Map<Position, Piece> pieces) {
        pieces.put(Queen.BLACK_INIT_LOCATION, new Queen(Color.BLACK));
        pieces.put(Queen.WHITE_INIT_LOCATION, new Queen(Color.WHITE));
    }

    private static void createRook(Map<Position, Piece> pieces) {
        for (Position position : Rook.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Rook(Color.BLACK));
        }

        for (Position position : Rook.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Rook(Color.WHITE));
        }
    }

    private static void createBishop(Map<Position, Piece> pieces) {
        for (Position position : Bishop.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Bishop(Color.BLACK));
        }

        for (Position position : Bishop.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Bishop(Color.WHITE));
        }
    }

    private static void createKnight(Map<Position, Piece> pieces) {
        for (Position position : Knight.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Knight(Color.BLACK));
        }

        for (Position position : Knight.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Knight(Color.WHITE));
        }
    }

    private static void createPawn(Map<Position, Piece> pieces) {
        for (Position position : Pawn.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Pawn(Color.BLACK));
        }

        for (Position position : Pawn.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Pawn(Color.WHITE));
        }
    }

    @Override
    public Map<Position, Piece> generate() {
        return new HashMap<>(pieces);
    }
}
