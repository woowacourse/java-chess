package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> board;
    private Color currentColor;

    public ChessBoard(Set<Square> squares, Color currentColor) {
        board = squares.stream()
            .collect(Collectors.toMap(Square::getPosition, Square::getPiece));
        this.currentColor = currentColor;
    }

    public void move(Position from, Position to) {
        checkIsPossibleMovement(from, to);
        movePiece(from, to);
        currentColor = currentColor.reverse();
    }

    private void checkIsPossibleMovement(Position from, Position to) {
        Piece piece = findPieceByPosition(from);

        if (isSamePosition(from, to)) {
            throw new IllegalArgumentException(String.format(
                "같은 위치(%s)로 기물을 이동할 수 없습니다.", from));
        }

        if (isUnmovablePieceColor(from)) {
            throw new IllegalArgumentException(String.format(
                "%s에 위치한 기물은 %s 색깔이 아닙니다.", from, currentColor));
        }

        if (isSameColorPiecesByPosition(from, to) || !piece.isPossibleMovement(from, to)
        || HasObstacleBetweenPositions(from, to) || piece.isPawn() && hasPieceByPosition(to)) {
            throw new IllegalArgumentException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", piece.getClass().getSimpleName(), from, to));
        }
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position));
        }
        return board.get(position);
    }

    private boolean isSamePosition(Position from, Position to) {
        return from.equals(to);
    }

    private boolean isUnmovablePieceColor(Position from) {
        return !isSameColorByPosition(from, currentColor);
    }

    public boolean isSameColorByPosition(Position from, Color color) {
        return findPieceByPosition(from).isSameColor(color);
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
        return from.getPath(to).stream()
            .anyMatch(this::hasPieceByPosition);
    }

    private boolean hasPieceByPosition(Position position) {
        return board.containsKey(position);
    }

    private void movePiece(Position from, Position to) {
        Piece piece = findPieceByPosition(from);
        board.remove(from);
        board.put(to, piece);
    }

    public Collection<Square> getSquares() {
        return board.keySet()
            .stream().map(position -> new Square(position, board.get(position)))
            .collect(Collectors.toList());
    }
}
