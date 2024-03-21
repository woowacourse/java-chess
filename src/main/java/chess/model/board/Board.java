package chess.model.board;

import chess.model.piece.Empty;
import chess.model.piece.Piece;
import chess.model.piece.Type;
import chess.model.position.Movement;
import chess.model.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Board {
    public static final int MAX_LENGTH = 8;
    public static final int MIN_LENGTH = 1;
    private static final List<Position> ALL_POSITIONS = Position.values();

    private final Map<Position, Piece> squares;

    public Board(Map<Position, Piece> squares) {
        this.squares = new HashMap<>(squares);
        ALL_POSITIONS.forEach(position -> this.squares.putIfAbsent(position, Empty.getInstance()));
    }

    public List<String> getSignatures() {
        return squares.values().stream()
                .map(Piece::getSignature)
                .toList();
    }

    public List<List<String>> getLines() {
        List<List<String>> lines = new ArrayList<>();
        for (int rank = MAX_LENGTH; rank >= MIN_LENGTH; rank--) {
            lines.add(getLine(rank));
        }
        return lines;
    }

    private List<String> getLine(int lineIndex) {
        return IntStream.rangeClosed(MIN_LENGTH, MAX_LENGTH)
                .mapToObj(file -> squares.get(Position.of(file, lineIndex)).getSignature())
                .toList();
    }

    public void move(Position source, Position destination) {
        Movement movement = new Movement(source, destination);
        Piece sourcePiece = squares.get(source);
        Piece targetPiece = squares.get(destination);
        validateMove(movement, sourcePiece, targetPiece);
        squares.put(destination, sourcePiece);
        squares.put(source, Empty.getInstance());
    }

    private void validateMove(Movement movement, Piece sourcePiece, Piece targetPiece) {
        validateOppositeColor(sourcePiece, targetPiece);
        validateMovementByPiece(movement, sourcePiece, targetPiece);
        validateIntermediatePositions(movement, sourcePiece);
    }

    private void validateOppositeColor(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameColorWith(targetPiece)) {
            throw new IllegalArgumentException("같은 색깔인 기물은 먹을 수 없습니다.");
        }
    }

    private void validateMovementByPiece(Movement movement, Piece sourcePiece, Piece targetPiece) {
        if (!sourcePiece.isValid(movement)) {
            throw new IllegalArgumentException("올바르지 않은 움직임입니다.");
        }
        if (sourcePiece.isType(Type.PAWN)) {
            validatePawn(movement, targetPiece);
        }
    }

    private void validatePawn(Movement movement, Piece targetPiece) {
        if (targetPiece.isEmpty() && movement.isSameFile() || !targetPiece.isEmpty() && movement.isDiagonal()) {
            return;
        }
        throw new IllegalArgumentException("올바르지 않은 움직임입니다.");
    }

    private void validateIntermediatePositions(Movement movement, Piece sourcePiece) {
        if (sourcePiece.isType(Type.KNIGHT)) {
            return;
        }
        List<Position> intermediatePositions = movement.getIntermediatePositions();
        if (intermediatePositions.stream().anyMatch(position -> !squares.get(position).isEmpty())) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }
}
