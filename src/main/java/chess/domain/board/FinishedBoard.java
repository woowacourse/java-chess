package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.position.Position;
import chess.domain.piece.score.Score;
import chess.domain.piece.team.Team;
import chess.domain.ui.UserInterface;

import java.util.Map;

public class FinishedBoard extends StartedBoard {
    FinishedBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        super(pieces, userInterface);
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

    @Override
    public boolean isNotFinished() {
        return false;
    }
}
