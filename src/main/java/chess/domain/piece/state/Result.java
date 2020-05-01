package chess.domain.piece.state;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Objects;

public class Result {
    private final Team winner;
    private final Team loser;
    private final Score whiteScore;
    private final Score blackScore;

    //todo: check if board is good
    private Result(Team winner, Team loser, Score whiteScore,  Score blackScore) {
        this.winner = winner;
        this.loser = loser;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    static Result conclude(Pieces pieces) {
        Team winner = concludeWinner(pieces);
        Team loser = winner.getOpposite();
        Score whiteScore = calculateScore(Team.WHITE, pieces);
        Score blackScore = calculateScore(Team.BLACK, pieces);
        return new Result(winner, loser, whiteScore, blackScore);
    }

    public String getWinner() {
        return winner.toString();
    }

    public String getWhiteScore() {
        return whiteScore.toString();
    }

    public String getBlackScore() {
        return blackScore.toString();
    }

    private static Team concludeWinner(Pieces pieces) {
        //todo: 여기에도 isNotFinished일 때를 고려해야하나...?
        //todo: check instanceOf
        if (pieces.isNotFinished()) {
            return Team.NOT_ASSIGNED;
        }

        return pieces.getPieces()
                .values()
                .stream()
                .filter(piece -> piece instanceof King)
                .findAny()
                .map(Piece::getTeam)
                .orElseThrow(() -> new IllegalStateException("어떤 팀도 King을 가지고 있지 않습니다."));
    }

    private static Score calculateScore(Team team, Pieces pieces) {
        double sum = pieces.getPieces()
                .values()
                .stream()
                .filter(piece -> piece.isSameTeam(team))
                .map(piece -> piece.calculateScore(pieces))
                .mapToDouble(Score::getValue)
                .sum();
        return new Score(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return winner == result.winner &&
                Objects.equals(whiteScore, result.whiteScore) &&
                loser == result.loser &&
                Objects.equals(blackScore, result.blackScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner, whiteScore, loser, blackScore);
    }
}
