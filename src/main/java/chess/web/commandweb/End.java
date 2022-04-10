package chess.web.commandweb;

import chess.console.ChessGame;
import java.util.Map;
import java.util.function.Supplier;

public final class End implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {

        // 게임 도중 종료하려고 -> 초기화후 end를 시행하면, running만 통과하고 finished외 ready까지 스위치 꺼버림
        // -> ready도 포함해서 통과시켜줘야한다.
        if (!chessGame.isRunning()) {
            chessGame.gameSwitchOff();
        }

        //finished상태로 response할 데이터 정리해두고
        chessGame.end();
        final Map<String, Object> model = returnModelToState.get();

        // 겜 상태는 Ready상태로 만들어놓기
        chessGame.ready();
        return model;
    }
}
