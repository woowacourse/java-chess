package chess.fixture;

import chess.domain.game.Turn;
import chess.domain.piece.Team;

import java.util.List;

public class TurnFixture {
    public static final Turn TURN = new Turn(List.of(Team.WHITE, Team.BLACK));
}
