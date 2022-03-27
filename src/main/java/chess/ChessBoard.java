package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.Position;
import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {

    private Map<Position, Piece> board;
    private Color currentColor;

    public ChessBoard(List<Piece> squares, Color currentColor) {
        this.board = squares.stream()
            .collect(Collectors.toMap(Piece::getPosition, piece -> piece));
        this.currentColor = currentColor;
    }

    public void move(Position from, Position to) {
        if (!isCurrentColorPiece(from)) {
            throw new IllegalArgumentException(String.format(
                "%s 색깔의 기물을 움직일 수 없습니다.", currentColor));
        }

        if (hasObstacleBetweenPositions(from, to) || hasUncaptuarableTargetPiece(from, to)) {
            throw new IllegalArgumentException(String.format(
                "기물을 %s에서 %s로 이동할 수 없습니다.", from, to));
        }

        movePickedPiece(from, to);
    }

    private boolean isCurrentColorPiece(Position position) {
        Piece piece = findPieceByPosition(position);
        return piece.isSameColor(currentColor);
    }

    private Piece findPieceByPosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position));
        }
        return board.get(position);
    }

    private boolean hasObstacleBetweenPositions(Position from, Position to) {
        if (from.hasLinearPath(to)) {
            return from.getLinearPath(to).stream()
                .anyMatch(this::hasPieceByPosition);
        }
        return false;
    }

    private boolean hasPieceByPosition(Position position) {
        return board.containsKey(position);
    }

    private boolean hasUncaptuarableTargetPiece(Position from, Position to) {
        if (hasPieceByPosition(to)) {
            Piece pickedPiece = findPieceByPosition(from);
            Piece targetPiece = findPieceByPosition(to);
            return pickedPiece.isSameColor(targetPiece.getColor()) || pickedPiece.isPawn();
        }
        return false;
    }

    private void movePickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        board.remove(from);
        board.put(to, pickedPiece.transfer(to));
        currentColor = currentColor.reverse();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
