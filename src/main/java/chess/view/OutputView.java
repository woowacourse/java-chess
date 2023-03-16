package chess.view;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.piece.Piece;
import chess.domain.Position;
import chess.domain.Rank;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printErrorMessage(final Exception e) {
        System.out.println(e.getMessage());
    }

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(final Board board) {
        final StringBuilder stringBuilder = new StringBuilder();
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
}
