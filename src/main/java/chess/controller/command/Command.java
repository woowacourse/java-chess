package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public interface Command {

    static Command emptyCommand() {
        return EmptyCommand.getInstance();
    }

    ChessGame execute(InputView inputView, OutputView outputView, ChessGameService chessGameService, ChessGame chessGame);

    boolean isGameEnd();
}
