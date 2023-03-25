package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public static void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage() + LINE_SEPARATOR);
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printBoard(final Board board) {
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

    public static void printScore(final Double whiteScore, final Double blackScore) {
        System.out.println(String.format("White팀 점수: %.1f점", whiteScore));
        System.out.println(String.format("Black팀 점수: %.1f점", blackScore));
    }

    public static void printWinner(Side side) {
        if (side.isWhite()) {
            System.out.println("White팀 승리!!");
            return;
        }
        if (side.isBlack()) {
            System.out.println("Black팀 승리!!");
            return;
        }
        System.out.println("Draw!!");
    }
}
