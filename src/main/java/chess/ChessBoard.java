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

    private void movePickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        Piece transferredPiece = pickedPiece.transfer(to, getPieces());

        board.remove(from);
        board.put(to, transferredPiece);
        currentColor = currentColor.reverse();
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }
}
