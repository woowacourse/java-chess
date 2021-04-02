package chess.domain.dto;

public interface PieceFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
