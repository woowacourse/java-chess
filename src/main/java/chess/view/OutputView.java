package chess.view;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Piece;

import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.print(
                "> 체스 게임을 시작합니다." + System.lineSeparator() +
                        "> 게임 시작 : start" + System.lineSeparator() +
                        "> 게임 종료 : end" + System.lineSeparator() +
                        "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator()
        );
    }

    public static void printChessBoard(final Map<Position, Piece> board) {
        System.out.println();
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                System.out.print(PieceViewForm.parseToName(board.get(Position.of(row, column))));
            }
            System.out.println();
        }
    }

    public static void printExceptionMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
