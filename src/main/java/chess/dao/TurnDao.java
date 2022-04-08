package chess.dao;


public interface TurnDao {

    String getCurrentTurn();

    void updateTurn(final String currentTurn, final String previousTurn);
}
