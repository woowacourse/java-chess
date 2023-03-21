package chess.dto.response;

import chess.domain.piece.Piece;
import chess.controller.ColumnToNumber;
import chess.domain.position.Position;
import chess.controller.RowToNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessBoardDto {

    public static final String EMPTY_POSITION = ".";
    private final List<String> chessBoard;

    private ChessBoardDto(List<String> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoardDto of(Map<Position, Piece> chessBoard) {
        return new ChessBoardDto(getChessBoardForView(chessBoard));
    }

    public static List<String> getChessBoardForView(Map<Position, Piece> chessBoard) {
        List<String> chessBoardForView = new ArrayList<>();
        int columnCount = ColumnToNumber.values().length;
        int rowCount = RowToNumber.values().length;
        for (int row = rowCount; row >= 1; row--) {
            String oneRow = getOneRow(chessBoard, row, columnCount);
            chessBoardForView.add(oneRow);
        }
        return chessBoardForView;
    }

    private static String getOneRow(Map<Position, Piece> chessBoard, int row, int columnCount) {
        StringBuilder oneRow = new StringBuilder();
        for (int column = 1; column <= columnCount; column++) {
            oneRow.append(pieceNameOrDot(chessBoard, Position.of(row, column)));
        }
        return oneRow.toString();
    }

    private static String pieceNameOrDot(Map<Position, Piece> chessBoard, Position position) {
        if (chessBoard.containsKey(position)) {
            return chessBoard.get(position).getName();
        }
        return EMPTY_POSITION;
    }

    public List<String> getChessBoard() {
        return chessBoard;
    }
}
