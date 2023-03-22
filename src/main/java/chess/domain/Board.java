package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.game.GameScore;

import java.util.*;

public class Board {

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public boolean canMove(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        Piece targetPiece = squares.get(target);
        return isAttackable(source, target) && isMovable(sourcePiece, targetPiece);
    }

    public void move(Position source, Position target) {
        squares.put(target, squares.get(source));
        squares.put(source, Empty.INSTANCE);
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

    public double calculateScore(Team team) {
        return GameScore.calculateScore(new HashMap<>(squares), team);
    }

    private boolean isAttackable(Position source, Position target) {
        return isCorrectMoving(source, target) && validatePieceRole(source, target);
    }

    private boolean isMovable(Piece sourcePiece, Piece targetPiece) {
        return isNotEmptyPiece(sourcePiece) && isDifferentTeamPieces(sourcePiece, targetPiece);
    }

    private boolean isNotEmptyPiece(Piece sourcePiece) {
        return !sourcePiece.isRoleOf(Role.EMPTY);
    }

    private boolean isDifferentTeamPieces(Piece sourcePiece, Piece targetPiece) {
        return !sourcePiece.isSameTeamWith(targetPiece);
    }

    private boolean isCorrectMoving(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        return sourcePiece.canMove(source, target);
    }

    private boolean validatePieceRole(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        if (sourcePiece.isRoleOf(Role.KNIGHT)) {
            return true;
        }
        return hasNoCollision(source, target) && checkPawnMoving(source, target);
    }

    private boolean hasNoCollision(Position source, Position target) {
        List<Position> routes = source.getBetweenPositions(target);
        return routes.stream()
                .map(squares::get)
                .allMatch(piece -> piece.isRoleOf(Role.EMPTY));
    }

    private boolean checkPawnMoving(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        if (!sourcePiece.isRoleOf(Role.PAWN)) {
            return true;
        }
        Piece targetPiece = squares.get(target);
        if (source.isSameXAs(target) && !targetPiece.isRoleOf(Role.EMPTY)
                || !source.isSameXAs(target) && targetPiece.isRoleOf(Role.EMPTY)) {
            return false;
        }
        return true;
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }
}
