package chess.dao;

public interface TurnDao {

    void update(String nowTurn, String nextTurn);

    String getTurn();

    void reset();
}