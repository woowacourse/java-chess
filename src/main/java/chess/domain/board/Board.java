package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.direction.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Score;
import chess.domain.piece.king.King;
import chess.manager.Status;

import java.util.*;

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

    public void move(final Position source, final Position target) {
        validateMove(source, target);
        checkGameEnd(target);
        movePiece(source, target);
    }

    private void validateMove(final Position source, final Position target) {
        if (!ableToMove(source).contains(target)) {
            throw new IllegalArgumentException("유효하지 않은 이동입니다.");
        }
    }

    private List<Position> ableToMove(final Position source) {
        // TODO :: 인덴트 줄이기
        final List<Position> ableToMove = new ArrayList<>();
        final Piece sourcePiece = of(source);

        for (final Direction direction : sourcePiece.getDirections()) {
            for (int distance = 1; distance <= sourcePiece.getMaxDistance(); distance++) {
                if (isBlocked(source, direction, distance)) {
                    break;
                }

                final Position target = source.next(direction, distance);
                if (sourcePiece.isReachable(source, target, of(target))) {
                    ableToMove.add(target);
                }

                if (sourcePiece.isEnemy(of(target))) {
                    break;
                }
            }
        }

        return ableToMove;
    }

    private boolean isBlocked(final Position source, final Direction direction, final int distance) {
        try {
            return isSameTeam(source, source.next(direction, distance));
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean isSameTeam(final Position source, final Position target) {
        return of(source).isSameTeam(of(target));
    }

    private void checkGameEnd(final Position target) {
        if (of(target).isKing()) {
            isEnd = true;
        }
    }

    public boolean isEnd() {
        return isEnd;
    }

    private void movePiece(final Position source, final Position target) {
        putPiece(source, target);
        putEmpty(source);
    }

    private void putPiece(final Position source, final Position target) {
        board.put(target, board.get(source));
    }

    private void putEmpty(final Position position) {
        board.put(position, Empty.getInstance());
    }

    public List<Position> getAbleToMove(final Position source) {
        return ableToMove(source);
    }

    public boolean isKingAlive(final Owner owner) {
        return board.containsValue(King.getInstanceOf(owner));
    }

    public Collection<Piece> pieces() {
        return new ArrayList<>(board.values());
    }

    public boolean isPositionOwner(final Position position, final Owner owner) {
        return of(position).isOwner(owner);
    }

    public Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    public void resetBoard() {
        BoardInitializer.resetBoard(board);
    }
}
