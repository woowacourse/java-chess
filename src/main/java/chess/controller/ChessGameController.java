package chess.controller;

import chess.domain.commnad.Command;
import chess.domain.commnad.GameStatusCommand;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
import chess.service.BoardService;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.ResultView;

public class ChessGameController {

    private static final int BOARD_ID = 1;

    private final InputView inputView;
    private final OutputView outputView;
    private final ResultView resultView;
    private final BoardService boardService;

    public ChessGameController(final InputView inputView,
                               final OutputView outputView,
                               final ResultView resultView,
                               final BoardService boardService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.resultView = resultView;
        this.boardService = boardService;
    }

    public void run() {
        GameStatusCommand statusCommand = inputView.readStatusOfGame();
        outputView.printStartMessage();

        ChessGame chessGame = loadChessGame(statusCommand);
        playChess(chessGame);

        resultView.printGameEnd();
    }

    private ChessGame loadChessGame(final GameStatusCommand statusCommand) {
        if (statusCommand.isSavedGame()) {
            ChessGame chessGame = new ChessGame(boardService.findById(BOARD_ID), boardService.isLowerTeamTurnByBoardId(BOARD_ID));
            outputView.printBoard(chessGame.getBoard());
            return chessGame;
        }

        return new ChessGame(BoardFactory.createBoard(), true);
    }


    private void playChess(ChessGame chessGame) {
        Command command = inputView.readGameCommand();

        while (!isGameEndCase(chessGame, command)) {
            chessGame = checkCreateNewGame(chessGame, command);

            checkMovePiece(chessGame, command);
            outputView.printBoard(chessGame.getBoard());

            if (isGameDoneCase(chessGame)) {
                break;
            }

            command = inputView.readGameCommand();
        }
    }

    private boolean isGameEndCase(final ChessGame chessGame, final Command command) {
        if (isGameEnd(chessGame, command)) {
            resultView.printGameEndWithSaving();
            boardService.delete(BOARD_ID);
            boardService.save(BOARD_ID, chessGame.getBoard(), chessGame.isLowerTeamTurn());
            return true;
        }
        return false;
    }

    private boolean isGameEnd(final ChessGame chessGame, final Command command) {
        if (isCommandStatus(chessGame, command) || command.isGameStop()) {
            return true;
        }

        return false;
    }

    private boolean isCommandStatus(final ChessGame chessGame, final Command command) {
        if (command.isStatus()) {
            resultView.printScore(chessGame.calculateScoreOfUpperTeam(), chessGame.calculateScoreOfLowerTeam());
            resultView.printWinner(chessGame.calculateScoreOfUpperTeam(),
                    chessGame.calculateScoreOfLowerTeam());
            return true;
        }
        return false;
    }

    private ChessGame checkCreateNewGame(final ChessGame chessGame, final Command command) {
        if (command.isCreateNewGame()) {
            chessGame.initGame();
        }
        return chessGame;
    }

    private boolean isGameDoneCase(final ChessGame chessGame) {
        if (isKingDead(chessGame)) {
            resultView.printGameEndWithDeleting();
            boardService.delete(BOARD_ID);
            return true;
        }
        return false;
    }

    private boolean isKingDead(final ChessGame chessGame) {
        if (chessGame.isKingDead() && chessGame.isUpperTeamWin()) {
            resultView.printWinnerIsUpperTeam();
            return true;
        }

        if (chessGame.isKingDead() && !chessGame.isUpperTeamWin()) {
            resultView.printWinnerIsLowerTeam();
            return true;
        }

        return false;
    }

    private void checkMovePiece(final ChessGame chessGame, final Command command) {
        if (!command.isMove()) {
            return;
        }

        try {
            chessGame.move(command.findSelectedPiece(), command.findDestination());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
