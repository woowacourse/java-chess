package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class TurnResult implements Result {

    private static final String BLANK = " ";
    private static final String COLUMN_INFO = "\n  a b c d e f g h\n";

    private final Board board;

    public TurnResult(final Board board) {
        this.board = board;
    }

    @Override
    public String infoAsString() {
        return renderBoard(board);
    }

    @Override
    public Map<Position, Piece> infoAsMap() {
        return board.coordiates();
    }

    private String renderBoard(final Board board) {
        final String visualBoard = Stream.of(Row.values())
                .map(index -> index.value() + BLANK + renderRow(board, index) + index.value())
                .collect(Collectors.joining(System.lineSeparator()))
                ;
        return COLUMN_INFO + visualBoard + COLUMN_INFO;
    }

    private String renderRow(final Board board, final Row row) {
        return Stream.of(Column.values())
                .map(column -> Position.of(column, row))
                .map(position -> renderPosition(board.pieceAt(position)))
                .collect(Collectors.joining())
                ;
    }

    private String renderPosition(final Piece piece) {
        return piece.getName() + BLANK;
    }
}
