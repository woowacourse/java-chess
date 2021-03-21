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

    public void move(final Position source, final Position target) {
        validateMove(source, target);
        checkGameEnd(target);
        movePiece(source, target);
    }

    private void validateMove(final Position source, final Position target){
        if (!ableToMove(source).contains(target)) {
            throw new IllegalArgumentException();
        }
    }

    private List<Position> ableToMove(final Position source) {
        // TODO :: 인덴트 줄이기
        final List<Position> ableToMove = new ArrayList<>();
        final Piece sourcePiece = of(source);

        /*
            XXX :: piece의 방향과 가능 거리를 가져와 처리하지 말고 piece에서 처리할 수 있도록
         */

        for (final Direction direction : sourcePiece.getDirections()) {
            for (int distance = 1; distance <= sourcePiece.getMaxDistance(); distance++) {
                if(isBlocked(source, direction, distance)){
                    break;
                }

                final Position target = source.next(direction, distance);
                if (sourcePiece.isReachable(source, target, of(target))) {
                    ableToMove.add(target);
                }

                if (sourcePiece.isEnemy(of(target)) ) {
                    break;
                }
            }
        }

        return ableToMove;
    }

    private boolean isBlocked(final Position source, final Direction direction, final int distance){
        try {
            return of(source).isSameTeam(of(source.next(direction, distance)));
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private void checkGameEnd(final Position target) {
        if (of(target).isKing()) {
            isEnd = true;
        }
    }

    private void movePiece(final Position source,final Position target) {
        board.put(target, board.get(source));
        board.put(source, Empty.getInstance());
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
                if(!of(v, h).isOwner(owner)){
                    continue;
                }

                if (of(v, h).isPawn()) {
                    verticalCount++;
                }
            }
            if (verticalCount > 1) {
                totalCount += verticalCount;
            }
        }
        return totalCount;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public List<Position> getAbleToMove(final Position source) {
        return ableToMove(source);
    }

    public boolean isPositionOwner(final Position position, final Owner owner){
        return of(position).isOwner(owner);
    }

    public Piece getPieceOf(final Position position){
        return of(position);
    }
}
