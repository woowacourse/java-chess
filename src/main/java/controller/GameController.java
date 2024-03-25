package controller;

import domain.game.ChessGame;
import domain.game.GameCommand;
import domain.game.GameCommandType;
import dto.BoardDto;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ChessGame chessGame;

    public GameController(final InputView inputView, final OutputView outputView, final ChessGame chessGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.chessGame = chessGame;
    }

    public void run() {
        initGame();

        if (chessGame.isGameRunning()) {
            gameStart();
        }
    }

    private void initGame() {
        try {
            GameCommand gameCommand = inputCommand();
            gameCommand.execute(chessGame);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            initGame();
        }
    }

    private GameCommand inputCommand() {
        String[] inputValues = inputView.inputCommand().split(" ");
        return GameCommandType.of(inputValues);
    }

    private void gameStart() {
        outputView.printWelcomeMessage();
        while (chessGame.isGameRunning()) {
            BoardDto boardDto = BoardDto.from(chessGame.piecePositions());
            outputView.printBoard(boardDto);
            playTurn();
        }
    }

    private void playTurn() {
        try {
            GameCommand gameCommand = inputCommand();
            gameCommand.execute(chessGame);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            playTurn();
        }
    }
}
