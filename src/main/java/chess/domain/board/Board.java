package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Empty;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;
import chess.view.web.PieceSymbolMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final int DUPLICATED_NUMBER_OF_PAWN_IN_LINE = 2;

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

    public void movePiece(final Position source, final Position target) {
        if (reachablePositions(source).contains(target)) {
            board.put(target, board.get(source));
            board.put(source, Empty.of());
            return;
        }
        throw new IllegalArgumentException("해당 기물이 갈 수 있는 위치가 아닙니다.");
    }

    public List<Position> reachablePositions(final Position source) {
        return Arrays.stream(Direction.values())
                .map(direction -> checkReachableInDirection(source, direction))
                .flatMap(positions -> positions.stream())
                .collect(Collectors.toList());
    }

    private List<Position> checkReachableInDirection(final Position source, final Direction direction) {
        final List<Position> positions = new ArrayList<>();

        Distance distance = Distance.ONE;
        while (!isBlocked(source, direction, distance) && isValidMovement(source, direction, distance)) {
            positions.add(source.next(direction, distance));
            distance = distance.next();
        }

        return positions;
    }

    private boolean isBlocked(final Position source, final Direction direction, final Distance distance) {
        try {
            final Position target = source.next(direction, distance);

            final Piece sourcePiece = of(source);
            final Piece targetPiece = of(target);

            return sourcePiece.isSameTeam(targetPiece) || capturedEnemyAlready(source, direction, distance);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean isValidMovement(final Position source, final Direction direction, final Distance distance) {
        final Position target = source.next(direction, distance);
        final Piece sourcePiece = of(source);
        final Piece targetPiece = of(target);

        return sourcePiece.isReachable(direction, distance, source, targetPiece);
    }

    private boolean capturedEnemyAlready(final Position source, final Direction direction, final Distance distance) {
        if (distance.isFirst()) {
            return false;
        }

        final Position prevPosition = source.next(direction, distance.pre());
        final Piece sourcePiece = of(source);
        final Piece prevPiece = of(prevPosition);

        return sourcePiece.isEnemy(prevPiece);
    }

    public int countDuplicatedPawnInLine(final Owner owner) {
        return Arrays.stream(Vertical.values())
                .mapToInt(v -> countPawnInVerticalLine(v, owner))
                .filter(count -> count >= DUPLICATED_NUMBER_OF_PAWN_IN_LINE)
                .sum();
    }

    private int countPawnInVerticalLine(final Vertical v, final Owner owner) {
        return (int) Arrays.stream(Horizontal.values())
                .filter(h -> of(v, h).isOwner(owner) && of(v, h).isPawn())
                .count();
    }

    public String[][] parseUnicodeBoard() {
        return PieceSymbolMapper.parseBoardAsUnicode(board);
    }
}