package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.result.GameResult;
import chess.domain.util.Run;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private Board board;
    private GameResult gameResult;

    public ChessGame() {
        this.gameResult = new GameResult();
    }

    public void run() {
        OutputView.printInputStartGuideMessage();
        String[] inputCommand = InputView.inputCommand();
        Run runner = Run.of(inputCommand[0]);
        int turnFlag = 0;

        while(runner.isNotEnd()) {
            if (runner.isStart()) {
                board = BoardFactory.createBoard();
            }
            try {
                if (runner.isMove()) {
                    board = board.movePiece(inputCommand[1], inputCommand[2], turnFlag);
                    turnFlag = 1 - turnFlag;
                }
            } catch (IllegalAccessException | UnsupportedOperationException e) {
                System.out.println(e.getMessage());
            }
            OutputView.printBoard(board);
            gameTerminateWhenFinished();

            if (runner.isStatus()) {
                OutputView.printTeamScore(gameResult.calculateScore(board, Team.WHITE),
                        gameResult.calculateScore(board, Team.BLACK));
            }
            inputCommand = InputView.inputCommand();
            runner = Run.of(inputCommand[0]);
        }
    }

    private void gameTerminateWhenFinished() {
        if (board.isFinished()) {
            OutputView.printGameFinish();
            System.exit(0);
        }
    }
}
