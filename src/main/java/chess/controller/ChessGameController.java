package chess.controller;

import chess.domain.commnad.Command;
import chess.domain.commnad.LoadGameCommand;
import chess.domain.game.ChessGame;
import chess.dto.BoardResultDto;
import chess.dto.BoardSaveDto;
import chess.dto.GameScoreResultDto;
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
        LoadGameCommand loadGameCommand = inputView.readStatusOfGame();
        ChessGame chessGame = loadGame(loadGameCommand);

        outputView.printStartMessage();
        playChess(chessGame);

        resultView.printGameEnd();
    }

    private ChessGame loadGame(final LoadGameCommand loadCommand) {
        if (loadCommand.isSavedGame()) {
            ChessGame chessGame = boardService.findById(BOARD_ID).getChessGame();
            outputView.printBoard(BoardResultDto.toDto(chessGame));
            return chessGame;
        }

        return new ChessGame(BoardFactory.createBoard(), true);
    }


    private void playChess(ChessGame chessGame) {
        Command command = inputView.readGameCommand();

        while (!isGameEndCase(chessGame, command)) {
            chessGame = checkCreateNewGame(chessGame, command);

            checkMovePiece(chessGame, command);
            outputView.printBoard(BoardResultDto.toDto(chessGame));

            if (isGameDone(chessGame)) {
                break;
            }

            command = inputView.readGameCommand();
        }
    }

    private boolean isGameEndCase(final ChessGame chessGame, final Command command) {
        if (isGameEnd(chessGame, command)) {
            resultView.printGameEndWithSaving();
            boardService.delete(BOARD_ID);
            boardService.save(BoardSaveDto.toDto(BOARD_ID, chessGame));
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
            resultView.printScore(GameScoreResultDto.toDto(chessGame));
            resultView.printWinner(GameScoreResultDto.toDto(chessGame));
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

    private boolean isGameDone(final ChessGame chessGame) {
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
