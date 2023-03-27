package chess.domain.state;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.point.Points;
import chess.dto.Command;
import chess.dto.MovementDto;

import java.util.List;

import static chess.domain.Color.BLACK;
import static chess.domain.PieceType.KING;

public final class Black extends State {
    public Black(final long gameId, final Chess chess) {
        super(gameId, chess);
    }

    @Override
    public State start(final long gameId) {
        return new Start(gameId, Chess.create(Board.create(), Points.create()));
    }

    @Override
    public State move(final Command command) {
        final Piece targetPiece = chess.move(command.getSource(), command.getTarget(), BLACK);
        if (targetPiece.isSamePieceType(KING)) {
            return end();
        }
        return new White(gameId, chess);
    }

    @Override
    public State end() {
        return new End(gameId, chess);
    }

    @Override
    public State status() {
        return new Status(gameId, chess, BLACK);
    }

    @Override
    public State loadList() {
        return new LoadList(gameId, chess);
    }

    @Override
    public State load(final List<MovementDto> movements) {
        throw new UnsupportedOperationException("Black에서 샤용할 수 없는 load 메서드 입니다.");
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public Color getCurrentPlayer() {
        return BLACK;
    }
}
