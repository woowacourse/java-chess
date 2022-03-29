package chess;

import static java.util.stream.Collectors.toMap;

import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessBoard {

    private final Map<Position, Piece> board;
    private Color currentColor;

    public ChessBoard(List<Piece> pieces, Color currentColor) {
        checkPieces(pieces);
        this.board = pieces.stream().collect(toMap(Piece::getPosition, piece -> piece));
        this.currentColor = currentColor;
    }

    private void checkPieces(List<Piece> pieces) {
        if (hasDuplicatePositionPieces(pieces)) {
            throw new IllegalArgumentException("동일한 위치의 기물이 있습니다.");
        }
        if (!hasKingEachColor(pieces)) {
            throw new IllegalArgumentException("색깔별로 킹이 없습니다.");
        }
    }

    private boolean hasKingEachColor(List<Piece> pieces) {
        return hasKingByColor(pieces, Color.WHITE) && hasKingByColor(pieces, Color.BLACK);
    }

    private boolean hasKingByColor(List<Piece> pieces, Color color) {
        return pieces.stream()
                .filter(Piece::isKing)
                .anyMatch(piece -> piece.isSameColor(color));
    }

    private boolean hasDuplicatePositionPieces(List<Piece> pieces) {
        return getDistinctPositionPieceCount(pieces) != pieces.size();
    }

    private long getDistinctPositionPieceCount(List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getPosition)
                .distinct()
                .count();
    }

    public void move(Position from, Position to) {
        if (isFinished()) {
            throw new IllegalStateException("체스 게임이 종료되었습니다.");
        }
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
        Piece transferredPiece = transferPickedPiece(from, to);
        board.remove(from);
        board.put(to, transferredPiece);
        currentColor = currentColor.reverse();
    }

    private Piece transferPickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        return pickedPiece.transfer(to, new Pieces(getPieces()));
    }

    public boolean isFinished() {
        return !hasKingByColor(Color.WHITE) || !hasKingByColor(Color.BLACK);
    }

    private boolean hasKingByColor(Color color) {
        return getPieces().stream()
                .filter(Piece::isKing)
                .anyMatch(piece -> piece.isSameColor(color));
    }

    public List<Piece> getPieces() {
        return new ArrayList<>(board.values());
    }

    public Score getScore(Color color) {
        return new Score(getPiecesByColor(color));
    }

    private List<Piece> getPiecesByColor(Color color) {
        return getPieces().stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
    }

    public Color getWinner() {
        if (!isFinished()) {
            throw new IllegalStateException("체스 게임이 종료되지 않았습니다.");
        }
        if (hasKingByColor(Color.WHITE)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
