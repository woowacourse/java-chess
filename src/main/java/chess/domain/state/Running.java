package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;

public final class Running extends State {
    Running(final Color color) {
        super(color);
    }

    @Override
    public State move(final Position source, final Position target, final Board board) {
        Piece capturePiece = board.getPiece(target);
        board.move(source, target, color());
        if (capturePiece.isSamePieceType(PieceType.KING)) {
            return new GameEnd(color());
        }
        return new Running(color().reverse());
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("게임 진행 중 입니다.");
    }

    @Override
    public State end() {
        return new End(color());
    }
}
