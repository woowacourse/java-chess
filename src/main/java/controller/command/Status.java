package controller.command;

import dao.ChessBoardDao;
import domain.chessGame.ChessBoard;
import domain.chessGame.ScoreCalculator;
import view.OutputView;

public class Status extends GameCommand {

    protected Status(ChessBoardDao chessBoardDao) {
        super(chessBoardDao);
    }

    @Override
    public Command execute() {
        ChessBoard chessBoard = chessBoardDao.select();
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard.getBlackPieces(), chessBoard.getWhitePieces());
        OutputView.printStatusResult(scoreCalculator.getBlackScore(), scoreCalculator.getWhiteScore());
        return readNextCommand();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
