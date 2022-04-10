package chess.dao;

import java.util.List;

public interface RoomDao<T> {

    List<T> findAll();

    int deleteAll();

    T save(T room);

    T getById(int roomId);
}
