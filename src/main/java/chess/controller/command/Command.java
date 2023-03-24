package chess.controller.command;


import chess.domain.ChessGame;
import chess.view.OutputView;

public interface Command {
    void execute(final ChessGame chessGame, final OutputView outputView);
}
