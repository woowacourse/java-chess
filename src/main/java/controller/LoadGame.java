package controller;

import dao.ChessDao;
import domain.chessgame.ChessGame;
import domain.position.Position;
import domain.position.PositionFactory;
import dto.ChessBoardDto;
import dto.MoveDto;
import view.OutputView;

import java.util.List;

public final class LoadGame extends GameStatus {

    private final ChessDao chessDao;

    LoadGame(final ChessGame chessGame, final ChessDao chessDao) {
        super(chessGame);
        this.chessDao = chessDao;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        loadGame(chessGame);
        final ChessBoardDto chessBoardDTO = ChessBoardDto.from(chessGame.getChessBoard());

        OutputView.printNotice("불러온 게임을 시작합니다.");
        OutputView.printChessBoard(chessBoardDTO);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

    private void loadGame(final ChessGame chessGame) {
        final List<MoveDto> allMove = chessDao.findAllMove();

        for (MoveDto move : allMove) {
            final Position source = PositionFactory.createPosition(move.getSource());
            final Position target = PositionFactory.createPosition(move.getTarget());

            chessGame.move(source, target);
        }
    }

}
