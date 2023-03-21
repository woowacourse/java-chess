package chess.controller;

import chess.controller.service.BoardService;
import chess.domain.commnad.Command;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
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

        String savingGame = inputView.readSavingGame();

        ChessGame chessGame;
        if (savingGame.equals("1")) {
            // 새로운 게임 하기
            chessGame = new ChessGame(BoardFactory.createBoard());
            outputView.printStartMessage();

            Command command = inputView.readGameCommand();

            playChess(chessGame, command);

            resultView.printGameEnd();
        }

        if (savingGame.equals("2")) {
            // 저장된 게임 하기
            chessGame = new ChessGame(boardService.findById(1));
            outputView.printStartMessage();
            outputView.printBoard(chessGame.getBoard());

            Command command = inputView.readGameCommand();

            playChess(chessGame, command);

            resultView.printGameEnd();
        }
    }

    private void playChess(ChessGame chessGame, Command command) {
        while (true) {
            if (isGameEnd(chessGame, command)) {
                System.out.println("게임을 중단했습니다. 현재 게임은 저장됩니다.");
                boardService.save(1, chessGame.getBoard());
                return;
            }

            chessGame = createNewChessGame(chessGame, command);
            tryChessMove(chessGame, command);
            outputView.printBoard(chessGame.getBoard());

            if (isGameDone(chessGame)) {
                System.out.println("게임이 끝났습니다. 기존 게임은 삭제됩니다.");
                boardService.delete(1);
                break;
            }

            command = inputView.readGameCommand();
        }
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

    private boolean isGameDone(final ChessGame chessGame) {
        if (chessGame.isGameDone() && chessGame.isUpperTeamWin()) {
            resultView.printWinnerIsUpperTeam();
            return true;
        }

        if (chessGame.isGameDone()) {
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

    private ChessGame createNewChessGame(ChessGame chessGame, final Command command) {
        if (command.isCreateNewGame()) {
            chessGame.initBoard();
        }
        return chessGame;
    }
}
