package util;

import domain.game.GameStatus;
import java.util.Arrays;

public enum GameStatusMapper {
    IN_PROGRESS(GameStatus.IN_PROGRESS, "진행 중"),
    WHITE_WIN(GameStatus.WHITE_WIN, "White 승"),
    BLACK_WIN(GameStatus.BLACK_WIN, "Black 승");

    private final GameStatus status;
    private final String text;

    GameStatusMapper(GameStatus status, String text) {
        this.status = status;
        this.text = text;
    }

    public static String convertGameStatusToText(GameStatus gameStatus) {
        return Arrays.stream(GameStatusMapper.values())
                .filter(gameStatusMapper -> gameStatusMapper.status.equals(gameStatus))
                .findFirst()
                .map(gameStatusMapper -> gameStatusMapper.text)
                .orElseThrow(
                        () -> new IllegalStateException("서버 내부 에러 - 존재하지 않는 GameStatus를 Mapping 시도했습니다."));
    }
}
