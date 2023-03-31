package chess.controller.action;

import chess.domain.game.ChessGame;
import chess.service.ChessService;
import chess.view.OutputView;
import java.util.List;

@FunctionalInterface
public interface GameAction {

    void execute(final ChessService chessService, final ChessGame chessGame, final List<String> gameCommand, final OutputView outputView);
}
