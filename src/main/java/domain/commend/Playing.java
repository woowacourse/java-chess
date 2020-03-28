package domain.commend;

import domain.pieces.Pieces;
import domain.team.Team;

public class Playing extends Commend {

    public Playing(Pieces pieces) {
        super(pieces);
    }

    @Override
    public GameState start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GameState end() {
        return new Finished(pieces);
    }

    @Override
    public GameState move(Team team, String input) {
        Move.movePiece(team, pieces, input);
        return this;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
