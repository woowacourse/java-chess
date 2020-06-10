package chess.domain.piece.state;

import chess.domain.piece.Piece;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Objects;

public class Result {
    private final Team winner;
    private final Team loser;
    private final Score whiteScore;
    private final Score blackScore;

    private Result(Team winner, Team loser, Score whiteScore,  Score blackScore) {
        this.winner = winner;
        this.loser = loser;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Result conclude(PiecesState piecesState) {
        Team winner = concludeWinner(piecesState);
        Team loser = winner.getOpposite();
        Score whiteScore = calculateScore(Team.WHITE, piecesState);
        Score blackScore = calculateScore(Team.BLACK, piecesState);
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

    private static Team concludeWinner(PiecesState piecesState) {
        if (piecesState.isNotFinished()) {
            return Team.NOT_ASSIGNED;
        }

        return piecesState.getPieces()
                .values()
                .stream()
                .filter(Piece::isKing)
                .findAny()
                .map(Piece::getTeam)
                .orElseThrow(() -> new IllegalStateException("어떤 팀도 King을 가지고 있지 않습니다."));
    }

    private static Score calculateScore(Team team, PiecesState piecesState) {
        return piecesState.calculateScoreOf(team);
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
