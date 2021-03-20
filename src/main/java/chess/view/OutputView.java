package chess.view;

import chess.domain.grid.Lines;

import java.util.stream.Collectors;

public class OutputView {
    public static void printChessInstruction() {
        System.out.println("체스 게임을 시작합니다");
        System.out.println("게임 시작 : start");
        System.out.println("게임 종료 : end");
        System.out.println("게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printGridStatus(final Lines lines) {
        System.out.println();
        String gridStatus =
                lines
                .lines()
                .stream()
                .map(line -> line
                        .pieces()
                        .stream()
                        .map(piece -> Character.toString(piece.name()))
                        .collect(Collectors.joining("", "", "  " + (8 - lines.lines().indexOf(line)))))
                .collect(Collectors.joining("\n"));
        System.out.println(gridStatus);
        System.out.println();
        System.out.println("abcdefgh");
        System.out.println();
    }

    public static void printScores(final boolean isBlack, final double calculateScore) {
        if (isBlack) {
            System.out.println("Black Score: " + calculateScore);
            return;
        }
        System.out.println("White Score: " + calculateScore);
    }

    public static void printWinner(boolean isBlackWinner) {
        if(isBlackWinner){
            System.out.println("Black 플레이어가 승리하였습니다.");
            return;
        }
        System.out.println("White 플레이어가 승리하였습니다.");
    }
}
