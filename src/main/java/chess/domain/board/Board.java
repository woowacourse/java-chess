package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.Team;
import chess.domain.move.Move;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {

    private final Map<Position, Piece> pieces;

    protected Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public void move(Position source, Position target, Team team) {
        validatePieceExistsAt(source);
        validateTurn(team, source);
        validateNotFriendlyFire(source, target);
        validateMoveExists(source, target);
        validateNoJumps(source, target);
        makeMove(source, target);
    }

    private void validatePieceExistsAt(Position source) {
        if (getPieceAt(source) == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
    }

    private void validateTurn(Team team, Position source) {
        Piece sourcePiece = getPieceAt(source);
        if (!sourcePiece.hasTeam(team)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다");
        }
    }

    private void validateNotFriendlyFire(Position source, Position target) {
        Piece sourcePiece = getPieceAt(source);
        Piece targetPiece = getPieceAt(target);
        if (targetPiece != null && sourcePiece.hasSameColor(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void validateMoveExists(Position source, Position target) {
        if (!hasMove(source, target)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private boolean hasMove(Position source, Position target) {
        Piece sourcePiece = getPieceAt(source);
        Move move = Move.of(source, target);
        if (isAttack(target)) {
            return sourcePiece.hasAttackMove(move);
        }
        return sourcePiece.hasMove(move);
    }

    private void validateNoJumps(Position source, Position target) {
        Move unitMove = Move.of(source, target).getUnitMove();
        Position current = unitMove.move(source);
        while (!current.equals(target)) {
            validateNoPieceAt(current);
            current = unitMove.move(current);
        }
    }

    private void validateNoPieceAt(Position position) {
        if (getPieceAt(position) != null) {
            throw new IllegalArgumentException("다른 기물을 지나칠 수 없습니다");
        }
    }

    private void makeMove(Position source, Position target) {
        if (isAttack(target)) {
            pieces.remove(target);
        }
        pieces.put(target, getPieceAt(source).touch());
        pieces.remove(source);
    }

    private boolean isAttack(Position target) {
        Piece targetPiece = getPieceAt(target);
        return targetPiece != null;
    }

    private Piece getPieceAt(Position position) {
        return pieces.get(position);
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
