package domain;

import domain.pieceType.Bishop;
import domain.pieceType.Knight;
import domain.pieceType.Pawn;
import domain.pieceType.Piece;
import domain.pieceType.Queen;
import domain.pieceType.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessTable {

    private static final Map<File, Piece> BLACK_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Color.BLACK),
            File.B, new Knight(Color.BLACK),
            File.C, new Bishop(Color.BLACK),
            File.D, new Queen(Color.BLACK),
            File.E, new Knight(Color.BLACK),
            File.F, new Bishop(Color.BLACK),
            File.G, new Knight(Color.BLACK),
            File.H, new Rook(Color.BLACK)
    );
    private static final Map<File, Piece> WHITE_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Color.WHITE),
            File.B, new Knight(Color.WHITE),
            File.C, new Bishop(Color.WHITE),
            File.D, new Queen(Color.WHITE),
            File.E, new Knight(Color.WHITE),
            File.F, new Bishop(Color.WHITE),
            File.G, new Knight(Color.WHITE),
            File.H, new Rook(Color.WHITE)
    );

    private final Map<Square, Piece> pieceContainer;

    public ChessTable() {
        this.pieceContainer = new HashMap<>();
    }

    public ChessTable(final Map<Square, Piece> pieceContainer) {
        this.pieceContainer = pieceContainer;
    }

    public static ChessTable create() {
        final Map<Square, Piece> chessTable = new HashMap<>();

        for (final File file : File.values()) {
            chessTable.put(new Square(Rank.SEVEN, file), new Pawn(Color.BLACK));
            chessTable.put(new Square(Rank.TWO, file), new Pawn(Color.WHITE));
        }

        for (final File file : File.values()) {
            chessTable.put(new Square(Rank.EIGHT, file), BLACK_PIECE_TYPE_ORDERS.get(file));
            chessTable.put(new Square(Rank.ONE, file), WHITE_PIECE_TYPE_ORDERS.get(file));

        }

        return new ChessTable(chessTable);
    }

    public Map<Square, Piece> getPieceContainer() {
        return Collections.unmodifiableMap(pieceContainer);

//        return pieceContainer.entrySet().stream()
//                .collect(Collectors.toMap(Entry::getKey,
//                        entry -> new PieceInfo(entry.getValue().getPieceType(), entry.getValue().getColor())));
    }

    public void move(final String source, final String target) {

    }
}
