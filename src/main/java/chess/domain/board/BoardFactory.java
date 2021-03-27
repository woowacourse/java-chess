package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BoardFactory {

    private static final Row MIN_ROW = Row.ONE;
    private static final Row MAX_ROW = Row.EIGHT;
    private static final Row BLACK_PAWN_ROW = Row.SEVEN;
    private static final Row WHITE_PAWN_ROW = Row.TWO;

    public static Board initializeBoard() {
        return initializePieces();
    }

    private static Board initializePieces() {
        Map<Piece, Position> coordinates = new HashMap<>();
        initializeSpecialPiecesByRow(coordinates, MIN_ROW, PieceColor.WHITE);
        initializeSpecialPiecesByRow(coordinates, MAX_ROW, PieceColor.BLACK);

        initializePawnPieces(coordinates);
        return new Board(coordinates);
    }

    private static void initializePawnPieces(Map<Piece, Position> coordinates) {
        Arrays.stream(Column.values()).forEach(column -> {
            coordinates.put(new Pawn(PieceColor.WHITE), Position.of(column, WHITE_PAWN_ROW));
            coordinates.put(new Pawn(PieceColor.BLACK), Position.of(column, BLACK_PAWN_ROW));
        });
    }

    private static void initializeSpecialPiecesByRow(Map<Piece, Position> coordinates, Row row,
        PieceColor color) {
        Map<Column, Piece> pieces = createSpecialPieces(color);
        Arrays.stream(Column.values()).forEach(column -> {
            coordinates.put(pieces.get(column), Position.of(column, row));
        });
    }

    private static Map<Column, Piece> createSpecialPieces(PieceColor color) {
        Map<Column, Piece> pieces = new LinkedHashMap<>();
        pieces.put(Column.A, new Rook(color));
        pieces.put(Column.B, new Knight(color));
        pieces.put(Column.C, new Bishop(color));
        pieces.put(Column.D, new Queen(color));
        pieces.put(Column.E, new King(color));
        pieces.put(Column.F, new Bishop(color));
        pieces.put(Column.G, new Knight(color));
        pieces.put(Column.H, new Rook(color));
        return pieces;
    }
}
