package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.direction.Direction;
import chess.domain.piece.*;
import chess.manager.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void move(final Position source, final Position target, final Owner owner) {
        if (!of(source).isOwner(owner)) {
            throw new IllegalArgumentException();
        }

        if (!ableToMove(source).contains(target)) {
            throw new IllegalArgumentException();
        }

        checkGameEnd(target);
        movePiece(source, target);
    }

    private void checkGameEnd(final Position target) {
        if (of(target).isKing()) {
            isEnd = true;
        }
    }

    private void movePiece(final Position source,final Position target) {
        putPiece(source, target);
        putEmpty(source);
    }

    private void putPiece(final Position source, final Position target) {
        board.put(target, board.get(source));
    }

    private void putEmpty(final Position position) {
        board.put(position, Empty.getInstance());
    }

    public Status getStatus() {
        return new Status(calculateScore(Owner.WHITE), calculateScore(Owner.BLACK));
    }

    private Score calculateScore(final Owner owner) {
        Score score = new Score(0);
        boolean existKing = false;

        for (final Vertical v : Vertical.values()) {
            for (final Horizontal h : Horizontal.values()) {
                final Piece piece = of(v, h);

                if (!piece.isOwner(owner)) {
                    continue;
                }

                if (piece.isKing()) {
                    existKing = true;
                }

                score = score.plus(piece.score());
            }
        }

        if (existKing == false) {
            return new Score(0);
        }

        return score.calculatePawnPenaltyScore(getPawnCountInLine(owner));
    }

    private int getPawnCountInLine(final Owner owner) {
        int totalCount = 0;
        for (final Vertical v : Vertical.values()) {
            int verticalCount = 0;
            for (final Horizontal h : Horizontal.values()) {
                if (of(v, h).equals(Pawn.getInstanceOf(owner))) {
                    verticalCount++;
                }
            }
            if (verticalCount > 1) {
                totalCount += verticalCount;
            }
        }
        return totalCount;
    }

    public List<Position> ableToMove(final Position source) {
        final List<Position> ableToMove = new ArrayList<>();
        final Piece sourcePiece = of(source);

        for (final Direction direction : sourcePiece.getDirections()) {
            for (int i = 1; i <= sourcePiece.getMaxDistance(); i++) {
                Position nextPosition;
                try {
                    nextPosition = source.next(direction, i);
                } catch (IllegalArgumentException e) {
                    break;
                }

                final Piece nextPiece = of(nextPosition);

                if (sourcePiece.isSameTeam(nextPiece)) {
                    break;
                }

                if (sourcePiece.validateMove(source, nextPosition, nextPiece)) {
                    ableToMove.add(nextPosition);
                }

                if (sourcePiece.isEnemy(nextPiece)) {
                    break;
                }
            }
        }

        return ableToMove;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<Position> getAbleToMove(final Position source) {
        return ableToMove(source);
    }
}
