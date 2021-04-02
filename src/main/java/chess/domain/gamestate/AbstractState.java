package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.dto.ScoreDto;
import chess.domain.team.Team;

public abstract class AbstractState implements State {

    protected final Board board;

    public AbstractState(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public ScoreDto judgeResult() {
        return ScoreDto.of(board);
    }

    @Override
    public boolean isAnyKingDead() {
        return board.isAnyKingDead();
    }
}
