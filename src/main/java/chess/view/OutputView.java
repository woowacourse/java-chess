package chess.view;

import chess.domain.piece.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(Map<Position, String> pieceNames) {
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);

        for (Rank rank : ranks) {
            printPieceName(pieceNames, rank);
            System.out.println();
        }
        System.out.println();
    }

    private static void printPieceName(Map<Position, String> pieceNames, Rank rank) {
        for (File file : File.values()) {
            String name = pieceNames.get(Position.of(file, rank));
            System.out.print(name);
        }
    }

    public static void printScores(Map<Color, Double> scores) {
        System.out.println("각 팀의 점수");
        for (Color color : scores.keySet()) {
            System.out.println(color.getName() + " : " + scores.get(color));
        }
    }
}
