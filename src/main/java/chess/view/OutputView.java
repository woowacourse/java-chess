package chess.view;

import chess.domain.location.Column;
import chess.domain.location.Location;
import chess.domain.location.Row;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {
    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(Map<Location, Piece> board) {
        Arrays.stream(Row.values()).sorted(Comparator.reverseOrder())
                .forEach(row -> {
                    printBoardRow(row, board);
                    System.out.println();
                });
    }

    private void printBoardRow(Row row, Map<Location, Piece> board) {
        for (Column column : Column.values()) {
            Location location = new Location(column, row);
            Piece piece = board.get(location);
            System.out.print(convertPieceToString(piece));
        }
    }

    private String convertPieceToString(Piece piece) {
        if (piece == null) {
            return ".";
        }
        String s = getPieceString(piece.getPieceType());
        if (piece.isBlack()) {
            return s.toUpperCase();
        }
        return s.toLowerCase();
    }

    private String getPieceString(PieceType type) {
        if (type == PieceType.KING) {
            return "k";
        }
        if (type == PieceType.QUEEN) {
            return "q";
        }
        if (type == PieceType.ROOK) {
            return "r";
        }
        if (type == PieceType.KNIGHT) {
            return "n";
        }
        if (type == PieceType.BISHOP) {
            return "b";
        }
        return "p";
    }

    public void printException(RuntimeException exception) {
        System.out.println(exception.getMessage());
    }
}
