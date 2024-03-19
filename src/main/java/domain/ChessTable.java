package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessTable {

    private final Map<Square, Piece> pieceContainer;

    public ChessTable(final Map<Square, Piece> pieceContainer) {
        this.pieceContainer = pieceContainer;
    }

    public static ChessTable create() {
        final List<PieceType> pieceTypeOrders = List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP,
                PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);

        final Map<Square, Piece> chessTable = new HashMap<>();

        for (int file = 1; file <= 8; file++) {
            chessTable.put(new Square(7, file),
                    new Piece(PieceType.PAWN, Color.BLACK));
            chessTable.put(new Square(2, file),
                    new Piece(PieceType.PAWN, Color.WHITE));
            chessTable.put(new Square(8, file), new Piece(pieceTypeOrders.get(file - 1), Color.BLACK));
            chessTable.put(new Square(1, file), new Piece(pieceTypeOrders.get(file - 1), Color.WHITE));
        }
        return new ChessTable(chessTable);
    }

    public Map<Square, Piece> getPieceContainer() {
        return Collections.unmodifiableMap(pieceContainer);
    }
}
