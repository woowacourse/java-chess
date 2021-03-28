package chess.domain.piece.info;

public interface Direction {
    String DIRECTION_ERROR = "[ERROR] 올바른 방향이 아닙니다.";

    int[] getChangeValues();
}
