package chess.view;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : start\n" +
            "> 게임 종료 : end\n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";

    public void printGameStartMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public void printBoard(Map<Position, Piece> board) {
        Arrays.stream(PositionY.values())
                .sorted(Comparator.reverseOrder())
                .forEach(positionY -> printEachRank(board, positionY));
        System.out.println();

        Arrays.stream(PositionX.values())
                .map(PositionX::getName)
                .forEach(System.out::print);
        System.out.println();
    }

    private void printEachRank(Map<Position, Piece> board, PositionY positionY) {
        Arrays.stream(PositionX.values())
                .map(positionX -> new Position(positionX, positionY))
                .map(position -> board.get(position).signature())
                .forEach(System.out::print);
        System.out.println("\t(" + positionY.name() + ")");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printStatus(Map<Color, Double> scores, Color winningColor) {
        for (Color color : scores.keySet()) {
            System.out.println(color.name() + ": " + scores.get(color) + "점");
        }

        if(winningColor == Color.NONE){
            System.out.println("동점입니다.");
            return;
        }
        System.out.println(winningColor.name()+"진영이 이기고 있습니다.");
        System.out.println();
    }
}
