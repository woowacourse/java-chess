package chess.domain;

public class MoveStateDTO {

    private String source;
    private String target;
    private int moveCount;
    private Player player;

    public MoveStateDTO(MoveState moveState) {
        this(moveState, moveState.getMoveCount());
    }

    private MoveStateDTO(MoveState moveState, MoveCount moveCount) {
        this.moveCount = moveCount.getMoveCount();
        this.source = moveState.getSource().toString();
        this.target = moveState.getTarget().toString();
        this.player = moveState.getPlayer();
    }

    public Player getPlayer() {
        return player;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
