package chess.view;

import chess.domain.Board;
import chess.domain.Piece;
import chess.domain.Position;
import java.util.Map;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(final Board board) {
        final StringBuilder stringBuilder = new StringBuilder();
        final Map<Position, Piece> boardMap = board.getBoard();

        for (int rank = Board.LOWER_BOUNDARY; rank <= Board.UPPER_BOUNDARY; rank++) {
            for (int file = Board.LOWER_BOUNDARY; file <= Board.UPPER_BOUNDARY; file++) {
                final Piece piece = boardMap.get(Position.of(rank, file));
                stringBuilder.append(piece.getName());
            }
            stringBuilder.append(LINE_SEPARATOR);
        }

        System.out.println(stringBuilder);
    }
}
