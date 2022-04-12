package chess.status;

import chess.game.Board;
import chess.game.MoveCommand;
import chess.piece.Piece;
import chess.piece.detail.Color;
import chess.position.Position;
import chess.view.Command;
import java.util.Map;

public final class Finished extends AbstractState {

    Finished() {
    }

    Finished(final Board board, final Color turn) {
        super(board, turn);
    }

    @Override
    public State turn(final Command command) {
        throw new IllegalStateException("게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void move(final MoveCommand moveCommand) {
        throw new IllegalStateException("게임이 종료되어 말을 움직일 수 없습니다.");
    }

    @Override
    public Map<Color, Double> getStatus() {
        throw new IllegalCallerException("게임이 종료되어 상태를 불러올 수 없습니다.");
    }

    @Override
    public void load(final Map<Position, Piece> board) {
        throw new IllegalCallerException("불가능");
    }
}
