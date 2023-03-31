package chess.domain.strategy;

import chess.domain.Score;
import chess.domain.Square;
import chess.domain.piece.info.Team;
import java.util.List;

public interface ScoreCalculator {

    Score calculateByTeam(List<Square> squares, Team team);

}
