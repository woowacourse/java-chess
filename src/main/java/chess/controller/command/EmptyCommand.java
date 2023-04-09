package chess.controller.command;

import chess.domain.chessgame.ChessGame;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;

public final class EmptyCommand extends AbstractCommand {

    private static final EmptyCommand INSTANCE = new EmptyCommand();

    private EmptyCommand() {
    }

    public static EmptyCommand getInstance() {
        return INSTANCE;
    }

    @Override
    public ChessGame execute(final InputView inputView, final OutputView outputView, final ChessGameService chessGameService, final ChessGame chessGame) {
        throw new UnsupportedOperationException("실행 불가능한 명령입니다");
    }
}
