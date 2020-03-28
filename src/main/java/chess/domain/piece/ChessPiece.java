package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static chess.domain.piece.Direction.*;

public enum ChessPiece {

    KING("k", Collections.singletonList(Position.of(Column.E, Row.ONE)),
            new OrdinaryMovement(Arrays.asList(N, NE, E, SE, S, SW, W, NW), 1), 0),
    QUEEN("q", Collections.singletonList(Position.of(Column.D, Row.ONE)),
            new OrdinaryMovement(Arrays.asList(N, NE, E, SE, S, SW, W, NW), 8), 9),
    ROOK("r", Arrays.asList(Position.of(Column.A, Row.ONE), Position.of(Column.H, Row.ONE)),
            new OrdinaryMovement(Arrays.asList(N, E, W, S), 8), 5),
    BISHOP("b", Arrays.asList(Position.of(Column.C, Row.ONE), Position.of(Column.F, Row.ONE)),
            new OrdinaryMovement(Arrays.asList(NE, SE, NW, SW), 8), 3),
    KNIGHT("n", Arrays.asList(Position.of(Column.B, Row.ONE), Position.of(Column.G, Row.ONE)),
            new OrdinaryMovement(Arrays.asList(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW), 1), 2.5),
    PAWN("p", Arrays.stream(Column.values()).map(file -> Position.of(file, Row.TWO)).collect(Collectors.toList()),
            new PawnStrategy(), 1);

    private final String name;
    private final List<Position> initialPositions;
    private final MoveStrategy moveStrategy;
    private final double score;

    ChessPiece(String name, List<Position> initialPositions, MoveStrategy moveStrategy, double score) {
        this.name = name;
        this.initialPositions = Collections.unmodifiableList(initialPositions);
        this.moveStrategy = moveStrategy;
        this.score = score;
    }

    public List<Position> searchMovePath(Position source, Position target) {
        return moveStrategy.findMovePath(source, target);
    }

    public List<Position> searchKillPath(Position source, Position target) {
        return moveStrategy.findKillPath(source, target);
    }

    public double calculateScore(int count) {
        return score * count;
    }

    public List<Position> getOriginalPositions() {
        return initialPositions;
    }

    public String getName() {
        return name;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }
}
