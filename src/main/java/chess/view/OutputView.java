package chess.view;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

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
        for (int rank = 0; rank < 8; rank++) {
            for (int column = 0; column < 8; column++) {
                Position position = new Position(PositionX.of(column), PositionY.of(rank));
                System.out.print(board.get(position).signature());
            }
            System.out.println("\t(rank" + PositionY.of(rank).getName() + ")");
        }
        System.out.println();
        for (PositionX positionX : PositionX.values()) {
            System.out.print(positionX.getName());
        }
        System.out.println();
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
