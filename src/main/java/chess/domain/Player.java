package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

public class Player {
    public static final String OPPONENT_MOVE_ERROR = "상대편의 말은 이동할 수 없습니다";
    public static final String CANT_KILL_MINE_ERROR = "내 말이 있는 곳으로 이동할 수 없습니다";
    private final Color color;

    public Player(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Piece pickStartPiece(final Position position, final Pieces pieces) {
        final Piece piece = pieces.getPieceOf(position);
        if (piece.isSameColor(color)) {
            return piece;
        }
        throw new IllegalArgumentException(OPPONENT_MOVE_ERROR);
    }

    public void move(final Position from, final Position to, final Pieces pieces) {
        final Piece start = pickStartPiece(from, pieces);
        final Piece piece = pieces.getPieceOf(to);
        if (piece.isEmpty()) {
            start.moveToEmpty(to, pieces);
            return;
        }
        if (piece.isSameColor(color)) {
            throw new IllegalArgumentException(CANT_KILL_MINE_ERROR);
        }
        start.moveForKill(to, pieces);
        pieces.delete(piece);
    }
}
