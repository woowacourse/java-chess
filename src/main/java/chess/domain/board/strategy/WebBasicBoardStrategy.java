package chess.domain.board.strategy;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class WebBasicBoardStrategy implements BoardGenerationStrategy {

    private static final int START_BLANK_ROW = 3;
    private static final int END_BLANK_ROW = 7;
    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> create() {
        BoardGenerationStrategy strategy = new BasicBoardStrategy();
        board.putAll(strategy.create());
        putBlanks();
        return board;
    }

    private void putBlanks() {
        for (int i = START_BLANK_ROW; i < END_BLANK_ROW; i++) {
            putBlank(i);
        }
    }

    private void putBlank(int i) {
        for (Column column : Column.values()) {
            String position = column.getValue() + i;
            board.put(new Position(position), new Blank());
        }
    }
}
