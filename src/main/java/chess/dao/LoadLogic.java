package chess.dao;

public interface LoadLogic<T> {
    T load(String gameId);
}
