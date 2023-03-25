package chess.view;

import chess.domain.Color;
import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Piece;
import chess.dto.ScoreDto;

import java.util.Map;

public final class OutputView {
    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.print(
                "> 체스 게임을 시작합니다." + System.lineSeparator() +
                        "> 게임 시작 : start" + System.lineSeparator() +
                        "> 게임 종료 : end" + System.lineSeparator() +
                        "> 점수 계산 : status" + System.lineSeparator() +
                        "> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3" + System.lineSeparator()
        );
    }

    public static void printChessBoard(final Map<Position, Piece> board) {
        System.out.println();
        for (Column column : Column.values()) {
            printBoardRow(board, column);
        }

        String columnLabel = "abcdefgh";
        System.out.println(columnLabel);
    }

    private static void printBoardRow(final Map<Position, Piece> board, final Column column) {
        for (Row row : Row.values()) {
            System.out.print(PieceViewForm.parseToName(board.get(Position.of(row, column))));
        }
        System.out.println(" " + column.getType());
    }

    public static void printScore(ScoreDto scoreDto) {
        System.out.println();
        System.out.print(
                "> Black 점수 : " + scoreDto.getBlackScore() + System.lineSeparator() +
                        "> White 점수 : " + scoreDto.getWhiteScore() + System.lineSeparator()
        );
        if (scoreDto.getWinner() != Color.EMPTY) {
            System.out.printf("> %s 승리\n", ColorForm.convertToDisplayName(scoreDto.getWinner()));
        }
    }

    public static void printColor(Color color) {
        System.out.println();
        System.out.printf("> %s 턴\n", ColorForm.convertToDisplayName(color));
    }

    public static void printEnd() {
        System.out.println();
        System.out.println("> 게임 종료");
    }

    public static void printGameEnd() {
        System.out.println();
        System.out.println("게임이 종료되었습니다. start, status, end 명령어를 입력해주세요.");
    }

    public static void printExceptionMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
