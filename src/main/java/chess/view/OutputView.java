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
    public static final String ONE_SQUARE = ".";

    public void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : " + InputView.START);
        System.out.println("> 게임 종료 : " + InputView.END);
        System.out.println("> 게임 이동 : " + InputView.MOVE + " source위치 target위치 - 예. " + InputView.MOVE + " b2 b3");
    }

    public void printBoard(Map<Location, Piece> board) {
        Arrays.stream(Row.values()).sorted(Comparator.reverseOrder())
                .forEach(row -> {
                    printBoardRow(row, board);
                    System.out.println();
                });
    }

    private void printBoardRow(Row row, Map<Location, Piece> board) {
        Arrays.stream(Column.values())
                .map(column -> new Location(column, row))
                .map(board::get)
                .map(this::convertPieceToString)
                .forEach(System.out::print);
    }

    private String convertPieceToString(Piece piece) {
        if (piece == null) {
            return ONE_SQUARE;
        }
        String pieceName = checkPieceName(piece);
        if (piece.isBlack()) {
            return pieceName.toUpperCase();
        }
        return pieceName.toLowerCase();
    }

    private String checkPieceName(Piece piece) {
        PieceType pieceType = piece.getPieceType();
        if (pieceType == PieceType.KING) {
            return "K";
        }
        if (pieceType == PieceType.QUEEN) {
            return "Q";
        }
        return checkMinionPieceName(pieceType);
    }

    private static String checkMinionPieceName(PieceType pieceType) {
        if (pieceType == PieceType.ROOK) {
            return "R";
        }
        if (pieceType == PieceType.BISHOP) {
            return "B";
        }
        if (pieceType == PieceType.KNIGHT) {
            return "N";
        }
        return "P";
    }

    public void printException(RuntimeException exception) {
        System.out.println(exception.getMessage());
    }
}
