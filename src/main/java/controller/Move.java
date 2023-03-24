package controller;

import dao.ChessDao;
import domain.chessgame.ChessGame;
import domain.position.PositionFactory;
import dto.ChessBoardDto;
import dto.MoveDto;
import view.OutputView;

import java.util.List;

public final class Move extends GameStatus {

    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    private final ChessDao chessDao;

    Move(final ChessGame chessGame, final ChessDao chessDao) {
        super(chessGame);
        this.chessDao = chessDao;
    }

    @Override
    public void playTurn(final List<String> inputs) {
        final String source = inputs.get(SOURCE_INDEX);
        final String target = inputs.get(TARGET_INDEX);

        chessGame.move(PositionFactory.createPosition(source), PositionFactory.createPosition(target));
        chessDao.save(new MoveDto(source, target));
        final ChessBoardDto chessBoardDTO = ChessBoardDto.from(chessGame.getChessBoard());

        OutputView.printChessBoard(chessBoardDTO);
    }

    @Override
    public boolean isKeepGaming() {
        return true;
    }

}
