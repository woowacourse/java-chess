package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.result.GameResult;
import chess.domain.util.Run;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private static final int INITIAL_TURN_OF_WHITE = 0;
    private static final int INITIAL_TURN_OF_BLACK = 1;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;
    private static final int COMMAND_INDEX = 0;

    private Board board;
    private GameResult gameResult;
    private String[] inputCommand;

    public ChessGame() {
        this.gameResult = new GameResult();
    }

    public void run() {
        OutputView.printInputStartGuideMessage();
        Run runner = inputCommandWithValidation();
        int turnFlag = INITIAL_TURN_OF_WHITE;

        while(inputCommandWithValidation().isNotEnd()) {
            if (runner.isStart()) {
                board = BoardFactory.createBoard();
                OutputView.printBoard(board);
            }
            try {
                if (runner.isMove()) {
                    board = board.movePiece(inputCommand[FROM_POSITION_INDEX], inputCommand[TO_POSITION_INDEX], turnFlag);
                    turnFlag = INITIAL_TURN_OF_BLACK - turnFlag;
                    OutputView.printBoard(board);
                    gameTerminateWhenFinished();
                }
            } catch (NullPointerException | IllegalArgumentException | IllegalAccessException | UnsupportedOperationException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }

            if (runner.isStatus()) {
                OutputView.printTeamScore(gameResult.calculateScore(board, Team.WHITE),
                        gameResult.calculateScore(board, Team.BLACK));
            }
        }
    }

    private Run inputCommandWithValidation() {
        try {
            inputCommand = InputView.inputCommand();
            return Run.of(inputCommand[COMMAND_INDEX]);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputCommandWithValidation();
        }
    }

    private void gameTerminateWhenFinished() {
        if (board.isFinished()) {
            OutputView.printGameFinish();
            System.exit(0);
        }
    }
}
