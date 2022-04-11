package chess.dao;

public interface StateDao {

    void save(String name);

    String find();

    void delete();

    void update(String white_turn, String black_turn);
}
