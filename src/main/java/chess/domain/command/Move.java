package chess.domain.command;

import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.chessgame.ChessGame;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class Move implements CommandGenerator {

    @Override
    public void execute(final String command,
                        final ChessGame chessGame,
                        final Runnable printBoardInfoToState) {
        final Positions movePositions = Positions.from(command);
        chessGame.move(movePositions);

        printBoardInfoToState.run();
    }

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
                chessGame.end();
                model.put("message", "현재 턴의 킹이 체크메이트 되어 게임을 종료합니다.");
                model.put("isRunning", false);
            }
        }
    }
}
