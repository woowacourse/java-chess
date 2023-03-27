package chess.domain.state;

public class End extends Finished {

    @Override
    public String findCurrentTurn() {
        return null;
    }
}
