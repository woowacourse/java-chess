package chess.domain.state;

import chess.domain.Board;
import chess.domain.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
    Running getOpposingTeam() {
        return new Black(getBoard());
    }

    @Override
    public TeamScore getScore() {
        return new TeamScore(Team.WHITE, getBoard());
    }

    @Override
    public void checkSourceColor(Piece piece) {
        if (!piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    @Override
    public void checkTarget(Piece piece) {
        if (piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
    }
}
