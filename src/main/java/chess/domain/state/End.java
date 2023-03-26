package chess.domain.state;

public class End extends Finished {

    @Override
    public String findWinner() {
        return null;
    }
}
