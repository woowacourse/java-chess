package chess.dao;

public interface GameStatusDao {

    void update(String nowStatus, String nextStatus);

    String getStatus();

    void reset();
}
