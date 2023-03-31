package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.chessgame.ChessGame;
import chess.dto.ChessGameDto;
import chess.view.OutputView;

import java.util.List;

public class ListCommand implements Command {

    @Override
    public ChessGame execute(ChessGame chessGame, List<String> input, OutputView outputView) {
        ChessGameDao chessGameDao = new ChessGameDao();
        List<ChessGameDto> chessGames = chessGameDao.selectAll();
        outputView.printAllList(chessGames);
        return chessGame;
    }
}
