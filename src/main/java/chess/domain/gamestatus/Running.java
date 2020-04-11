package chess.domain.gamestatus;

import chess.domain.Team;
import chess.domain.board.Board;

public class Running extends Started {

    private Team currentTeam;

    Running() {
        super();
        currentTeam = Team.WHITE;
    }

    private Running(Board board, Team thisTurn) {
        super(board);
        this.currentTeam = thisTurn;
    }

    @Override
    public GameStatus start() {
        throw new UnsupportedOperationException("게임이 이미 시작되었습니다.");
    }

    @Override
    public GameStatus move(String fromPosition, String toPosition) {
        board.move(currentTeam, fromPosition, toPosition);
        return decideNextStatus();
    }

    private GameStatus decideNextStatus() {
        Team nextTeam = currentTeam.opponent();

        if (!board.isPieceOnBoard(nextTeam)) {
            return new Finished(this.board);
        }
        return new Running(this.board, nextTeam);
    }

    @Override
    public GameStatus finish() {
        return new Finished(this.board);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
