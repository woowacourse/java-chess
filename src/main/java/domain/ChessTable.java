package domain;

import domain.pieceType.Bishop;
import domain.pieceType.King;
import domain.pieceType.Knight;
import domain.pieceType.Pawn;
import domain.pieceType.Piece;
import domain.pieceType.Queen;
import domain.pieceType.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessTable {

    private static final Map<File, Piece> BLACK_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Color.BLACK),
            File.B, new Knight(Color.BLACK),
            File.C, new Bishop(Color.BLACK),
            File.D, new Queen(Color.BLACK),
            File.E, new King(Color.BLACK),
            File.F, new Bishop(Color.BLACK),
            File.G, new Knight(Color.BLACK),
            File.H, new Rook(Color.BLACK)
    );
    private static final Map<File, Piece> WHITE_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Color.WHITE),
            File.B, new Knight(Color.WHITE),
            File.C, new Bishop(Color.WHITE),
            File.D, new Queen(Color.WHITE),
            File.E, new King(Color.WHITE),
            File.F, new Bishop(Color.WHITE),
            File.G, new Knight(Color.WHITE),
            File.H, new Rook(Color.WHITE)
    );

    private final Map<Square, Piece> pieceSquares;
    private Color color;

    public ChessTable() {
        this.pieceSquares = new HashMap<>();
    }

    public ChessTable(final Map<Square, Piece> pieceSquares) {
        this.pieceSquares = pieceSquares;
        this.color = Color.WHITE;
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

    public void move(final Square source, final Square target) {
        if (!pieceSquares.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }

        final Piece sourcePiece = pieceSquares.get(source);

        if (sourcePiece.getColor() != color) {
            throw new IllegalArgumentException("자기 말이 아닙니다.");
        }

        if (pieceSquares.containsKey(target)) {
            final Piece targetPiece = pieceSquares.get(target);
            if (targetPiece.getColor() == sourcePiece.getColor()) {
                throw new IllegalArgumentException("갈 수 없는 경로입니다.");
            }

        }
        final Piece piece = pieceSquares.get(source);
        final List<Square> path = piece.calculatePath(source, target);

        if (path.isEmpty()) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다.");
        }
        for (final Square square : path) {
            if (!square.equals(target) && pieceSquares.containsKey(square)) {
                throw new IllegalArgumentException("갈 수 없는 경로입니다.");
            }
        }

        pieceSquares.put(target, sourcePiece);
        pieceSquares.remove(source);
        color = color.toggle();
    }

    public Map<Square, Piece> getPieceSquares() {
        return Collections.unmodifiableMap(pieceSquares);
    }
}
