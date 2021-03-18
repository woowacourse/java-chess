package chess.domain.board;


import chess.domain.piece.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Board {
    public static final int MIN_BORDER = 1;
    public static final int MAX_BORDER = 8;
    private final Map<Position, Piece> board;

    public Board(Map<Position, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public Piece findPieceFromPosition(Position position) {
        return board.get(position);
    }

    public boolean movePiece(Position target, Position destination) {
        Piece targetPiece = findPieceFromPosition(target);
        List<Position> targetMovablePositions = targetPiece.searchMovablePositions(target);
        checkMovable(targetMovablePositions, destination);
        if (targetPiece.canMove(target, destination, this)) {
            movePieceToPosition(destination, targetPiece);
            clearPosition(target);
            return true;
        }
        return false;
    }

    private void movePieceToPosition(Position destination, Piece targetPiece) {
        board.put(destination, targetPiece);
    }

    private void clearPosition(Position target) {
        board.put(target, null);
    }

    private void checkMovable(List<Position> targetMovablePositions, Position destination) {
        if (targetMovablePositions.contains(destination)) {
            return;
        }
        throw new UnsupportedOperationException("이동 불가능한 좌표입니다.");
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
