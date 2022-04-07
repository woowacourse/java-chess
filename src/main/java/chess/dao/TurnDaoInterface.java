package chess.dao;

public interface TurnDaoInterface {

    void save(String team);

    String find();

    void delete();

    void update(String nowTurn, String nextTurn);
}
