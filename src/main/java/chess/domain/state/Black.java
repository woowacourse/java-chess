package chess.domain.state;

import chess.domain.Board;
import chess.domain.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class Black extends Running {
    public Black(Board board) {
        super(board);
    }

    @Override
    Running getOpposingTeam() {
        return new White(getBoard());
    }

    @Override
    public void checkSourceColor(Piece piece) {
        if (!piece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    @Override
    public void checkTarget(Piece piece) {
        if (piece.isBlack()) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
    }

    @Override
    public TeamScore getScore() {
        double score = getBoard().computeTotalScore(Team.BLACK);
        return new TeamScore(Team.BLACK, score);
    }

}
