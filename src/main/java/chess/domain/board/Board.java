package chess.domain.board;

import chess.domain.move.Move;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;

    protected Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public void move(Position source, Position target, boolean isWhiteTurn) {
        Piece sourcePiece = findSourcePiece(source);
        checkTurn(isWhiteTurn, sourcePiece);
        boolean isAttack = checkIsAttack(sourcePiece, target);
        Move move = Move.of(source, target);

        checkPieceReachable(sourcePiece, isAttack, move);
        checkNotCrossOtherPiece(source, target, move);

        if (isAttack) {
            pieces.remove(target);
        }
        pieces.remove(source);
        pieces.put(target, sourcePiece.touch());
    }

    private void checkTurn(boolean isWhiteTurn, Piece sourcePiece) {
        if (!sourcePiece.hasColor(isWhiteTurn)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다");
        }
    }

    private Piece findSourcePiece(Position source) {
        Piece sourcePiece = pieces.get(source);
        if (sourcePiece == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
        return sourcePiece;
    }

    private boolean checkIsAttack(Piece sourcePiece, Position target) {
        Piece targetPiece = pieces.get(target);
        if (isEmpty(target)) {
            return false;
        }
        if (sourcePiece.hasSameColor(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
        return true;
    }

    private void checkPieceReachable(Piece sourcePiece, boolean isAttack, Move move) {
        if (!canMove(sourcePiece, move, isAttack)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private boolean canMove(Piece piece, Move move, boolean isAttack) {
        if (isAttack) {
            return piece.hasAttackMove(move);
        }
        return piece.hasMove(move);
    }

    private void checkNotCrossOtherPiece(Position source, Position target, Move move) {
        Move unitMove = move.getUnitMove();
        Position current = moveFrom(source, unitMove);
        while (!current.equals(target)) {
            checkEmpty(current);
            current = moveFrom(current, unitMove);
        }
    }

    private void checkEmpty(Position position) {
        if (isNotEmpty(position)) {
            throw new IllegalArgumentException("다른 기물을 지나칠 수 없습니다");
        }
    }

    private Position moveFrom(Position position, Move move) {
        return move.findDestinationFrom(position);
    }

    private boolean isEmpty(Position position) {
        return !isNotEmpty(position);
    }

    private boolean isNotEmpty(Position position) {
        return pieces.get(position) != null;
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
