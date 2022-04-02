package chess.domain.state;

import chess.domain.board.Board;
<<<<<<< HEAD
import chess.domain.board.Location;
=======
>>>>>>> step1
import chess.domain.board.TeamScore;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class White extends Running {

    public White(Board board) {
        super(board);
    }

    @Override
<<<<<<< HEAD
    public State move(Location source, Location target) {
        Piece sourcePiece = getBoard().getPiece(source);
        Piece targetPiece = getBoard().getPiece(target);

        checkSourceColor(sourcePiece);
        super.checkMovable(source, target);
        checkTarget(targetPiece);

        getBoard().move(source, target);
        if (targetPiece.isKing()) {
=======
    public State getNextState(Piece piece) {
        if (piece.isKing()) {
>>>>>>> step1
            return end();
        }
        return new Black(getBoard());
    }

    @Override
    public TeamScore getScore() {
        double score = getBoard().computeTotalScore(Team.WHITE);
        return new TeamScore(Team.WHITE, score);
    }

<<<<<<< HEAD
    private void checkSourceColor(Piece piece) {
        if (!piece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 해당 말은 움직일 수 없습니다.");
        }
    }

    private void checkTarget(Piece targetPiece) {
        if (targetPiece.isWhite()) {
            throw new IllegalArgumentException("[ERROR] 같은 색의 기물을 잡을 수 없습니다.");
        }
=======
    @Override
    public Team getTeam() {
        return Team.WHITE;
>>>>>>> step1
    }
}
