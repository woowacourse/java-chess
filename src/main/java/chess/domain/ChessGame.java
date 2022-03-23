package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public void move(final String rawSource, final String rawTarget) {
        Position target = Position.valueOf(rawTarget);
        Piece sourcePiece = board.getPiece(Position.valueOf(rawSource));
        if (sourcePiece instanceof Blank) {
            throw new IllegalStateException("[ERROR] source에 Piece가 존재하지 않습니다.");
        }
    }
}
