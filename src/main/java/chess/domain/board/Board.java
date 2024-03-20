package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.square.Square;
import java.util.Map;

public class Board {
    public static final String INVALID_TURN = "헤당 색의 턴이 아닙니다.";
    public static final String INVALID_PIECE_MOVEMENT = "해당 기물은 위치로 이동할 수 없습니다.";
    private final Map<Square, Piece> pieces;

    public Board(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Square from, final Square to, final Color turn) {
        // 1. from 자리에 색이 turn과 같은지
        // 2. piece가 from에서 to로 이동 가능한지
        // 3. 경로에 다른 기물 있으면 예외
        validate(from, to, turn);
    }

    private void validate(final Square from, final Square to, final Color turn) {
        checkTurn(from, turn);
        checkMovable(from, to);
        checkRoute(from, to);
        checkDestinationColor(from, to);
    }

    private void checkTurn(final Square from, final Color turn) {
        if (!pieces.get(from).color().equals(turn)) {
            throw new IllegalArgumentException(INVALID_TURN);
        }
    }

    private void checkMovable(final Square from, final Square to) {
        if (!pieces.get(from).canMove(from, to)) {
            throw new IllegalArgumentException(INVALID_PIECE_MOVEMENT);
        }
    }

    private void checkRoute(final Square from, final Square to) {
        if (pieces.get(from).type().equals(Type.KNIGHT)) {
            return;
        }

        int dividor = Math.max(Math.abs(to.getFileIndex() - from.getFileIndex()),
                Math.abs(to.getRankIndex() - from.getRankIndex()));

        int fileDiff = (to.getFileIndex() - from.getFileIndex()) / dividor;
        int rankDiff = (to.getRankIndex() - from.getRankIndex()) / dividor;

        for (int i = 1; i < dividor; i++) {
            if (pieces.containsKey(
                    Square.getNextSquare(
                            from.getFileIndex() + fileDiff * i,
                            from.getRankIndex() + rankDiff * i
                    )
            )) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDestinationColor(final Square from, final Square to) {
        if (!pieces.containsKey(to)) {
            return;
        }
        if (pieces.get(from).color().equals(pieces.get(to).color())) {
            throw new IllegalArgumentException();
        }
    }

    public Map<Square, Piece> getPieces() {
        return pieces;
    }
}
