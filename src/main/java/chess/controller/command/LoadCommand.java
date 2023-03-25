package chess.controller.command;

import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.Running;
import chess.view.OutputView;

import java.util.List;

public final class LoadCommand implements Command {

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final OutputView outputView = new OutputView();
    private final Long parameters;

    public LoadCommand(List<String> parameters) {
        if (parameters.size() != 1) {
            throw new IllegalArgumentException("방 번호를 하나만 입력해주세요.");
        }
        this.parameters = Long.parseLong(parameters.get(0));
    }

    @Override
    public void execute(ChessGame chessGame) {
        Running gameLoaded = chessGameDao.load(parameters);
        chessGame.setGameId(parameters);
        chessGame.load(gameLoaded);
        outputView.printBoard(chessGame.getBoard());
    }
}
