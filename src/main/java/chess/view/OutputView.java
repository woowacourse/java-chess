package chess.view;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OutputView {

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialMessage() {
        System.out.println(
                "> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 - 예. move b2 b3"
        );
    }

    public static void printBoard(Board board) {
        for (Rank rank: Rank.asListInReverseOrder()) {
            String line = Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .map(board::findByPosition)
                    .map(Square::getNotation)
                    .collect(Collectors.joining());
            System.out.println(line);
        }
    }
}
