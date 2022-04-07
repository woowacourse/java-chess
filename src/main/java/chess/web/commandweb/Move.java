package chess.web.commandweb;

import chess.console.ChessGame;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class Move implements WebCommandGenerator {
    @Override
    public Map<String, Object> execute(final String command,
                                       final ChessGame chessGame,
                                       final Supplier<Map<String, Object>> returnModelToState) {
        final Positions movePositions = Positions.from(command);
        chessGame.move(movePositions);

        final Map<String, Object> model = returnModelToState.get();
        checkKingStateInRunning(chessGame, model);
        return model;
    }

    private void checkKingStateInRunning(final ChessGame chessGame, final Map<String, Object> model) {
        if (chessGame.isRunning()) {
            checkKingState(chessGame, model);
        }
    }

    private void checkKingState(final ChessGame chessGame,
                                final Map<String, Object> model) {
        if (chessGame.isKingChecked()) {
            model.put("message", "킹이 체크 되었습니다.");
        }

        final List<Position> kingCheckMatedPositions = chessGame.findKingCheckMatedPosition();
        if (chessGame.isAnyMovablePositionKingCheckmated(kingCheckMatedPositions)) {
            model.put("message", "현재 턴의 킹이 체크되었습니다");
            if (chessGame.isAllMovablePositionKingCheckMated(kingCheckMatedPositions)) {
                //chessGame.gameSwitchOff(); // 스위치를 꺼버리면, finished를 안간다.
                chessGame.end(); //  finished상태를 만들어야 -> 출력꾸러미에서 finished를 인식해서 작동함. 스위치 바로 오프하면 안됨.
                model.put("message", "현재 턴의 킹이 체크메이트 되어 게임을 종료합니다.");
                model.put("isRunning", false);
            }
        }
    }
}
