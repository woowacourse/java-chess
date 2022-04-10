package domain;

import chess.domain.board.BoardGenerator;
import chess.domain.piece.unit.Piece;
import chess.domain.position.Position;
import chess.domain.position.XPosition;
import chess.domain.position.YPosition;
import java.util.HashMap;
import java.util.Map;

public final class CustomBoardGenerator implements BoardGenerator {

    private Map<Position, Piece> board;

    public CustomBoardGenerator() {
        board = new HashMap<>();
        createInitialize();
    }

    @Override
    public Map<Position, Piece> generate() {
        return this.board;
    }

    private void createInitialize() {
        for (XPosition xPosition : XPosition.values()) {
            initializeByRow(xPosition);
        }
    }

    private void initializeByRow(final XPosition xPosition) {
        for (YPosition yPosition : YPosition.values()) {
            board.put(Position.of(xPosition, yPosition), null);
        }
    }

    public void add(Position position, Piece piece) {
        board.put(position, piece);
    }
}
