package chess.repository.exchanger;

public interface ObjectSpliter<S, U, V> {

    S combine(U u, V v);

    U splitFirst(S s);

    V splitSecond(S s);
}
