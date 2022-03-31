package chess.view.output;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;

public class OutputView {

    private static final String EMPTY_SYMBOL = ".";
    private static final String SCORE_MESSAGE_FORMAT = "whiteScore : %f | blackScore : %f%n";

    private OutputView() {
        throw new AssertionError();
    }

    public static void printStartMessage() {
        System.out.printf("> 체스 게임을 시작합니다.%n"
                + "> 게임 시작 : start%n"
                + "> 게임 종료 : end%n"
                + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n"
                + "> 점수 출력 : status%n");
    }

    public static void printCurrentBoard(final Board board) {
        final List<Rank> reverseOrderRanks = getReverseOrderRanks();
        for (Rank rank : reverseOrderRanks) {
            printCurrentBoard(board, rank);
            System.out.println();
        }
    }

    private static List<Rank> getReverseOrderRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private static void printCurrentBoard(final Board board, final Rank rank) {
        for (File file : File.values()) {
            System.out.print(getSymbolOfPosition(board, Position.of(file, rank)));
        }
    }

    private static String getSymbolOfPosition(final Board board, final Position position) {
        if (board.hasPieceInPosition(position)) {
            final Piece piece = board.findPieceInPosition(position);
            return PieceSymbol.findSymbol(piece);
        }
        return EMPTY_SYMBOL;
    }

    public static void printScore(double whiteTeamScore, double blackTeamScore) {
        System.out.printf(SCORE_MESSAGE_FORMAT, whiteTeamScore, blackTeamScore);
    }
}
