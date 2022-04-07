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

        System.err.println("move 커맨드가 req된 것을 확인.");

        chessGame.move(movePositions);

        //(1) ->  new Runnging( 반대진영) 으로 넘어간 상태

        final Map<String, Object> model = returnModelToState.get();
        // (3) 상대차례의 킹을 체크해보자.=========
        if (chessGame.isRunning()) {
            // (4) 무브의 결과로 종료 -> new Ready상태가될 수도 있다. 진행중일때만 검사하자.
            checkKingState(chessGame, model);
        }

        // (2) <- 아직 정보를 뿌려주기 전
        System.err.println("move 커맨드가 return되는 모델은?" + model);
        return model;
    }

    private void checkKingState(final ChessGame chessGame,
                                final Map<String, Object> model) {
        if (chessGame.isKingChecked()) {
            System.err.println("킹이 체크 되었데요");
            model.put("isKingChecked", true);
            model.put("message", "킹이 체크 되었습니다.");
        }

        final List<Position> kingCheckMatedPositions = chessGame.findKingCheckMatedPosition();
        if (chessGame.isAnyKingCheckmated(kingCheckMatedPositions)) {
            System.err.println("킹이 체크 메이트된 자리가 1개라도 있요");
            if (chessGame.isAllKingCheckMated(kingCheckMatedPositions, model)) {
                model.put("message", "현재 턴의 킹이 체크메이트 되어 게임을 종료합니다.");
                model.put("isRunning", false);
            }
            model.put("message", "현재 턴의 킹이 체크되었습니다");
        }
    }
}
