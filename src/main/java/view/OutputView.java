package view;

import domain.Board;
import domain.piece.Position;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class OutputView {

    private OutputView() {
    }

    public static void showBoard(Board board) {
        AtomicInteger count = new AtomicInteger(0);
        IntStream.range(0, 8)
                .boxed()
                .flatMap(row -> IntStream.range(0, 8)
                        .mapToObj(column -> Position.valueOf(row, column)))
                .forEach(position -> {
                    System.out.print(board.getPiece(position).getName());
                    if (count.incrementAndGet() % 8 == 0) {
                        System.out.println();
                    }
                });
    }

    public static void showGuide() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void showCommandLine() {
        System.out.print("> ");
    }

    public static void showEndMessage() {
        System.out.println("체스가 종료됩니다.");
    }

    public static void alreadyStartGame() {
        System.out.println("게임이 이미 실행중입니다.");
    }

    public static void gameNotStart() {
        System.out.println("게임이 아직 실행되지 않았습니다.");
    }

    public static void showStatus(Map<String, Double> piecesScore) {
        for (String teamColor : piecesScore.keySet()) {
            System.out.println(teamColor + " : " + piecesScore.get(teamColor) + "점");
        }
    }
}
