package domain.piece;

import domain.board.Board;
import domain.chessgame.Score;
import domain.position.Direction;
import domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final Score SCORE = new Score(0);
    private static final String NAME = "k";

    public Pawn(boolean isBlack) {
        super(isBlack);
    }

    public static Map<Position, Piece> createInitialPawn() {
        Map<Position, Piece> initialPawn = new HashMap<>();
        initialPawn.put(new Position("a7"), new Pawn(true));
        initialPawn.put(new Position("b7"), new Pawn(true));
        initialPawn.put(new Position("c7"), new Pawn(true));
        initialPawn.put(new Position("d7"), new Pawn(true));
        initialPawn.put(new Position("e7"), new Pawn(true));
        initialPawn.put(new Position("f7"), new Pawn(true));
        initialPawn.put(new Position("g7"), new Pawn(true));
        initialPawn.put(new Position("h7"), new Pawn(true));
        initialPawn.put(new Position("a2"), new Pawn(false));
        initialPawn.put(new Position("b2"), new Pawn(false));
        initialPawn.put(new Position("c2"), new Pawn(false));
        initialPawn.put(new Position("d2"), new Pawn(false));
        initialPawn.put(new Position("e2"), new Pawn(false));
        initialPawn.put(new Position("f2"), new Pawn(false));
        initialPawn.put(new Position("g2"), new Pawn(false));
        initialPawn.put(new Position("h2"), new Pawn(false));
        return initialPawn;
    }

    public String getName() {
        return NAME;
    }

    public Score score() {
        return SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        if (!target.isChessBoardPosition() || isSameColor(board.piece(target))) {
            return false;
        }
        List<Direction> directions = new ArrayList<>(findDirections());
        Direction forwardDirection = directions.remove(0);
        if (isForwardMovable(board, forwardDirection, source, target)
            || isDiagonalMovable(board, directions, source, target)) {
            return true;
        }
        return isFirstMovable(board, forwardDirection, source, target);
    }

    private List<Direction> findDirections() {
        if (isBlack()) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    private boolean isForwardMovable(Board board, Direction direction, Position source,
        Position target) {
        return board.piece(target).isEmpty() && source.sum(direction).equals(target);
    }

    private boolean isDiagonalMovable(Board board, List<Direction> directions, Position source,
        Position target) {
        return board.piece(target).isNotEmpty() && directions.stream()
            .anyMatch(direction -> source.sum(direction).equals(target));
    }

    private boolean isFirstMovable(Board board, Direction direction, Position source,
        Position target) {
        if (!createInitialPawn().containsKey(source) || board.piece(target).isNotEmpty()) {
            return false;
        }
        Position firstStep = source.sum(direction);
        if (board.piece(firstStep).isNotEmpty()) {
            return false;
        }
        source = firstStep.sum(direction);
        return source.equals(target);
    }
}
