package chess.view;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {
    private static final int PIECE_NUMBER_FOR_A_LINE = 8;
    private static final int ADDITION_FOR_INDEX = 1;
    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.%n> 게임 시작 : start%n> 게임 종료 : end%n> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n> 점수 확인 : status%n";
    private static final String SCORE_PRINT_TEMPLATE = "%n> 점수%n> 백 : %.2f%n> 흑 : %.2f%n%n";
    private static final String GAME_FINISH_MESSAGE = "게임이 종료되었습니다";
    private static final String NEW_LINE = System.lineSeparator();
    private static final List<Position> POSITIONS_ORDERED;

    static {
        POSITIONS_ORDERED = Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> Position.of(file, rank)))
                .collect(Collectors.toList());
    }

    private OutputView() {
    }

    public static void startMessage() {
        System.out.printf(START_MESSAGE);
    }

    public static void printBoard(Map<Position, Piece> board) {
        for (int i = 0; i < POSITIONS_ORDERED.size(); i++) {
            System.out.print(PieceViewMapper.parse(board.get(POSITIONS_ORDERED.get(i))));
            addLineBreakEveryFileEnded(i);
        }
        System.out.println();
    }

    private static void addLineBreakEveryFileEnded(int number) {
        if ((number + ADDITION_FOR_INDEX) % PIECE_NUMBER_FOR_A_LINE == 0) {
            System.out.println();
        }
    }

    public static void printScore(Map<Color, Double> score) {
        System.out.printf(SCORE_PRINT_TEMPLATE, score.get(Color.WHITE), score.get(Color.BLACK));
    }

    public static void printFinished() {
        System.out.println(GAME_FINISH_MESSAGE);
    }

    public static void printError(Exception exception) {
        System.out.println(NEW_LINE + exception.getMessage() + NEW_LINE);
    }
}
