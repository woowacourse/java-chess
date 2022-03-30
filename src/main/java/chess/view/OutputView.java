package chess.view;

import chess.domain.Score;
import chess.domain.piece.Piece;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import chess.domain.state.State;
import chess.domain.state.White;

import java.util.Map;

public class OutputView {

    private static final int BOARD_SIZE = 8;
    private static final String BOARD_BLANK_SYMBOL = ".";
    private static final String BLANK = "  ";
    private static final String RANK_INFORMATION = "abcdefgh";

    public static void printBoard(final Map<Position, Piece> cells) {
        for (int i = BOARD_SIZE; i >= 1; i--) {
            printRank(Rank.from(i), cells);
        }

        System.out.println();
        System.out.println(RANK_INFORMATION);
    }

    private static void printRank(final Rank rank, final Map<Position, Piece> cells) {
        for (int j = 1; j <= BOARD_SIZE; j++) {
            File file = File.from((j));
            Position position = new Position(file, rank);
            System.out.print(getBoardSymbol(position, cells));
        }

        System.out.print(BLANK + rank.getNumber());
        System.out.println();
    }

    private static String getBoardSymbol(final Position position, final Map<Position, Piece> cells) {
        if (cells.containsKey(position)) {
            Piece piece = cells.get(position);

            return piece.symbol();
        }

        return BOARD_BLANK_SYMBOL;
    }

    public static void printStatus(final Score score) {
        System.out.println("현재까지의 진행상황입니다.");
        printScore(score);
    }

    public static void printResult(final State state) {
        String winner = "백팀";
        if (state instanceof White) {
            winner = "흑팀";
        }

        System.out.println("승자: " + winner);
        printScore(state.status());
    }

    private static void printScore(final Score score) {
        System.out.println("백팀 점수: " + score.whiteScore());
        System.out.println("흑팀 점수: " + score.blackScore());
    }
}
