package chess.dao;

import java.util.List;

public interface RoomDao<T> {

    List<T> findAllWithRunning();

    int deleteAll();

    T save(T room);

    T getById(int roomId);
}
