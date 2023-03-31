package chess.controller;

import chess.dao.ChessGameDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.GameState;
import chess.view.OutputView;

import java.util.List;

public class StartCommand implements Command {

    @Override
    public ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView) {
        ChessGameDao chessGameDao = new ChessGameDao();
        Board initBoard = BoardFactory.createInitial();
        Long id = chessGameDao.insertNewGame(initBoard);
        ChessGame startGame = new ChessGame(id, GameState.RUNNING, initBoard);
        outputView.printBoard(startGame.findChessBoard());
        return startGame;
    }

}
