package controller.command;

import dao.ChessBoardDao;
import domain.chessGame.ChessBoard;
import domain.chessGame.ScoreCalculator;
import view.OutputView;

public class GameEnd extends GameCommand {

    protected GameEnd(ChessBoardDao chessBoardDao) {
        super(chessBoardDao);
    }

    @Override
    public Command execute() {
        ChessBoard chessBoard = chessBoardDao.select();
        if (chessBoard.isGameEnded()) {
            showGameEndResult(chessBoard);
            chessBoardDao.delete();
        }
        return this;
    }

    private static void showGameEndResult(ChessBoard chessBoard) {
        OutputView.printEndGameMessage();
        ScoreCalculator scoreCalculator = new ScoreCalculator(chessBoard.getBlackPieces(), chessBoard.getWhitePieces());
        OutputView.printStatusResult(scoreCalculator.getBlackScore(), scoreCalculator.getWhiteScore());
    }

    @Override
    public Command readNextCommand() {
        throw new UnsupportedOperationException("[ERROR] 게임이 종료되어 명령어를 입력할 수 없습니다.");
    }

    public boolean isEnd() {
        return true;
    }
}
