package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final int WHITE_SCORE_INDEX = 0;
    private static final int BLACK_SCORE_INDEX = 1;

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage() + LINE_SEPARATOR);
    }

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(final Board board) {
        final StringBuilder stringBuilder = new StringBuilder(LINE_SEPARATOR);
        final Map<Position, Piece> boardMap = board.getBoard();

        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                final Piece piece = boardMap.get(Position.of(file, rank));
                stringBuilder.append(piece.getName());
            }
            stringBuilder.append(LINE_SEPARATOR);
        }

        System.out.println(stringBuilder);
    }

    public static void printResult(final List<Double> calculateScore) {
        final Double whiteScore = calculateScore.get(WHITE_SCORE_INDEX);
        final Double blackScore = calculateScore.get(BLACK_SCORE_INDEX);
        System.out.println(String.format("White팀 점수: %.1f점", whiteScore));
        System.out.println(String.format("Black팀 점수: %.1f점", blackScore));
        printWinner(whiteScore, blackScore);

    }

    private static void printWinner(final Double whiteScore, final Double blackScore) {
        if (whiteScore < blackScore) {
            System.out.println("White팀 승리!!");
            return;
        }
        if (blackScore < whiteScore) {
            System.out.println("white팀 승리!!");
            return;
        }
        System.out.println("Draw!!");
    }
}
