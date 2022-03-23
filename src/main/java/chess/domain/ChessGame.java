package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(final String rawSource, final String rawTarget) {
        Piece sourcePiece = board.getPiece(Position.valueOf(rawSource));
        validateSourceBlank(sourcePiece);
        Position target = Position.valueOf(rawTarget);
    }

    private void validateSourceBlank(final Piece sourcePiece) {
        if (sourcePiece.isBlank()) {
            throw new IllegalStateException("[ERROR] source에 Piece가 존재하지 않습니다.");
        }
    }
}
