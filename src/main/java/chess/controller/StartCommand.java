package chess.controller;

import chess.domain.chessgame.ChessGame;
import chess.view.OutputView;

import java.util.List;

public class StartCommand implements Command {

    @Override
    public ChessGame execute(final ChessGame chessGame, final List<String> input, final OutputView outputView) {
        ChessGame startGame = chessGame.start();
        outputView.printBoard(startGame.findChessBoard());
        return startGame;
    }

}
