package domain.game;

import domain.move.Direction;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceGenerator;
import domain.piece.piecerole.Bishop;
import domain.piece.piecerole.King;
import domain.piece.piecerole.Knight;
import domain.piece.piecerole.Pawn;
import domain.piece.piecerole.PieceRole;
import domain.piece.piecerole.Queen;
import domain.piece.piecerole.Rook;
import domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> pieceByPosition;
    private final Map<PieceRole, RouteValidator> routeValidator;

    public ChessBoard() {
        this.pieceByPosition = PieceGenerator.generate();
        this.routeValidator = Map.of(
                new Pawn(Color.WHITE), this::validatePawnMove,
                new Pawn(Color.BLACK), this::validatePawnMove,
                new Knight(), this::validateSingleMoveRoute,
                new Bishop(), this::validateMultipleMoveRoute,
                new Rook(), this::validateMultipleMoveRoute,
                new Queen(), this::validateMultipleMoveRoute,
                new King(), this::validateSingleMoveRoute
        );
    }

    public void add(Position position, Piece piece) {
        pieceByPosition.put(position, piece);
    }

    public void checkColor(Position source, Color color) {
        checkSourcePosition(source);
        if (pieceByPosition.get(source).isNotEqualColor(color)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다.");
        }
    }

    public void checkRoute(Position source, Position target) {
        checkSourcePosition(source);
        checkTargetPosition(source, target);

        Piece piece = pieceByPosition.get(source);
        routeValidator.get(piece.getPieceRole()).validate(source, target);
    }

    private void checkSourcePosition(Position source) {
        if (isEmptyAt(source)) {
            throw new IllegalArgumentException("해당 위치에 Piece가 존재하지 않습니다.");
        }
    }

    private void checkTargetPosition(Position sourcePosition, Position targetPosition) {
        if (hasSameColorPiece(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("같은 진영의 기물이 있는 곳으로 옮길 수 없습니다.");
        }
        if (isSamePosition(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("같은 위치로의 이동입니다. 다시 입력해주세요.");
        }
    }

    private boolean hasSameColorPiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = pieceByPosition.get(sourcePosition);

        if (isNotEmptyAt(targetPosition)) {
            Piece targetPiece = pieceByPosition.get(targetPosition);
            return sourcePiece.isEqualColor(targetPiece.getColor());
        }
        return false;
    }

    private boolean isSamePosition(Position sourcePosition, Position targetPosition) {
        return sourcePosition.equals(targetPosition);
    }

    public void move(Position source, Position target) {
        Piece findPiece = pieceByPosition.get(source);

        pieceByPosition.remove(target);
        pieceByPosition.put(target, findPiece);
        pieceByPosition.remove(source);
    }

    private void validateSingleMoveRoute(Position source, Position target) {
        checkPieceMove(source, target);
    }

    private void checkPieceMove(final Position source, final Position target) {
        Piece sourcePiece = pieceByPosition.get(source);
        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException("해당 피스가 움직일 수 있는 지점이 아닙니다.");
        }
    }

    private void validateMultipleMoveRoute(Position source, Position target) {
        checkPieceMove(source, target);
        validateOtherPieceOnRoute(source, target);
    }

    private void validateOtherPieceOnRoute(Position sourcePosition, Position targetPosition) {
        Direction direction = Direction.findDirection(sourcePosition, targetPosition);
        Position movePosition = sourcePosition.move(direction);

        while (!movePosition.equals(targetPosition)) {
            checkOtherPieceAt(movePosition);
            movePosition = movePosition.move(direction);
        }
    }

    private void checkOtherPieceAt(Position movePosition) {
        if (isNotEmptyAt(movePosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있으면 이동할 수 없습니다.");
        }
    }

    private void validatePawnMove(Position source, Position target) {
        Direction direction = Direction.findDirection(source, target);
        checkPieceMove(source, target);

        if (direction.isNorthOrSouth()) {
            checkOtherPieceAt(target);
        }
        if (direction.isDiagonalDirection()) {
            checkEmptyAtDiagonal(target);
        }
    }

    private void checkEmptyAtDiagonal(Position target) {
        if (isEmptyAt(target)) {
            throw new IllegalArgumentException("이동하려는 곳에 기물이 없어 이동할 수 없습니다.");
        }
    }

    public boolean isNotEmptyAt(final Position position) {
        return pieceByPosition.containsKey(position);
    }

    private boolean isEmptyAt(final Position position) {
        return !isNotEmptyAt(position);
    }

    public Piece findPieceByPosition(Position targetPosition) {
        return pieceByPosition.get(targetPosition);
    }

    public Map<Position, Piece> getPieceByPosition() {
        return Collections.unmodifiableMap(pieceByPosition);
    }
}
