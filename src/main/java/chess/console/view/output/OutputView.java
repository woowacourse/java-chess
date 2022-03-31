package chess.console.view.output;

import static chess.console.domain.PositionRange.COLUMN_RANGE;
import static chess.console.domain.PositionRange.ROW_RANGE;

import java.util.Map;

import chess.console.domain.Position;

public class OutputView {

    private static final String CHESSBOARD_EMPTY_POSITION = ".";

    public void printGameCommandGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printChessBoard(final Map<Position, String> playerPieces) {
        for (final char row : ROW_RANGE.getReverseSortedAllValues()) {
            printChessBoardByRow(playerPieces, row);
            System.out.println();
        }
    }

    private void printChessBoardByRow(final Map<Position, String> playerPieces, final char row) {
        for (final char column : COLUMN_RANGE.getSortedAllValues()) {
            final Position position = new Position(column, row);
            System.out.print(piecePrintName(playerPieces, position));
        }
    }

    private String piecePrintName(final Map<Position, String> playerPieces, final Position position) {
        if (playerPieces.containsKey(position)) {
            return playerPieces.get(position);
        }
        return CHESSBOARD_EMPTY_POSITION;
    }

    public void printPlayerScores(final Map<String, Double> playerScores) {
        for (final String colorName : playerScores.keySet()) {
            printPlayerScore(colorName, playerScores.get(colorName));
        }
    }

    private void printPlayerScore(final String colorName, final double score) {
        System.out.println(colorName + "의 점수는 " + score + "입니다.");
    }

    public void printPromotionGuide() {
        System.out.println("폰이 프로모션에 도착했습니다. 프로모션을 진행해주세요.");
        System.out.println("Queen, Rook, Bishop, Knight 중 하나를 입력해주세요.");
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
