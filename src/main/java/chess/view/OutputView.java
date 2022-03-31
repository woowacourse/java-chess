package chess.view;

import java.util.Map;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Column;
import chess.domain.position.Row;

public class OutputView {

    private OutputView() {
    }

    public static void printGameStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
    }

    public static void printPlayingCommandMessage() {
        System.out.println("> 게임 이동 : move source 위치 target 위치 (예. move b2 b3)");
        System.out.println("> 게임 현황 : status");
        System.out.println("> 게임 종료 : end");
    }

    public static void printBoard(Map<Position, Piece> pieces) {
        System.out.println("------------------------");
        for (int rank = 0; rank < 8; rank++) {
            printBoardOnRow(pieces, rank);
            printRank(rank);
        }
        printFile();
        System.out.println("------------------------");
    }

    private static void printBoardOnRow(Map<Position, Piece> pieces, int rank) {
        for (int column = 0; column < 8; column++) {
            Position position = new Position(Column.of(column), Row.of(rank));
            System.out.print(makeSignature(pieces, position));
        }
    }

    private static String makeSignature(Map<Position, Piece> pieces, Position position) {
        if (!pieces.containsKey(position)) {
            return ".";
        }
        return pieces.get(position).signature();
    }

    private static void printRank(int rank) {
        System.out.println("\t(rank" + Row.of(rank).getName() + ")");
    }

    private static void printFile() {
        System.out.println();
        for (Column column : Column.values()) {
            System.out.print(column.getName());
        }
        System.out.println();
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStatus(Map<Color, Double> scores, Color winningColor) {
        for (Color color : scores.keySet()) {
            System.out.println(color.name() + ": " + scores.get(color) + "점");
        }
        if (winningColor == Color.NONE) {
            System.out.println("동점입니다.");
            return;
        }
        System.out.println(winningColor.name() + " 진영이 이기고 있습니다.");
        System.out.println();
    }
}
