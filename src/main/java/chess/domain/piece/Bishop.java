package chess.domain.piece;

import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.BishopMoveStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Bishop extends Piece {
    private static final Score SCORE = new Score(3);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('c', '8'),
            Position.of('f', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('c', '1'),
            Position.of('f', '1'));

    public Bishop(String name, Color color) {
        super(name, color, SCORE, new BishopMoveStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }

    public static Map<Position, Bishop> generate() {
        Map<Position, Bishop> blackBishops = INITIAL_BLACK_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Bishop("B", Color.BLACK)));
        Map<Position, Bishop> whiteBishops = INITIAL_WHITE_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Bishop("b", Color.WHITE)));
        blackBishops.putAll(whiteBishops);
        return blackBishops;
    }
}
