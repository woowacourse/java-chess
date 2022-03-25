package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;
import chess.position.Transition;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Position, Piece> board = new HashMap<>();

    public ChessBoard(Set<Square> squares) {
        board = squares.stream()
            .collect(Collectors.toMap(Square::getPosition, Square::getPiece));
    }

    public void move(Position from, Position to) {
        if (isSameColorPiecesByPosition(from, to)) {
            throw new IllegalArgumentException(String.format(
                "%s에서 %s로 기물을 이동할 수 없습니다.", from, to));
        }

        if (HasObstacleBetweenPositions(from, to)) {
            Piece piece = findPieceByPosition(from);
            throw new IllegalArgumentException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", piece.getClass().getSimpleName(), from, to));
        }

        Piece piece = findPieceByPosition(from);

        if (piece.isPawn() && hasPieceByPosition(to)) {
            throw new IllegalArgumentException(String.format("폰이 이동하려는 위치 %s에 기물이 있습니다.", to));
        }

        if (hasPieceByPosition(to)) {
            if (!piece.isMovablePosition(new Transition(from, to))) {
                throw new IllegalArgumentException();
            }

            Piece piece1 = findPieceByPosition(from);
            board.remove(from);
            board.put(to, piece1);
        } else {
            if (!piece.isMovablePosition(new Transition(from, to))) {
                throw new IllegalArgumentException();
            }
            Piece piece1 = findPieceByPosition(from);
            board.remove(from);
            board.put(to, piece1);
        }
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException();
        }
        return board.get(position);
    }

    private boolean isSameColorPiecesByPosition(Position from, Position to) {
        if (board.containsKey(to)) {
            Piece fromPiece = board.get(from);
            Piece toPiece = board.get(to);
            return fromPiece.isSameColor(toPiece.getColor());
        }
        return false;
    }

    private boolean HasObstacleBetweenPositions(Position from, Position to) {
        Transition transition = new Transition(from, to);
        return transition.getPath().stream()
            .anyMatch(this::hasPieceByPosition);
    }

    private boolean hasPieceByPosition(Position position) {
        return board.containsKey(position);
    }

    public Collection<Square> getSquares() {
        return board.keySet()
            .stream().map(position -> new Square(position, board.get(position)))
            .collect(Collectors.toList());
    }

    public boolean isSameColorByPosition(Position from, Color color) {
        return findPieceByPosition(from).isSameColor(color);
    }
}
