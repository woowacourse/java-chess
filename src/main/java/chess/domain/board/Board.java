package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Empty;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.rule.Direction;
import chess.domain.piece.rule.Distance;
import chess.domain.piece.rule.Score;
import chess.manager.Status;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Piece> board;
    private boolean isEnd = false;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Piece of(final Position position) {
        return board.get(position);
    }

    public Piece of(final Vertical vertical, final Horizontal horizontal) {
        return of(new Position(vertical, horizontal));
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    public void move(final Position source, final Position target) {
        validateMove(source, target);
        checkGameEnd(target);
        movePiece(source, target);
    }

    private void validateMove(final Position source, final Position target) {
        if (!calculateReachablePositions(source).contains(target)) {
            throw new IllegalArgumentException("해당 기물이 갈 수 있는 위치가 아닙니다.");
        }
    }

    private List<Position> calculateReachablePositions(final Position source) {
        return Arrays.stream(Direction.values())
                .flatMap(direction -> addReachableDistance(source, direction).stream())
                .collect(Collectors.toList());
    }

    private List<Position> addReachableDistance(final Position source, final Direction direction) {
        final List<Position> ableToMove = new ArrayList<>();
        for (final Distance distance : Distance.values()) {
            if (isBlocked(source, direction, distance) || isUnreachable(source, direction, distance)) {
                break;
            }
            ableToMove.add(source.next(direction, distance));
        }
        return ableToMove;
    }

    private boolean isBlocked(final Position source, final Direction direction, final Distance distance) {
        try {
            source.next(direction, distance);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean isUnreachable(final Position source, final Direction direction, final Distance distance) {
        final Position target = source.next(direction, distance);
        if (of(source).isSameTeam(of(target)) || isPrePositionEnemy(source, direction, distance)) {
            return false;
        }
        return !of(source).isReachable(direction, distance, source, of(target));
    }

    private boolean isPrePositionEnemy(final Position source, final Direction direction, final Distance distance) {
        if (distance.equals(Distance.ONE)) {
            return false;
        }
        final Position prePosition = source.next(direction, distance.before());
        return of(source).isEnemy(of(prePosition));
    }

    private void checkGameEnd(final Position target) {
        if (of(target).isKing()) {
            isEnd = true;
        }
    }

    private void movePiece(final Position source, final Position target) {
        board.put(target, board.get(source));
        board.put(source, Empty.getInstance());
    }

    public Status status() {
        return new Status(calculateScore(Owner.WHITE), calculateScore(Owner.BLACK));
    }

    private Score calculateScore(final Owner owner) {
        Score score = new Score(0);

        if (!isKingLive(owner)) {
            return score;
        }

        for (final Vertical v : Vertical.values()) {
            for (final Horizontal h : Horizontal.values()) {
                if (of(v, h).isOwner(owner)) {
                    score = score.plus(of(v, h).score());
                }
            }
        }

        return score.calculatePawnPenaltyScore(getPawnCountDuplicatedInLine(owner));
    }

    private boolean isKingLive(final Owner owner) {
        return Arrays.stream(Vertical.values())
                .flatMap(v -> Arrays.stream(Horizontal.values())
                        .filter(h -> of(v, h).isOwner(owner) && of(v, h).isKing()))
                .findFirst()
                .isPresent();
    }

    private int getPawnCountDuplicatedInLine(final Owner owner) {
        int totalCount = 0;
        for (final Vertical v : Vertical.values()) {
            int verticalCount = getPawnCountInVerticalLine(v, owner);
            if (verticalCount > 1) {
                totalCount += verticalCount;
            }
        }
        return totalCount;
    }

    private int getPawnCountInVerticalLine(final Vertical v, final Owner owner) {
        return (int) Arrays.stream(Horizontal.values())
                .filter(h -> of(v, h).isOwner(owner) && of(v, h).isPawn())
                .count();
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<Position> getAblePositionsToMove(final Position source) {
        return calculateReachablePositions(source);
    }

    public boolean isPositionOwner(final Position position, final Owner owner) {
        return of(position).isOwner(owner);
    }

    public Piece getPieceOf(final Position position) {
        return of(position);
    }
}
