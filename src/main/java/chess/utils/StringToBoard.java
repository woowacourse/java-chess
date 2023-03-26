package chess.utils;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StringToBoard {

    private static final char EMPTY_POSITION = '.';

    private StringToBoard() {
    }

    public static Map<Position, Piece> convert(List<String> chessBoardText) {
        return getChessBoardFromString(chessBoardText);
    }

    private static Map<Position, Piece> getChessBoardFromString(List<String> chessBoardText) {
        Map<Position, Piece> chessBoard = new HashMap<>();
        int rowCount = chessBoardText.size();
        for (int row = rowCount; row >= 1; row--) {
            String targetRow = chessBoardText.get(chessBoardText.size() - row);
            chessBoard.putAll(getOneRowChessBoard(targetRow, row));
        }
        return chessBoard;
    }

    private static Map<Position, Piece> getOneRowChessBoard(String targetRow, int row) {
        Map<Position, Piece> oneRowChessBoard = new HashMap<>();
        for (int column = 1, columnCount = targetRow.length(); column <= columnCount; column++) {
            Position position = Position.of(row, column);
            char pieceName = targetRow.charAt(column - 1);
            mapPositionAndPiece(oneRowChessBoard, position, pieceName);
        }
        return oneRowChessBoard;
    }

    private static void mapPositionAndPiece(Map<Position, Piece> oneRowChessBoard, Position position, char pieceName) {
        if (pieceName == EMPTY_POSITION) {
            return;
        }
        Piece piece = CharToPiece.of(pieceName);
        oneRowChessBoard.put(position, piece);
    }
}
