package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public class Finished extends Commend {

    public Finished(Pieces pieces) {
        super(pieces);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState move(Team team, String input) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
