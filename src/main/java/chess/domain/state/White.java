package chess.domain.state;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.point.Points;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;

import static chess.domain.Color.WHITE;
import static chess.domain.PieceType.KING;

public final class White extends State {
    public White(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public State start(final long gameId) {
        return new Start(gameId, Chess.create(Board.create(), Points.create()));
    }

    @Override
    public State move(final Command command) {
        final Piece targetPiece = chess.move(command.getSource(), command.getTarget(), WHITE);
        if (targetPiece.isSamePieceType(KING)) {
            return end();
        }
        return new Black(gameId, chess);
    }

    @Override
    public State end() {
        return new End(gameId, chess);
    }

    @Override
    public State status() {
        return new Status(gameId, chess, WHITE);
    }

    @Override
    public State loadList() {
        return new LoadList(gameId, chess);
    }

    @Override
    public State load(final List<MovementDto> movements) {
        throw new UnsupportedOperationException("White에서 사용할 수 없는 load 메서드입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public Color getCurrentPlayer() {
        return WHITE;
    }
}
