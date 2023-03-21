package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private State state;
    private final Board board;

    public ChessGame(final Board board) {
        this.state = State.RUN;
        this.board = board;
    }

    public void start() {
        this.state = State.START;
    }

    public void end() {
        this.state = State.END;
    }

    public void moveOrNot(final Position source, final Position target) {
        checkPlayable();
        final Piece sourcePiece = board.getPiece(source.getFile(), source.getRank());
        final List<Position> movablePositions = sourcePiece.findMovablePositions(source, board);
        checkMovable(movablePositions, target);
        board.move(source, target);
        if (sourcePiece.isPawn()) {
            sourcePiece.changePawnMoved();
        }
    }

    private void checkPlayable() {
        if (state.isStart()) {
            return;
        }
        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }

    private void checkMovable(final List<Position> movablePositions, final Position target) {
        if (!movablePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }

    public boolean isStart() {
        return state.isStart();
    }

    public Board getBoard() {
        return board;
    }
}
