package chess.domain.state;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.piece.Piece;

public final class Running extends State {
    Running(final Board board, final Color color) {
        super(board, color);
    }

    @Override
    public State move(final Position source, final Position target) {
        Piece capturePiece = board().getPiece(target);
        Board newBoard = board().move(source, target, color());
        if (capturePiece.isSamePieceType(PieceType.KING)) {
            return new GameEnd(newBoard, color());
        }
        return new Running(newBoard, nextColor());
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException("게임 진행 중 입니다.");
    }

    @Override
    public State end() {
        return new End(board(), color());
    }

    Color nextColor() {
        if (color() == Color.WHITE) {
            return Color.BLACK;
        }
        return Color.WHITE;
    }
}
