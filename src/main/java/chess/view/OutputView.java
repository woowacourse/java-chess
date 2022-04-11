package chess.view;

import java.util.Map;

import chess.domain.Color;
import chess.domain.board.LineNumber;
import chess.domain.board.Point;
import chess.domain.piece.Piece;

public class OutputView {

    public void printIntroduction() {
        System.out.println("> 체스 게임을 시작합니다.\n"
            + "> 게임 시작 : start\n"
            + "> 게임 종료 : end\n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3\n"
            + "> 점수 확인 : status");
    }

    public void printBoard(Map<Point, Piece> pointPieces, String turnColor) {
        System.out.println(toBoardString(pointPieces));
        System.out.println(turnColor + "의 차례입니다.");
    }

    private String toBoardString(Map<Point, Piece> pointPieces) {
        StringBuilder builder = new StringBuilder();
        for (int verticalIndex = LineNumber.MAX; verticalIndex >= LineNumber.MIN; verticalIndex--) {
            builder.append(toLine(pointPieces, verticalIndex)).append("\n");
        }
        return builder.toString();
    }

    private String toLine(Map<Point, Piece> pointPieces, int verticalIndex) {
        StringBuilder builder = new StringBuilder();
        for (int horizontalIndex = LineNumber.MIN; horizontalIndex <= LineNumber.MAX; horizontalIndex++) {
            builder.append(
                PieceRepresentation.convertType(pointPieces.get(Point.of(horizontalIndex, verticalIndex))));
        }
        return builder.toString();
    }

    public void printScore(Map<Color, Double> colorScores) {
        colorScores.entrySet()
            .forEach(this::printScorePerColor);
        System.out.println("현재 승부 결과는 " + findWinner(colorScores) + " 입니다.");
    }

    private String findWinner(Map<Color, Double> colorScores) {
        if (colorScores.get(Color.WHITE) > colorScores.get(Color.BLACK)) {
            return "WHITE WIN";
        }
        if (colorScores.get(Color.WHITE) < colorScores.get(Color.BLACK)) {
            return "BLACK WIN";
        }
        return "TIE";
    }

    private void printScorePerColor(Map.Entry<Color, Double> colorScore) {
        System.out.println(colorScore.getKey() + "점수는 " + colorScore.getValue() + " 입니다.");
    }

    public void printException(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printEnd() {
        System.out.println("게임이 종료되었습니다.");
    }
}
