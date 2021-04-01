package chess.websocket.util;

import java.io.IOException;

@FunctionalInterface
public interface TriConsumer<T, U, V> {

    void accept(T t, U u, V v) throws IOException;
}
