package chess.domain.DTO;

public class MoveResultDTO {

    boolean isMove;

    public MoveResultDTO(boolean isMove) {
        this.isMove = isMove;
    }

    public boolean isMove() {
        return isMove;
    }
}
