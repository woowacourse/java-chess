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

        if (statusCommand.isNewGame()) {
            playNewGame();
        }

        if (statusCommand.isSavedGame()) {
            playSavedGame();
        }
    }

    private void playNewGame() {
        boardService.delete(1);

        ChessGame chessGame = new ChessGame(BoardFactory.createBoard(), true);
        outputView.printStartMessage();

        Command command = inputView.readGameCommand();
        playChess(chessGame, command);

        resultView.printGameEnd();
    }

    private void playSavedGame() {
        ChessGame chessGame = new ChessGame(boardService.findById(1), boardService.isLowerTeamTurnByBoardId(1));

        outputView.printStartMessage();
        outputView.printBoard(chessGame.getBoard());

        Command command = inputView.readGameCommand();
        playChess(chessGame, command);

        resultView.printGameEnd();
    }


    private void playChess(ChessGame chessGame, Command command) {
        while (true) {
            if (isGameEndCase(chessGame, command)) {
                return;
            }

            chessGame = createNewChessGame(chessGame, command);
            tryChessMove(chessGame, command);
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
            boardService.delete(1);
            boardService.save(1, chessGame.getBoard(), chessGame.isLowerTeamTurn());
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

    private boolean isGameDoneCase(final ChessGame chessGame) {
        if (isKingDead(chessGame)) {
            resultView.printGameEndWithDeleting();
            boardService.delete(1);
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

    private void tryChessMove(final ChessGame chessGame, final Command command) {
        if (!command.isMove()) {
            return;
        }

        try {
            chessGame.move(command.findSelectedPiece(), command.findDestination());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private ChessGame createNewChessGame(final ChessGame chessGame, final Command command) {
        if (command.isCreateNewGame()) {
            chessGame.initGame();
        }
        return chessGame;
    }
}
