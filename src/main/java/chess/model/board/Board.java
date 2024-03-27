package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.position.Movement;
import chess.model.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Board {
    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 8;
    private static final Color START_COLOR = Color.WHITE;
    private static final Piece EMPTY = Empty.getInstance();

    private final Map<Position, Piece> squares;
    private Color currentColor = START_COLOR;

    public Board(Map<Position, Piece> squares) {
        this.squares = new HashMap<>(squares);
    }

    public Piece getPiece(int file, int rank) {
        Position position = Position.of(file, rank);
        return getByPosition(position);
    }

    public List<Piece> getRank(int rank) {
        return IntStream.rangeClosed(MIN_LENGTH, MAX_LENGTH)
                .mapToObj(file -> Position.of(file, rank))
                .map(this::getByPosition)
                .toList();
    }

    public Map<Piece, Integer> getPieceCount(Color color) {
        return squares.values()
                .stream()
                .filter(piece -> piece.hasColor(color))
                .collect(groupingBy(Function.identity(),
                        collectingAndThen(counting(), Long::intValue)));
    }

    public int countPieceOfFile(Piece piece, int file) {
        return (int) getFile(file).stream()
                .filter(filePiece -> filePiece.equals(piece))
                .count();
    }

    private List<Piece> getFile(int file) {
        return IntStream.rangeClosed(MIN_LENGTH, MAX_LENGTH)
                .mapToObj(rank -> Position.of(file, rank))
                .map(this::getByPosition)
                .toList();
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
                .map(this::getByPosition)
                .anyMatch(piece -> !piece.isEmpty());
        if (isMovementBlocked) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private void updateSquare(Movement movement) {
        Position destination = movement.getDestination();
        Position source = movement.getSource();
        squares.put(destination, getSourcePiece(movement));
        squares.remove(source);
    }

    private Piece getSourcePiece(Movement movement) {
        Position source = movement.getSource();
        return getByPosition(source);
    }

    private Piece getDestinationOf(Movement movement) {
        Position destination = movement.getDestination();
        return getByPosition(destination);
    }

    private Piece getByPosition(Position position) {
        return squares.getOrDefault(position, EMPTY);
    }
}
