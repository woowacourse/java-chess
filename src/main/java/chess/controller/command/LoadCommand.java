package chess.controller.command;

import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.Running;
import chess.view.OutputView;

import java.util.List;

public final class LoadCommand implements Command {

    private static final int GAME_ID_INDEX = 0;
    private static final int GAME_ID_PARAMETERS_SIZE = 1;

    private final ChessGameDao chessGameDao = new ChessGameDao();
    private final OutputView outputView = new OutputView();
    private final Long parameters;

    public LoadCommand(List<String> parameters) {
        if (parameters.size() != GAME_ID_PARAMETERS_SIZE) {
            throw new IllegalArgumentException("방 번호를 하나만 입력해주세요.");
        }
        this.parameters = Long.parseLong(parameters.get(GAME_ID_INDEX));
    }

    @Override
    public void execute(ChessGame chessGame) {
        Running gameLoaded = chessGameDao.findGameById(parameters);
        chessGame.setGameId(parameters);
        chessGame.load(gameLoaded);
        outputView.printBoard(chessGame.getBoard());
    }
}
