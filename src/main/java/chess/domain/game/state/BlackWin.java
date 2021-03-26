package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;
import java.util.List;

public class BlackWin extends Finished {
    public BlackWin(Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new Init(board());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException("이미 체스게임이 종료되었습니다.");
    }

    @Override
    public State passTurn() {
        throw new IllegalStateException("이미 체스게임이 종료되었습니다.");
    }

    @Override
    public List<Rank> ranks() {
        throw new IllegalStateException("이미 체스게임이 종료되었습니다.");
    }

    @Override
    public String finishReason() {
        return "흑색의 승리로 게임이 종료되었습니다.";
    }

    @Override
    public Color winner() {
        return Color.BLACK;
    }

    @Override
    public State end() {
        return new End(board());
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }
}
