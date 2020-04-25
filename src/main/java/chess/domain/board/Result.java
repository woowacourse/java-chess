package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;

import java.util.Map;
import java.util.Objects;

public class Result {
    private final Team winner;
    private final Score winnerScore;
    private final Team loser;
    private final Score loserScore;

    //todo: check if board is good
    Result(Map<Position, Piece> pieces) {
        this.winner = concludeWinner(pieces);
        this.winnerScore = calculateScore(winner, pieces);
        this.loser = winner.getOpposite();
        this.loserScore = calculateScore(loser, pieces);
    }

    public String getWinner() {
        return winner.toString();
    }

    public String getWinnerScore() {
        return winnerScore.toString();
    }

    public String getLoser() {
        return loser.toString();
    }

    public String getLoserScore() {
        return loserScore.toString();
    }

    private Team concludeWinner(Map<Position, Piece> pieces) {
        return pieces.values()
                .stream()
                .filter(Piece::isNotBlank)
                .filter(Piece::isKing)
                .findAny()
                .map(Piece::getTeam)
                .orElseThrow(() -> new IllegalStateException("어떤 팀도 King을 가지고 있지 않습니다."));
    }

    private Score calculateScore(Team team, Map<Position, Piece> pieces) {
        PiecesState piecesState = new PiecesState(pieces);
        double sum = pieces.values()
                .stream()
                .filter(Piece::isNotBlank)
                .filter(piece -> piece.isSameTeam(team))
                .map(piece -> piece.calculateScore(piecesState))
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
                Objects.equals(winnerScore, result.winnerScore) &&
                loser == result.loser &&
                Objects.equals(loserScore, result.loserScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winner, winnerScore, loser, loserScore);
    }
}
