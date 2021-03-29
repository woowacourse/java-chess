package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import chess.domain.piece.info.Score;
import chess.domain.piece.strategy.KnightMoveStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Knight extends Piece {
    private static final Score SCORE = new Score(2.5);
    private static final List<Position> INITIAL_BLACK_POSITIONS = Arrays.asList(Position.of('b', '8'),
            Position.of('g', '8'));
    private static final List<Position> INITIAL_WHITE_POSITIONS = Arrays.asList(Position.of('b', '1'),
            Position.of('g', '1'));

    public Knight(String name, Color color) {
        super(name, color, SCORE, new KnightMoveStrategy());
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return moveStrategy.canMove(source, target);
    }

    public static Map<Position, Knight> generate() {
        Map<Position, Knight> blackKnights = INITIAL_BLACK_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Knight("N", Color.BLACK)));
        Map<Position, Knight> whiteKnights = INITIAL_WHITE_POSITIONS.stream()
                .collect(Collectors.toMap(position -> position, position -> new Knight("n", Color.WHITE)));
        blackKnights.putAll(whiteKnights);
        return blackKnights;
    }
}
