package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Turn;
import chess.domain.result.GameResult;
import chess.domain.util.Command;
import chess.exception.InvalidPositionException;
import chess.exception.MoveCommandWhenBoardNullException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private static final int COMMAND_INDEX = 0;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    private Board board;
    private GameResult gameResult;
    private String[] inputCommand;

    public ChessGame() {
        this.gameResult = new GameResult();
        board = BoardFactory.createEmptyBoard();
    }

    public void run() {
        OutputView.printInputStartGuideMessage();
        Command command;

        while ((command = inputCommandWithValidation()).isNotEnd()) {
            if (command.isStart()) {
                board.initialize();
                OutputView.printBoard(board.getBoard());
            }
            try {
                if (command.isMove()) {
                    board.move(inputCommand[FROM_POSITION_INDEX], inputCommand[TO_POSITION_INDEX]);
                    OutputView.printBoard(board.getBoard());
                }
            } catch (InvalidPositionException | MoveCommandWhenBoardNullException | PieceImpossibleMoveException | TakeTurnException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }

            if (command.isStatus()) {
                OutputView.printTeamScore(gameResult.calculateScore(board, Turn.WHITE),
                        gameResult.calculateScore(board, Turn.BLACK));
            }

            if (board.isFinished()) {
                OutputView.printGameFinish();
                break;
            }
        }
    }

    private Command inputCommandWithValidation() {
        try {
            inputCommand = InputView.inputCommand();
            return Command.of(inputCommand[COMMAND_INDEX]);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return inputCommandWithValidation();
        }
    }
}
