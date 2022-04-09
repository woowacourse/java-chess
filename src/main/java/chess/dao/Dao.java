package chess.dao;

public interface Dao<T> {

    T save(T target);
}
