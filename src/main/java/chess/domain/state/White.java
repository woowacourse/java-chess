package chess.domain.state;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.point.Points;
import chess.dto.Command;

import static chess.domain.PieceType.KING;

public final class White extends State {
    public White(final Chess chess) {
        super(chess);
    }

    @Override
    public State start() {
        return new Start(Chess.create(Board.create(), Points.create()));
    }

    @Override
    public State move(final Command command) {
        final Piece targetPiece = chess.move(command.getSource(), command.getTarget(), Color.WHITE);
        if (targetPiece.isSamePieceType(KING)) {
            return end();
        }
        return new Black(chess);
    }

    @Override
    public State end() {
        return new End(chess);
    }

    @Override
    public State status() {
        return new Status(chess, Color.WHITE);
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
