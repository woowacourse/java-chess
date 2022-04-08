package chess.dao;

public class FakeTurnDao implements TurnDao {

    private String turn;

    public FakeTurnDao() {
        this.turn = "white";
    }

    @Override
    public String getCurrentTurn() {
        return turn;
    }

    @Override
    public void updateTurn(String currentTurn, String previousTurn) {
        turn = currentTurn;
    }
}
