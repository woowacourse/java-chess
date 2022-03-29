package chess2.domain2.board2;

import chess2.domain2.board2.piece2.Color;
import chess2.domain2.board2.piece2.Piece;
import chess2.domain2.board2.piece2.PieceType;
import chess2.util2.PositionUtil;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = board;
    }

    public void movePiece(Position from, Position to, Color color) {
        validatePieceAndCurrentTurn(from, color);
        Piece fromPiece = board.get(from);
        if (isOccupied(to)) {
            confirmAttack(from, to, fromPiece);
            return;
        }
        validateMovable(fromPiece.canMove(from, to));
        confirmMove(from, to);
    }

    private void validatePieceAndCurrentTurn(Position from, Color currentTurnColor) {
        if (!isOccupied(from)) {
            throw new IllegalArgumentException("해당 위치에 체스 말은 존재하지 않습니다.");
        }
        Piece fromPiece = board.get(from);
        if (!fromPiece.hasColorOf(currentTurnColor)) {
            throw new IllegalArgumentException(currentTurnColor + " 진영이 움직일 차례입니다!");
        }
    }

    private void confirmAttack(Position from, Position to, Piece fromPiece) {
        Piece targetPiece = board.get(to);
        if (!fromPiece.canAttack(from, to, targetPiece)) {
            throw new IllegalArgumentException("공격할 수 없는 위치입니다.");
        }
        confirmMove(from, to);
    }

    private void validateMovable(boolean movable) {
        if (!movable) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }
    }

    private void confirmMove(Position from, Position to) {
        validateClearPath(from, to);
        Piece fromPiece = board.remove(from);
        board.put(to, fromPiece);
    }

    private void validateClearPath(Position from, Position to) {
        boolean isClear = PositionUtil.positionsStraightBetween(from, to)
                .stream()
                .noneMatch(this::isOccupied);

        if (!isClear) {
            throw new IllegalArgumentException("다른 말이 가로막고 있습니다.");
        }
    }

    private boolean isOccupied(Position position) {
        return board.containsKey(position);
    }

    public int countByType(PieceType pieceType) {
        return (int) board.values()
                .stream()
                .filter(piece -> piece.hasTypeOf(pieceType))
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    @Override
    public String toString() {
        return "Board{board=" + board + '}';
    }
}
