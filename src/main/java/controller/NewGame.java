package controller;

import dao.ChessDao;
import domain.chessgame.ChessGame;
import dto.ChessBoardDto;
import view.OutputView;

import java.util.List;

public final class NewGame extends GameStatus {

    private final ChessDao chessDao;

    NewGame(ChessGame chessGame, final ChessDao chessDao) {
        super(chessGame);
        this.chessDao = chessDao;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        chessDao.deleteAll();
        final ChessBoardDto chessBoardDTO = ChessBoardDto.from(chessGame.getChessBoard());

        OutputView.printNotice("새로운 게임을 시작합니다.");
        OutputView.printChessBoard(chessBoardDTO);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
