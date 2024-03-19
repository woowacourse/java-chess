package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessTable {

    private static final Map<File, PieceType> PIECE_TYPE_ORDERS = Map.of(
            File.A, PieceType.ROOK,
            File.B, PieceType.KNIGHT,
            File.C, PieceType.BISHOP,
            File.D, PieceType.QUEEN,
            File.E, PieceType.KING,
            File.F, PieceType.BISHOP,
            File.G, PieceType.KNIGHT,
            File.H, PieceType.ROOK
    );

    private final Map<Square, Piece> pieceContainer;

    public ChessTable(final Map<Square, Piece> pieceContainer) {
        this.pieceContainer = pieceContainer;
    }

    public static ChessTable create() {
        final Map<Square, Piece> chessTable = new HashMap<>();

        for (final File file : File.values()) {
            chessTable.put(new Square(Rank.SEVEN, file), new Piece(PieceType.PAWN, Color.BLACK));
            chessTable.put(new Square(Rank.TWO, file), new Piece(PieceType.PAWN, Color.WHITE));
            chessTable.put(new Square(Rank.EIGHT, file), new Piece(PIECE_TYPE_ORDERS.get(file), Color.BLACK));
            chessTable.put(new Square(Rank.ONE, file), new Piece(PIECE_TYPE_ORDERS.get(file), Color.WHITE));
        }

        return new ChessTable(chessTable);
    }

    public Map<Square, Piece> getPieceContainer() {
        return Collections.unmodifiableMap(pieceContainer);
    }
}
