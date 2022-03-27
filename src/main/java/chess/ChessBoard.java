package chess;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.File;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
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
                "%s 색깔의 기물을 움직일 수 있습니다.", currentColor));
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

    public BigDecimal getScore(Color color) {
        return getDefaultScore(color).subtract(getDeductionOfPawn(color));
    }

    private BigDecimal getDefaultScore(Color color) {
        return getPieces().stream()
            .filter(piece -> piece.isSameColor(color))
            .map(Piece::getPoint)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getDeductionOfPawn(Color color) {
        return new BigDecimal("0.5").multiply(new BigDecimal(numberOfPawn(color)));
    }

    private long numberOfPawn(Color color) {
        return Arrays.stream(File.values())
            .mapToLong(file -> numberOfPawnEachFile(color, file))
            .filter(numberOfPawn -> numberOfPawn > 1)
            .sum();
    }

    private long numberOfPawnEachFile(Color color, File file) {
        return getPieces().stream()
            .filter(Piece::isPawn)
            .filter(piece -> piece.isSameColor(color))
            .filter(piece -> piece.isSameFile(file))
            .count();
    }
}
