package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.dto.BoardSnapshot;

import java.util.*;

public class Board {

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = squares;
    }

    public boolean canMove(Position source, Position target) {
        Piece sourcePiece = squares.get(source);
        BoardSnapshot boardSnapShot = getBoardSnapShot();
        return sourcePiece.canMove(source, target, boardSnapShot);
    }

    public void move(Position source, Position target) {
        squares.put(target, squares.get(source));
        squares.put(source, Empty.INSTANCE);
    }

    public Piece findByPosition(Position position) {
        return squares.get(position);
    }

//    private boolean isAttackable(Position source, Position target) {
//        return isDifferentPosition(source, target) && isCorrectMoving(source, target) && validatePieceRole(source, target);
//    }
//
//    private boolean isMovable(Piece sourcePiece, Piece targetPiece) {
//        return isNotEmptyPiece(sourcePiece) && isDifferentTeamPieces(sourcePiece, targetPiece);
//    }
//
//    private boolean isDifferentPosition(Position source, Position target) {
//        return !Objects.equals(source, target);
//    }
//
//    private boolean isNotEmptyPiece(Piece sourcePiece) {
//        return !sourcePiece.isRoleOf(Role.EMPTY);
//    }
//
//    private boolean isDifferentTeamPieces(Piece sourcePiece, Piece targetPiece) {
//        return !sourcePiece.isSameTeamWith(targetPiece);
//    }
//
//    private boolean isCorrectMoving(Position source, Position target) {
//        Piece sourcePiece = squares.get(source);
//        return sourcePiece.canMove(source, target);
//    }
//
//    private boolean validatePieceRole(Position source, Position target) {
//        Piece sourcePiece = squares.get(source);
//        if (sourcePiece.isRoleOf(Role.KNIGHT)) {
//            return true;
//        }
//        BoardSnapshot boardSnapShot = getBoardSnapShot();
//        return sourcePiece.canAttack(source, target, boardSnapShot);
//    }

//    private boolean validateCollision(Position source, Position target) {
//        List<Position> routes = source.getBetweenPositions(target);
//        return routes.stream()
//                .map(squares::get)
//                .allMatch(piece -> piece.isRoleOf(Role.EMPTY));
//    }

//    private boolean validatePawnMove(Position source, Position target) {
//        Piece targetPiece = squares.get(target);
//        if (source.isSameXAs(target) && !targetPiece.isRoleOf(Role.EMPTY)
//                && !source.isSameXAs(target) && targetPiece.isRoleOf(Role.EMPTY)) {
//            return false;
//        }
//        return true;
//    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(squares);
    }

    private BoardSnapshot getBoardSnapShot() {
        return new BoardSnapshot(new HashMap<>(squares));
    }
}
