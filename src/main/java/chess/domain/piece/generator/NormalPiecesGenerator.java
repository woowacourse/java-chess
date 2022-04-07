package chess.domain.piece.generator;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class NormalPiecesGenerator implements PiecesGenerator {

    private static final int PIECES_NUM = 32;

    private static final Map<Position, Piece> pieces = new HashMap<>(PIECES_NUM);

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
        for (Column column : Column.values()) {
            pieces.put(Position.of(column, Pawn.BLACK_INIT_ROW), new Pawn(Color.BLACK));
            pieces.put(Position.of(column, Pawn.WHITE_INIT_ROW), new Pawn(Color.WHITE));
        }
    }

    @Override
    public Map<Position, Piece> generate() {
        return new HashMap<>(pieces);
    }
}
