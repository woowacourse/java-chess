package chess.domain.board;

import chess.domain.direction.Direction;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public Piece findPiece(final String start) {
        return chessBoard.get(Position.from(start));
    }

    public void switchPosition(final String start, final String end) {
        validateMove(start, end);
        chessBoard.replace(Position.from(end), findPiece(start));
        chessBoard.replace(Position.from(start), new Place());
    }

    private void validateMove(final String start, final String end) {
        Piece piece = findPiece(start);
        validateMoveSamePosition(start, end);
        if (!(piece instanceof Knight)) {
            validateObstacle(start, end);
        }
        piece.canMove(start, end);
        validatePawnMove(piece, start, end);
        validateMoveMyTeam(start, end);
    }

    private void validatePawnMove(final Piece piece, final String start, final String end) {
        if (!(piece instanceof Pawn)) {
            return;
        }

        if (piece.isNameLowerCase()) {
            validateLowercasePawnMove(start, end);
            return;
        }

        validateUppercasePawnMove(start, end);
    }

    private void validateLowercasePawnMove(final String start, final String end) {
        if (Direction.isLowerPawnDiagonal(start, end)) {
            validateLowercasePawnAttack(end);
            return;
        }

        validateLowercasePawnMoveForward(end);
    }

    private void validateLowercasePawnAttack(final String end) {
        Piece upperEnemy = findPiece(end);
        if (upperEnemy.isNameLowerCase() || upperEnemy instanceof Place) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateLowercasePawnMoveForward(final String end) {
        Piece destination = findPiece(end);
        if (!(destination instanceof Place)) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateUppercasePawnMove(final String start, final String end) {
        if (Direction.isUpperPawnDiagonal(start, end)) {
            validateUppercasePawnAttack(end);
            return;
        }
        validateUppercasePawnMoveForward(end);
    }

    private void validateUppercasePawnAttack(final String end) {
        Piece lowerEnemy = findPiece(end);
        if (!lowerEnemy.isNameLowerCase() || lowerEnemy instanceof Place) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateUppercasePawnMoveForward(final String end) {
        Piece destination = findPiece(end);
        if (!(destination instanceof Place)) {
            throw new IllegalArgumentException("폰의 잘못된 이동입니다.");
        }
    }

    private void validateMoveMyTeam(final String start, final String end) {
        if ((findPiece(start).isNameLowerCase() == findPiece(end).isNameLowerCase()) && !findPiece(end).getName()
                .equals(".")) {
            throw new IllegalArgumentException("우리팀 말에게 이동할 수 없습니다.");
        }
    }

    private void validateMoveSamePosition(final String start, final String end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("같은 위치로 움직일 수 없습니다.");
        }
    }

    private void validateObstacle(final String start, final String end) {
        List<String> routes = Direction.getRoute(start, end);

        boolean check = routes.stream()
                .map(this::findPiece)
                .anyMatch(piece -> !(piece instanceof Place));

        if(check) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
    }
}
