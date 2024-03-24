package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.piece.Type;
import chess.model.position.Movement;
import chess.model.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 8;
    private static final Color START_COLOR = Color.WHITE;
    private static final Piece EMPTY = PieceFactory.of(Color.NONE, Type.NONE);
    private static final List<Position> ALL_POSITIONS = Position.values();

    private final Map<Position, Piece> squares;
    private Color currentColor = START_COLOR;

    public Board(Map<Position, Piece> squares) {
        this.squares = new HashMap<>(squares);
        ALL_POSITIONS.forEach(position -> this.squares.putIfAbsent(position, EMPTY));
    }

    public Piece getPiece(int file, int rank) {
        Position position = Position.of(file, rank);
        return squares.get(position);
    }

    public void move(Movement movement) {
        validateTurn(movement);
        validateMove(movement);
        updateSquare(movement);
        currentColor = currentColor.getOpposite();
    }

    private void validateTurn(Movement movement) {
        Piece sourcePiece = getSourcePiece(movement);
        if (!sourcePiece.hasColor(currentColor)) {
            throw new IllegalArgumentException("현재 턴에 맞는 기물을 선택해주세요.");
        }
    }

    private void validateMove(Movement movement) {
        validateMovementByPiece(movement);
        validateIntermediatePositions(movement);
    }

    private void validateMovementByPiece(Movement movement) {
        Piece sourcePiece = getSourcePiece(movement);
        Piece destinationPiece = getDestinationOf(movement);
        if (!sourcePiece.canMove(movement, destinationPiece)) {
            throw new IllegalArgumentException("올바르지 않은 움직임입니다.");
        }
    }

    private void validateIntermediatePositions(Movement movement) {
        boolean isMovementBlocked = movement.getIntermediatePositions()
                .stream()
                .map(squares::get)
                .anyMatch(piece -> !piece.isEmpty());
        if (isMovementBlocked) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private void updateSquare(Movement movement) {
        Position destination = movement.getDestination();
        Position source = movement.getSource();
        squares.put(destination, getSourcePiece(movement));
        squares.put(source, EMPTY);
    }

    private Piece getSourcePiece(Movement movement) {
        Position source = movement.getSource();
        return squares.get(source);
    }

    private Piece getDestinationOf(Movement movement) {
        Position destination = movement.getDestination();
        return squares.get(destination);
    }
}
