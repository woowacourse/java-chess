package chess.domain;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String NON_MOVABLE_POSITION = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        validateExistPiecePosition(fromPosition);
        Piece piece = board.get(fromPosition);
        validateMovablePosition(piece, fromPosition, toPosition);
        board.remove(fromPosition);
        board.put(toPosition, piece);
    }

    private void validateExistPiecePosition(Position position) {
        if(!board.containsKey(position)) {
            throw new IllegalArgumentException(NOT_EXIST_PIECE);
        }
    }

    private void validateMovablePosition(Piece piece,Position fromPosition, Position toPosition) {
        if (!piece.isMovable(fromPosition, toPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_POSITION);
        }
    }
}
