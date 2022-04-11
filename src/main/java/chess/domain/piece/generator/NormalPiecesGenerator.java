package chess.domain.piece.generator;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Symbol;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class NormalPiecesGenerator implements PiecesGenerator {

    private static final Map<Position, Piece> pieces = new HashMap<>(32);

    static {
        createKing();
        createQueen();
        createRook();
        createBishop();
        createKnight();
        createPawn();
        PiecesGenerator.fillEmptyPiece(pieces);
    }

    private static void createKing() {
        pieces.put(King.BLACK_INIT_LOCATION, Piece.of(Color.BLACK, Symbol.KING));
        pieces.put(King.WHITE_INIT_LOCATION, Piece.of(Color.WHITE, Symbol.KING));
    }

    private static void createQueen() {
        pieces.put(Queen.BLACK_INIT_LOCATION, Piece.of(Color.BLACK, Symbol.QUEEN));
        pieces.put(Queen.WHITE_INIT_LOCATION, Piece.of(Color.WHITE, Symbol.QUEEN));
    }

    private static void createRook() {
        for (Position position : Rook.BLACK_INIT_LOCATIONS) {
            pieces.put(position, Piece.of(Color.BLACK, Symbol.ROOK));
        }

        for (Position position : Rook.WHITE_INIT_LOCATIONS) {
            pieces.put(position, Piece.of(Color.WHITE, Symbol.ROOK));
        }
    }

    private static void createBishop() {
        for (Position position : Bishop.BLACK_INIT_LOCATIONS) {
            pieces.put(position, Piece.of(Color.BLACK, Symbol.BISHOP));
        }

        for (Position position : Bishop.WHITE_INIT_LOCATIONS) {
            pieces.put(position, Piece.of(Color.WHITE, Symbol.BISHOP));
        }
    }

    private static void createKnight() {
        for (Position position : Knight.BLACK_INIT_LOCATIONS) {
            pieces.put(position, Piece.of(Color.BLACK, Symbol.KNIGHT));
        }

        for (Position position : Knight.WHITE_INIT_LOCATIONS) {
            pieces.put(position, Piece.of(Color.WHITE, Symbol.KNIGHT));
        }
    }

    private static void createPawn() {
        for (Position position : Position.getAllPositionsOfRow(Pawn.BLACK_INIT_ROW)) {
            pieces.put(position, Piece.of(Color.BLACK, Symbol.PAWN));
        }

        for (Position position : Position.getAllPositionsOfRow(Pawn.WHITE_INIT_ROW)) {
            pieces.put(position, Piece.of(Color.WHITE, Symbol.PAWN));
        }
    }

    @Override
    public Map<Position, Piece> generate() {
        return new HashMap<>(pieces);
    }
}
