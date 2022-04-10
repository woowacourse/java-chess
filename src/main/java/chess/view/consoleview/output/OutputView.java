package chess.view.consoleview.output;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class OutputView {

    private static final String EMPTY_SYMBOL = ".";
    private static final String SCORE_MESSAGE_FORMAT = "whiteScore : %.1f | blackScore : %.1f%n";

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.printf("> 체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n");
    }

    public static void printCurrentBoard(final Map<Position, Piece> pieces) {
        final List<Rank> reverseOrderRanks = getReverseOrderRanks();
        for (Rank rank : reverseOrderRanks) {
            printCurrentBoard(pieces, rank);
            System.out.println();
        }
        System.out.println();
    }

    private static List<Rank> getReverseOrderRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static void printCurrentBoard(final Map<Position, Piece> board, final Rank rank) {
        for (File file : File.values()) {
            final Piece piece = board.get(Position.of(file, rank));
            System.out.print(getSymbol(piece));
        }
    }

    private static String getSymbol(final Piece piece) {
        if (piece == null) {
            return EMPTY_SYMBOL;
        }
        return piece.getSymbol();
    }

    public static void printScore(double whiteTeamScore, double blackTeamScore) {
        System.out.printf(SCORE_MESSAGE_FORMAT, whiteTeamScore, blackTeamScore);
    }
}
