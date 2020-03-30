package chess.domain.piece;

public interface MoveEventListener {
    void call(MoveEvent moveEvent);
}
