package chess.domain.board;

import chess.domain.*;

import java.util.*;

import static chess.domain.PieceType.*;

public class BoardInitializer {
    private static final List<PieceType> ORDER_OF_PIECES =
            Arrays.asList(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
    private static final Row WHITE_PIECES_ROW = Row.of(1);
    private static final Row WHITE_PAWNS_ROW = Row.of(2);
    private static final Row BLACK_PAWNS_ROW = Row.of(7);
    private static final Row BLACK_PIECES_ROW = Row.of(8);

    public static Map<Tile, ChessPiece> initialize() {
        Map<Tile, ChessPiece> boardState = new HashMap<>();
        placeBlackPieces(boardState);
        placeWhitePieces(boardState);
        return boardState;
    }

    private static void placeWhitePieces(Map<Tile, ChessPiece> board) {
        placePieces(board, PieceColor.WHITE, WHITE_PIECES_ROW);
        placePawns(board, PieceColor.WHITE, WHITE_PAWNS_ROW);
    }

    private static void placeBlackPieces(Map<Tile, ChessPiece> board) {
        placePieces(board, PieceColor.BLACK, BLACK_PIECES_ROW);
        placePawns(board, PieceColor.BLACK, BLACK_PAWNS_ROW);
    }

    private static void placePieces(Map<Tile, ChessPiece> board, PieceColor color, Row row) {
        for (int i = 0; i < 8; i++) {
            board.put(
                    Tile.of(Column.of((char) ('a' + i)), row),
                    new ChessPiece(color, ORDER_OF_PIECES.get(i))
            );
        }
    }

    private static void placePawns(Map<Tile, ChessPiece> board, PieceColor color, Row row) {
        Column.stream().forEach(column ->
                board.put(Tile.of(column, row), new ChessPiece(color, PAWN)));
    }
}