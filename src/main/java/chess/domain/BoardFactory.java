package chess.domain;

import java.util.LinkedHashMap;

public class BoardFactory {
    private static final LinkedHashMap<Position, Piece> INITIALIZED_POSITIONS = new LinkedHashMap<>();
    private static final int WHITE_EDGE_ROW = 1;
    private static final int BLACK_EDGE_ROW = 8;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLACK_PAWN_ROW = 7;
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 8;
    private static final int BLANK_START_INDEX = 3;
    private static final int BLACK_END_INDEX = 6;

    static {
        setUpBlackPiece();
        setUpBlank();
        setUpWhitePiece();
    }

    private static void setUpBlackPiece() {
        setUpEdge(BlackPiece::new, BLACK_EDGE_ROW);
        for (int x = START_INDEX; x <= END_INDEX; x++) {
            INITIALIZED_POSITIONS.put(new Position(x, BLACK_PAWN_ROW), new BlackPiece(PieceType.PAWN));
        }
    }

    private static void setUpBlank() {
        for (int y = BLANK_START_INDEX; y <= BLACK_END_INDEX; y++) {
            for (int x = START_INDEX; x <= END_INDEX; x++) {
                INITIALIZED_POSITIONS.put(new Position(x, y), new Blank(PieceType.BLANK));
            }
        }
    }

    private static void setUpWhitePiece() {
        for (int x = START_INDEX; x <= END_INDEX; x++) {
            INITIALIZED_POSITIONS.put(new Position(x, WHITE_PAWN_ROW), new WhitePiece(PieceType.PAWN));
        }
        setUpEdge(WhitePiece::new, WHITE_EDGE_ROW);
    }

    private static void setUpEdge(PieceCreator pieceCreator, int edgeRow) {
        INITIALIZED_POSITIONS.put(new Position(1, edgeRow), pieceCreator.create(PieceType.ROOK));
        INITIALIZED_POSITIONS.put(new Position(2, edgeRow), pieceCreator.create(PieceType.KNIGHT));
        INITIALIZED_POSITIONS.put(new Position(3, edgeRow), pieceCreator.create(PieceType.BISHOP));
        INITIALIZED_POSITIONS.put(new Position(4, edgeRow), pieceCreator.create(PieceType.QUEEN));
        INITIALIZED_POSITIONS.put(new Position(5, edgeRow), pieceCreator.create(PieceType.KING));
        INITIALIZED_POSITIONS.put(new Position(6, edgeRow), pieceCreator.create(PieceType.BISHOP));
        INITIALIZED_POSITIONS.put(new Position(7, edgeRow), pieceCreator.create(PieceType.KNIGHT));
        INITIALIZED_POSITIONS.put(new Position(8, edgeRow), pieceCreator.create(PieceType.ROOK));
    }

    private interface PieceCreator {
        Piece create(PieceType pieceType);
    }

    public static Board createBoard() {
        return new Board(INITIALIZED_POSITIONS);
    }
}
