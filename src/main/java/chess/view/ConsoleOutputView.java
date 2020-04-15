package chess.view;

import chess.Scores;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import chess.position.Rank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleOutputView {

    private static final String GAME_INTRO_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_COMMAND_INFORMATION_MESSAGE = "> 게임 시작 : start";
    private static final String END_COMMAND_INFORMATION_MESSAGE = "> 게임 종료 : end";
    private static final String MOVE_COMMAND_INFORMATION_MESSAGE = "> 말 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String WHITE_SCORE_MESSAGE_FORMAT = "백팀 점수 : %,.1f";
    private static final String BLACK_SCORE_MESSAGE_FORMAT = "흑팀 점수 : %,.1f";

    public static void printGameIntro() {
        System.out.println(GAME_INTRO_MESSAGE);
        System.out.println(START_COMMAND_INFORMATION_MESSAGE);
        System.out.println(END_COMMAND_INFORMATION_MESSAGE);
        System.out.println(MOVE_COMMAND_INFORMATION_MESSAGE);
    }

    public static void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public static void printBoard(Map<Position, Piece> pieces) {
        StringBuilder builder = new StringBuilder();
        for (Rank rank : reverseOrderOfRankValuesExceptNone()) {
            builder.append(informationOf(pieces, rank));
        }
        System.out.println(builder);
    }

    public static List<Rank> reverseOrderOfRankValuesExceptNone() {
        return Arrays.stream(Rank.valuesExceptNone())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static StringBuilder informationOf(Map<Position, Piece> pieces, Rank rank) {
        StringBuilder builder = new StringBuilder();
        for (File file : File.valuesExceptNone()) {
            builder.append(pieces.get(Position.of(file, rank)).getSymbol());
        }
        builder.append("\n");
        return builder;
    }

    public static void printScores(Scores scores) {
        System.out.println(String.format(WHITE_SCORE_MESSAGE_FORMAT, scores.getWhiteScore()));
        System.out.println(String.format(BLACK_SCORE_MESSAGE_FORMAT, scores.getBlackScore()));
    }
}
