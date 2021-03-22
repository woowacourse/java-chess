package chess.domain.position;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.state.State;

public class Source {
    private final Piece piece;

    private Source(final Piece piece) {
        this.piece = piece;
    }

    public static Source valueOf(final Position position, final State state) {
        Piece piece = state.findPiece(position)
                .orElseThrow(() -> new IllegalArgumentException(String.format("해당 위치에 기물없음! 입력 값: %s", position)));
        return new Source(piece);
    }

    public boolean isSamePosition(final Position piece) {
        return this.piece.isSamePosition(piece);
    }

    public void move(final Target target, final Pieces basePieces, final Pieces targetPieces) {
        piece.move(target, basePieces, targetPieces);
    }
}
