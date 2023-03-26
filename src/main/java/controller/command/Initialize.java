package controller.command;

import dao.ChessBoardDao;
import domain.chessGame.ChessBoard;
import domain.position.Position;
import view.OutputView;

public class Initialize extends GameCommand {

    protected Initialize(ChessBoardDao chessBoardDao) {
        super(chessBoardDao);
    }

    @Override
    public Command execute() {
        ChessBoard chessBoard = chessBoardDao.select();
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
        return readNextCommand();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
