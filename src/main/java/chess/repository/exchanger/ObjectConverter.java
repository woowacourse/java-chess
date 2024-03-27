package chess.repository.exchanger;

public interface ObjectConverter<T, R> {

    T convertToObject(R r);

    R convertToData(T t);
}
