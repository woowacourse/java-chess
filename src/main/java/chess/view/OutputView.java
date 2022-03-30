package chess.view;

import java.util.Map;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

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

    public static void printBoard(Map<Position, Piece> board) {
        for (int rank = 0; rank < 8; rank++) {
            printBoardInRank(board, rank);
            printPositionY(rank);
        }
        System.out.println();
        printPositionX();
        System.out.println();
    }

    private static void printBoardInRank(Map<Position, Piece> board, int rank) {
        for (int column = 0; column < 8; column++) {
            Position position = new Position(PositionX.of(column), PositionY.of(rank));
            System.out.print(board.get(position).signature());
        }
    }

    private static void printPositionY(int rank) {
        System.out.println("\t(rank" + PositionY.of(rank).getName() + ")");
    }

    private static void printPositionX() {
        for (PositionX positionX : PositionX.values()) {
            System.out.print(positionX.getName());
        }
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
