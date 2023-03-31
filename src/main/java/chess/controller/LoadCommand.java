package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

import java.util.List;

public class LoadCommand implements Command{
    public static final int COMMAND_GAME_ID_INDEX = 1;

    @Override
    public ChessGame execute(final ChessGame chessGame, List<String> input, OutputView outputView) {
        ChessGameDao chessGameDao = new ChessGameDao();
        Long id = Long.parseLong(input.get(COMMAND_GAME_ID_INDEX));
        ChessGame loadChessGame = chessGameDao.select(id);
        outputView.printBoard(loadChessGame.findChessBoard());
        return loadChessGame;
    }
}
