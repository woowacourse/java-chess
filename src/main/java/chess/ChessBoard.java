package chess;

import chess.piece.Color;
import chess.piece.King;
import chess.piece.Piece;
import chess.piece.movementcondition.MovementCondition;
import chess.position.Position;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> board;
    private Color currentColor;

    public ChessBoard(Map<Position, Piece> board, Color currentColor) {
        if (!hasKingEachColor(board)) {
            throw new IllegalArgumentException("각 색깔 별로 King이 필요합니다.");
        }
        this.board = new HashMap<>(board);
        this.currentColor = currentColor;
    }

    private boolean hasKingEachColor(Map<Position, Piece> board) {
        return hasKingByColor(board, Color.WHITE) && hasKingByColor(board, Color.BLACK);
    }

    private boolean hasKingByColor(Map<Position, Piece> board, Color color) {
        return board.values().stream()
                .filter(piece -> piece.equals(new King(color)))
                .anyMatch(piece -> piece.isSameColor(color));
    }

    public void move(Position from, Position to) {
        if (isFinished()) {
            throw new IllegalStateException("체스 게임이 종료되었습니다.");
        }
        if (!isCurrentColorPiece(from)) {
            throw new IllegalArgumentException(String.format("%s 색깔의 기물을 움직일 수 있습니다.", currentColor));
        }
        if (isSameMovementPosition(from, to) || !isPossibleMovement(from, to) || hasSameColorTargetPiece(from, to)) {
            throw new IllegalArgumentException(String.format("기물을 %s에서 %s로 이동할 수 없습니다.", from, to));
        }
        movePickedPiece(from, to);
    }

    public boolean isFinished() {
        return !hasKingByColor(getBoard(), Color.WHITE) || !hasKingByColor(getBoard(), Color.BLACK);
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

    private boolean isSameMovementPosition(Position from, Position to) {
        return from.equals(to);
    }

    private boolean isPossibleMovement(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        MovementCondition condition = pickedPiece.identifyMovementCondition(from, to);
        return condition.isPossibleMovement(from, to, getBoard());
    }

    private boolean hasSameColorTargetPiece(Position from, Position to) {
        if (!hasPieceByPosition(to)) {
            return false;
        }
        Piece pickedPiece = findPieceByPosition(from);
        Piece targetPiece = findPieceByPosition(to);
        return pickedPiece.isSameColor(targetPiece.getColor());
    }

    private boolean hasPieceByPosition(Position position) {
        return board.containsKey(position);
    }

    private void movePickedPiece(Position from, Position to) {
        Piece pickedPiece = findPieceByPosition(from);
        board.remove(from);
        board.put(to, pickedPiece);
        currentColor = currentColor.reverse();
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public Score getScore(Color color) {
        return new Score(getBoard(), color);
    }

    public Color getWinner() {
        if (!isFinished()) {
            throw new IllegalStateException("체스 게임이 종료되지 않았습니다.");
        }
        if (hasKingByColor(getBoard(), Color.WHITE)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
