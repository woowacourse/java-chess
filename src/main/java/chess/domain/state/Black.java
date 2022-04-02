package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Black extends Running {
    public Black(Board board) {
        super(board);
    }

    @Override

    public State getNextState(Piece piece) {
        if (piece.isKing()) {
            return end();
        }
        return new White(getBoard());
    }

    @Override
    public TeamScore getScore() {
        double score = getBoard().computeTotalScore(Team.BLACK);
        return new TeamScore(Team.BLACK, score);
    }


    @Override
    public Team getTeam() {
        return Team.BLACK;
    }
}
