package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.piece.PieceType.*;

public class BoardInitializer {
    public static final Row WHITE_PAWNS_ROW = Row.of(2);
    public static final Row BLACK_PAWNS_ROW = Row.of(7);
    private static final List<PieceType> ORDER_OF_PIECES =
            Arrays.asList(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final Row WHITE_PIECES_ROW = Row.of(1);
    private static final Row BLACK_PIECES_ROW = Row.of(8);

    public static Map<Tile, Piece> initialize() {
        Map<Tile, Piece> boardState = new HashMap<>();
        placeBlackPieces(boardState);
        placeWhitePieces(boardState);
        return boardState;
    }

    private static void placeWhitePieces(Map<Tile, Piece> board) {
        placePieces(board, PieceColor.WHITE, WHITE_PIECES_ROW);
        placePawns(board, PieceColor.WHITE, WHITE_PAWNS_ROW);
    }

    private static void placeBlackPieces(Map<Tile, Piece> board) {
        placePieces(board, PieceColor.BLACK, BLACK_PIECES_ROW);
        placePawns(board, PieceColor.BLACK, BLACK_PAWNS_ROW);
    }

    private static void placePieces(Map<Tile, Piece> board, PieceColor color, Row row) {
        for (int i = 0; i < Row.MAX_ROW_SIZE; i++) {
            board.put(
                    Tile.of(Column.of((char) ('a' + i)), row),
                    ORDER_OF_PIECES.get(i).generate(color)
            );
        }
    }

    private static void placePawns(Map<Tile, Piece> board, PieceColor color, Row row) {
        Column.stream().forEach(column ->
                board.put(Tile.of(column, row), PieceType.PAWN.generate(color)));
    }
}