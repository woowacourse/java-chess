package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.ui.UserInterface;

import java.util.Map;

public class FinishedBoard implements Board {
    private final Map<Position, Piece> pieces;
    private final UserInterface userInterface;

    FinishedBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        this.pieces = pieces;
        this.userInterface = userInterface;
    }

    @Override
    public Result concludeResult() {
        Team winner = concludeWinner();
        Score winnerScore = calculateScore(winner);
        Team loser = winner.getOpposite();
        Score loserScore = calculateScore(loser);
        return new Result(winner, winnerScore, loser, loserScore);
    }

    private Team concludeWinner() {
        return pieces.values()
                .stream()
                .filter(Piece::isNotBlank)
                .filter(Piece::isKing)
                .findAny()
                .map(Piece::getTeam)
                .orElseThrow(() -> new IllegalStateException("어떤 팀도 King을 가지고 있지 않습니다."));
    }

    private Score calculateScore(Team team) {
        double sum = pieces.values()
                .stream()
                .filter(Piece::isNotBlank)
                .filter(piece -> piece.isSameTeam(team))
                .map(piece -> piece.calculateScore(this))
                .mapToDouble(Score::getValue)
                .sum();
        return new Score(sum);
    }

    @Override
    public Board movePiece() {
        throw new IllegalStateException("게임이 종료되어, 체스말을 움직일 수 없습니다.");
    }

    @Override
    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    @Override
    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    @Override
    public boolean isNotFinished() {
        return false;
    }
}
