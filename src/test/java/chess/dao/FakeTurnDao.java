package chess.dao;

public class FakeTurnDao implements TurnDao {

    private String turn = "white";

    @Override
    public void update(String nowTurn, String nextTurn) {
        turn = nextTurn;
    }

    @Override
    public String getTurn() {
        return turn;
    }

    @Override
    public void reset() {
        turn = "white";
    }
}
