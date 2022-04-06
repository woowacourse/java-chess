package chess.dao;

public interface ConnectionManager {

    <T> T executeQuery(ConnectionMapper<T> connectionFunction);
}
