package chess.domain.board;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.domain.board.piece.PieceType;
import chess.domain.board.position.Position;
import chess.util.PositionUtil;
import java.util.Map;
import java.util.Objects;

public class Board {

    private static final String NON_EXISTING_PIECE_EXCEPTION_MESSAGE = "해당 위치에 체스 말은 존재하지 않습니다.";
    private static final String INVALID_TURN_EXCEPTION_TEXT = " 진영이 움직일 차례입니다!";
    private static final String INVALID_ATTACK_ROUTE_EXCEPTION_MESSAGE = "공격할 수 없는 위치입니다.";
    private static final String INVALID_MOVE_ROUTE_EXCEPTION_MESSAGE = "해당 위치로 이동할 수 없습니다.";
    private static final String BLOCKED_PATH_EXCEPTION_MESSAGE = "다른 말이 가로막고 있습니다.";

    private final Map<Position, Piece> boardMap;

    public Board(Map<Position, Piece> boardMap) {
        this.boardMap = boardMap;
    }

    public void movePiece(Position from, Position to, Color color) {
        validatePieceAndCurrentTurn(from, color);
        Piece fromPiece = boardMap.get(from);
        if (isOccupied(to)) {
            confirmAttack(from, to, fromPiece);
            return;
        }
        validateMovable(fromPiece.canMove(from, to));
        confirmMove(from, to);
    }

    private void validatePieceAndCurrentTurn(Position from, Color currentTurn) {
        if (!isOccupied(from)) {
            throw new IllegalArgumentException(NON_EXISTING_PIECE_EXCEPTION_MESSAGE);
        }
        Piece fromPiece = boardMap.get(from);
        if (!fromPiece.hasColorOf(currentTurn)) {
            throw new IllegalArgumentException(currentTurn + INVALID_TURN_EXCEPTION_TEXT);
        }
    }

    private void confirmAttack(Position from, Position to, Piece fromPiece) {
        Piece targetPiece = boardMap.get(to);
        if (!fromPiece.canAttack(from, to, targetPiece)) {
            throw new IllegalArgumentException(INVALID_ATTACK_ROUTE_EXCEPTION_MESSAGE);
        }

        confirmMove(from, to);
    }

    private void validateMovable(boolean movable) {
        if (!movable) {
            throw new IllegalArgumentException(INVALID_MOVE_ROUTE_EXCEPTION_MESSAGE);
        }
    }

    private void confirmMove(Position from, Position to) {
        validateClearPath(from, to);

        Piece fromPiece = boardMap.remove(from);
        boardMap.put(to, fromPiece);
    }

    private void validateClearPath(Position from, Position to) {
        boolean isClear = PositionUtil.positionsStraightBetween(from, to)
                .stream()
                .noneMatch(this::isOccupied);

        if (!isClear) {
            throw new IllegalArgumentException(BLOCKED_PATH_EXCEPTION_MESSAGE);
        }
    }

    private boolean isOccupied(Position position) {
        return boardMap.containsKey(position);
    }

    public int countByType(PieceType pieceType) {
        return (int) boardMap.values()
                .stream()
                .filter(piece -> piece.hasTypeOf(pieceType))
                .count();
    }

    public Map<Position, Piece> toMap() {
        return boardMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(boardMap, board.boardMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardMap);
    }

    @Override
    public String toString() {
        return "Board{" + boardMap + '}';
    }
}
