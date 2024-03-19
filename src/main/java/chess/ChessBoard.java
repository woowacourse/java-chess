package chess;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static chess.ChessPiece.*;
import static chess.ChessPiece.BLACK_KNIGHT;

public class ChessBoard {
    private final Map<Column, List<ChessPiece>> chessBoard;

    private ChessBoard(Map<Column, List<ChessPiece>> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard initializeChessBoard() {
        Map<Column, List<ChessPiece>> board = new LinkedHashMap<>();
        board.put(Column.valueOf("8"), List.of(BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK));
        board.put(Column.valueOf("7"), List.of(BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN));
        board.put(Column.valueOf("6"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("5"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("4"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("3"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("2"), List.of(WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN));
        board.put(Column.valueOf("1"), List.of(WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK));

        return new ChessBoard(board);
    }

    public Map<Column, List<ChessPiece>> getChessBoard(){
        return Collections.unmodifiableMap(chessBoard);
    }
}
