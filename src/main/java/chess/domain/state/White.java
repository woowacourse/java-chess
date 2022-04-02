package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
    public State getNextState(Piece piece) {
        if (piece.isKing()) {
            return end();
        }
        return new Black(getBoard());
    }

    @Override
    public TeamScore getScore() {
        double score = getBoard().computeTotalScore(Team.WHITE);
        return new TeamScore(Team.WHITE, score);
    }

    @Override
    public Team getTeam() {
        return Team.WHITE;

    }
}
