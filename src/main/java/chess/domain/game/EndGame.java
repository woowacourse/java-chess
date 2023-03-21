package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;

import java.util.List;

public class EndGame implements ChessGameStep {

    private final Color winColor;

    public EndGame(final Color winColor) {
        this.winColor = winColor;
    }

    @Override
    public boolean playable() {
        return false;
    }

    @Override
    public ChessGameStep initialize() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public ChessGameStep movePiece(final PiecePosition source, final PiecePosition destination) {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public ChessGameStep end() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public List<Piece> pieces() {
        throw new IllegalStateException("이미 종료된 상태입니다.");
    }

    @Override
    public Color winColor() {
        return winColor;
    }
}
