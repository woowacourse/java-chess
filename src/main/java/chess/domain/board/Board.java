package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Empty;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece of(final Position position) {
        return board.get(position);
    }

    public Piece of(final Vertical vertical, final Horizontal horizontal) {
        return of(new Position(vertical, horizontal));
    }

    public void move(final Position source, final Position target) {
        if (!reachablePositions(source).contains(target)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 있는 위치가 아닙니다.");
        }

        board.put(target, board.get(source));
        board.put(source, Empty.getInstance());
    }

    private List<Position> reachablePositions(final Position source) {
        return Arrays.stream(Direction.values())
                .flatMap(direction -> addReachableDistance(source, direction).stream())
                .collect(Collectors.toList());
    }

    private List<Position> addReachableDistance(final Position source, final Direction direction) {
        final List<Position> ableToMove = new ArrayList<>();
        Distance distance = Distance.ONE;
        while (isReachable(source, direction, distance)) {
            ableToMove.add(source.next(direction, distance));
            distance = distance.next();
        }
        return ableToMove;
    }

    private boolean isReachable(final Position source, final Direction direction, final Distance distance) {
        try {
            final Position target = source.next(direction, distance);
            return !of(source).isSameTeam(of(target))
                    && !isPrePositionEnemy(source, direction, distance)
                    && of(source).isReachable(direction, distance, source, of(target));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isPrePositionEnemy(final Position source, final Direction direction, final Distance distance) {
        if (distance.isFirst()) {
            return false;
        }
        final Position prePosition = source.next(direction, distance.pre());
        return of(source).isEnemy(of(prePosition));
    }

    public int countDuplicatedPawnInLine(final Owner owner) {
        return Arrays.stream(Vertical.values())
                .mapToInt(v -> countPawnInVerticalLine(v, owner))
                .filter(count -> count > 1)
                .sum();
    }

    private int countPawnInVerticalLine(final Vertical v, final Owner owner) {
        return (int) Arrays.stream(Horizontal.values())
                .filter(h -> of(v, h).isOwner(owner) && of(v, h).isPawn())
                .count();
    }

    public List<Position> getAblePositionsToMove(final Position source) {
        return reachablePositions(source);
    }

    public Piece getPieceOf(final Position position) {
        return of(position);
    }
}