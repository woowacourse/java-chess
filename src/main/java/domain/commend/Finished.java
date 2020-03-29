package domain.commend;

import domain.commend.exceptions.CommendTypeException;
import domain.pieces.Pieces;
import domain.team.Team;

public class Finished extends GameState {

    public Finished(Pieces pieces) {
        super(pieces);
    }

    @Override
    public StateStrategy start() {
        throw new CommendTypeException("");
    }

    @Override
    public StateStrategy end() {
        throw new CommendTypeException("");
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
        return true;
    }
}
