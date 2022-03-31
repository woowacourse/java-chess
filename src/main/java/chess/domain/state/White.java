package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Location;
import chess.domain.board.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
    public State move(Location source, Location target) {
        Piece sourcePiece = getBoard().getPiece(source);
        Piece targetPiece = getBoard().getPiece(target);

        super.checkMovable(source, target);
        checkSourceColor(sourcePiece);
        checkTarget(targetPiece);

        getBoard().move(source, target);
        if (targetPiece.isKing()) {
            return end();
        }
        return new Black(getBoard());
    }

    @Override
    public TeamScore getScore() {
        double score = getBoard().computeTotalScore(Team.WHITE);
        return new TeamScore(Team.WHITE, score);
    }

    private void checkSourceColor(Piece piece) {
        if (!piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    private void checkTarget(Piece targetPiece) {
        if (targetPiece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
    }
}
