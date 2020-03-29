package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Team;
import chess.domain.result.GameResult;
import chess.domain.util.Run;
import chess.exception.BlankMoveUnsupportedException;
import chess.exception.MoveCommandWhenBoardNullException;
import chess.exception.PieceImpossibleMoveException;
import chess.exception.TakeTurnException;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
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
        Team currentTurn = Team.WHITE;

        Run runner;
        while ((runner = inputCommandWithValidation()).isNotEnd()) {
            if (runner.isStart()) {
                board = BoardFactory.createBoard();
                OutputView.printBoard(board.getBoard());
            }
            try {
                if (runner.isMove()) {
                    board = board.movePiece(inputCommand[FROM_POSITION_INDEX], inputCommand[TO_POSITION_INDEX], currentTurn);
                    currentTurn = reverseTurn(currentTurn);
                    OutputView.printBoard(board.getBoard());
                }
            } catch (BlankMoveUnsupportedException | MoveCommandWhenBoardNullException |
                    PieceImpossibleMoveException | TakeTurnException e) {
                OutputView.printExceptionMessage(e.getMessage());
            }

            if (runner.isStatus()) {
                OutputView.printTeamScore(gameResult.calculateScore(board, Team.WHITE),
                        gameResult.calculateScore(board, Team.BLACK));
            }

            if (board.isFinished()) {
                OutputView.printGameFinish();
                break;
            }
        }
    }

    private Team reverseTurn(Team currentTurn) {
        if (currentTurn == Team.WHITE) {
            return Team.BLACK;
        }
        return Team.WHITE;
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
}
