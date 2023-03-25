package chess.controller.command.execute;

import chess.domain.game.ChessGame;
import chess.view.OutputView;

public interface ExecuteCommand {

    void execute(final ChessGame chessGame, final OutputView outputView);
}
