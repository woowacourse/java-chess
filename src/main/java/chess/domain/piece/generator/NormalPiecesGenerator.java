package chess.domain.piece.generator;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.fixedmovablepiece.King;
import chess.domain.piece.fixedmovablepiece.Knight;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.piece.straightmovablepiece.Bishop;
import chess.domain.piece.straightmovablepiece.Queen;
import chess.domain.piece.straightmovablepiece.Rook;
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
    }

    private static void createKing() {
        pieces.put(King.BLACK_INIT_LOCATION, new King(Color.BLACK));
        pieces.put(King.WHITE_INIT_LOCATION, new King(Color.WHITE));
    }

    private static void createQueen() {
        pieces.put(Queen.BLACK_INIT_LOCATION, new Queen(Color.BLACK));
        pieces.put(Queen.WHITE_INIT_LOCATION, new Queen(Color.WHITE));
    }

    private static void createRook() {
        for (Position position : Rook.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Rook(Color.BLACK));
        }

        for (Position position : Rook.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Rook(Color.WHITE));
        }
    }

    private static void createBishop() {
        for (Position position : Bishop.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Bishop(Color.BLACK));
        }

        for (Position position : Bishop.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Bishop(Color.WHITE));
        }
    }

    private static void createKnight() {
        for (Position position : Knight.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new Knight(Color.BLACK));
        }

        for (Position position : Knight.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new Knight(Color.WHITE));
        }
    }

    private static void createPawn() {
        for (Position position : BlackPawn.BLACK_INIT_LOCATIONS) {
            pieces.put(position, new BlackPawn());
        }

        for (Position position : WhitePawn.WHITE_INIT_LOCATIONS) {
            pieces.put(position, new WhitePawn());
        }
    }

    @Override
    public Map<Position, Piece> generate() {
        return new HashMap<>(pieces);
    }
}
