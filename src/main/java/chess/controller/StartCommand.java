package chess.controller;

import chess.dao.BoardDAO;
import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

import java.util.List;

public class StartCommand implements Command {

    @Override
    public ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView) {
        BoardDAO boardDAO = new BoardDAO();
        ChessGame startGame = boardDAO.select();
        if (startGame == null) {
            startGame = chessGame.start();
            boardDAO.insert(startGame);
        }
        outputView.printBoard(startGame.findChessBoard());
        return startGame;
    }

}
