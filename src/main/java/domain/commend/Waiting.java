package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.pieces.Pieces;
import domain.team.Team;

public class Waiting extends GameState {

    public Waiting(Pieces pieces) {
        super(pieces);
    }

    @Override
    public StateStrategy start() {
        return new Playing(pieces);
    }

    @Override
    public StateStrategy end() {
        return new Finished(pieces);
    }

    @Override
    public StateStrategy move(Team team, String input) {
        throw new CommendTypeException("");
    }

    @Override
    public StateStrategy status() {
        throw new CommendTypeException("");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
