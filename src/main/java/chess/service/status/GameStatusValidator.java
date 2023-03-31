package chess.service.status;

import static chess.service.status.GameStatus.OVER;
import static chess.service.status.GameStatus.READY;
import static chess.service.status.GameStatus.RUNNING;

public class GameStatusValidator {

    public static void validateStart(GameStatus gameStatus) {
        if (gameStatus == RUNNING) {
            throw new IllegalStateException("이미 게임이 실행중입니다.");
        }
    }

    public static void validateMove(GameStatus gameStatus) {
        if (gameStatus != RUNNING) {
            throw new IllegalStateException("게임 대기 상태이거나, 오버된 게임입니다.");
        }
    }

    public static void validateEnd(GameStatus gameStatus) {
        if (gameStatus == READY) {
            throw new IllegalStateException("이미 게임 대기 상태입니다.");
        }
    }

    public static void validateStatus(GameStatus gameStatus) {
        if (gameStatus != OVER) {
            throw new IllegalStateException("게임 오버 시에만 결과를 확인할 수 있습니다.");
        }
    }

}
