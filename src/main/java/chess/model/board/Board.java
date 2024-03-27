package chess.model.board;

import chess.model.piece.Color;
import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Board {
    private static final Color START_COLOR = Color.WHITE;
    public static final int MAX_LENGTH = 8;
    public static final int MIN_LENGTH = 1;
    private static final List<Position> ALL_POSITIONS = Position.values();

    private final Map<Position, Piece> squares;
    private Color currnetColor = START_COLOR;

    public Board(Map<Position, Piece> squares) {
        this.squares = new HashMap<>(squares);
        ALL_POSITIONS.forEach(position -> this.squares.putIfAbsent(position, Empty.getInstance()));
    }

    public void move(Movement movement) {
        validateTurn(movement);
        validateMove(movement);
        updateSquare(movement);
        updateTurn();
    }

    private void validateTurn(Movement movement) {
        Piece sourcePiece = getSourcePiece(movement);
        if (sourcePiece.hasOppositeColorTo(currnetColor)) {
            throw new IllegalArgumentException("현재 턴에 맞는 기물이 아니라서 움직일 수 없습니다.");
        }
    }

    private void validateMove(Movement movement) {
        validateMovementByPiece(movement);
        validateIntermediatePositionsByPiece(movement);
    }

    private void validateMovementByPiece(Movement movement) {
        Piece sourcePiece = getSourcePiece(movement);
        Piece destinationPiece = getDestinationPiece(movement);
        if (!sourcePiece.isValid(movement, destinationPiece)) {
            throw new IllegalArgumentException("해당 기물의 행마법에 맞지 않아 움직일 수 없습니다.");
        }
    }

    private void validateIntermediatePositionsByPiece(Movement movement) {
        Piece sourcePiece = getSourcePiece(movement);
        List<Position> intermediatePositions = sourcePiece.getIntermediatePositions(movement);
        if (intermediatePositions.stream().anyMatch(position -> !squares.get(position).isEmpty())) {
            throw new IllegalArgumentException("해당 기물은 이동 경로에 다른 기물이 있으면 움직일 수 없습니다.");
        }
    }

    private void updateSquare(Movement movement) {
        Position destination = movement.getDestination();
        Position source = movement.getSource();
        squares.put(destination, getSourcePiece(movement));
        squares.put(source, Empty.getInstance());
    }

    private void updateTurn() {
        currnetColor = currnetColor.getOpposite();
    }

    public List<List<Piece>> getLines() {
        List<List<Piece>> lines = new ArrayList<>();
        for (int rank = MAX_LENGTH; rank >= MIN_LENGTH; rank--) {
            lines.add(getLine(rank));
        }
        return lines;
    }

    private List<Piece> getLine(int lineIndex) {
        return IntStream.rangeClosed(MIN_LENGTH, MAX_LENGTH)
                .mapToObj(file -> squares.get(Position.of(file, lineIndex)))
                .toList();
    }

    private Piece getSourcePiece(Movement movement) {
        Position source = movement.getSource();
        return squares.get(source);
    }

    private Piece getDestinationPiece(Movement movement) {
        Position destination = movement.getDestination();
        return squares.get(destination);
    }
}
