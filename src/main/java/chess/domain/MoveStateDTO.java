package chess.domain;

public class MoveStateDTO {

    private Square source;
    private Square target;
    private int moveCount;

    public MoveStateDTO(MoveState moveState) {
        this(moveState, moveState.getMoveCount());
    }

    private MoveStateDTO(MoveState moveState, MoveCount moveCount) {
        this.moveCount = moveCount.getMoveCount();
        this.source = moveState.getSource();
        this.target = moveState.getTarget();
    }

    public Square getSource() {
        return source;
    }

    public Square getTarget() {
        return target;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
