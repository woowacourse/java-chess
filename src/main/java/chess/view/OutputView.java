package chess.view;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PieceDto;
import chess.dto.PieceType;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final List<Row> ROW_ORDER = List.of(
            Row.EIGHT, Row.SEVEN, Row.SIX, Row.FIVE, Row.FOUR, Row.THREE, Row.TWO, Row.ONE
    );
    private static final List<Column> COLUMN_ORDER = List.of(
            Column.A, Column.B, Column.C, Column.D, Column.E, Column.F, Column.G, Column.H
    );
    private static final Map<PieceType, String> PIECE_DISPLAY = Map.of(
            PieceType.KING, "K", PieceType.QUEEN, "Q", PieceType.KNIGHT, "N",
            PieceType.BISHOP, "B", PieceType.ROOK, "R", PieceType.PAWN, "P"
    );
    private static final String EMPTY_SPACE = ".";

    public void printStartGame() {
        System.out.println("""
                > 체스 게임을 시작합니다.
                > 게임 시작 : start
                > 게임 종료 : end
                > 게임 이동 : move source위치 target위치 - 예. move b2 b3""");
    }

    public void printBoard(Map<Position, PieceDto> board) {
        for (Row row : ROW_ORDER) {
            printBoardOneLine(board, row);
        }
        System.out.println();
    }

    private void printBoardOneLine(Map<Position, PieceDto> board, Row row) {
        for (Column column : COLUMN_ORDER) {
            PieceDto piece = board.get(new Position(row, column));
            printPiece(piece);
        }
        System.out.println();
    }

    private void printPiece(PieceDto piece) {
        if (piece == null) {
            System.out.print(EMPTY_SPACE);
            return;
        }
        if (piece.isBlack()) {
            printBlackPiece(piece.type());
            return;
        }
        printWhitePiece(piece.type());
    }

    private void printBlackPiece(PieceType type) {
        String display = PIECE_DISPLAY.get(type);
        System.out.print(display.toUpperCase());
    }

    private void printWhitePiece(PieceType type) {
        String display = PIECE_DISPLAY.get(type);
        System.out.print(display.toLowerCase());
    }
}
