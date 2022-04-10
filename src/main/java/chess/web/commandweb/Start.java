package chess.web.commandweb;

import chess.console.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class Start implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {

        // finished상태에서 [종료]클릭하면 ,스위치가 꺼지고 메인으로 가는데
        // -> 스위치가 꺼진 상태에서 시작하면 제대로 시작안된다.
        // -> 스위치를 껐으면, 새로 시작할 땐, 스위치 다시 올려주자(콘솔에선 신경안쓰던 부분)

        if (chessGame.isEndInGameOff()) {
            chessGame.gameSwitchOn();
        }
        chessGame.run();

        return returnModelToState.get();
    }
}
