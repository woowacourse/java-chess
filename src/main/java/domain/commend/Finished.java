package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public class Finished extends GameState {

    public Finished(Pieces pieces) {
        super(pieces);
    }

    @Override
    public StateStrategy start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StateStrategy end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public StateStrategy move(Team team, String input) {
        throw new UnsupportedOperationException();
    }

    @Override
    public StateStrategy status() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
