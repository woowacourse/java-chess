package othercase;

import domain.board.Position;
import domain.game.ChessGame;
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
        try {
            GameCommand gameCommand = inputCommand();
            gameCommand.execute(this);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            run();
        }
    }

    private GameCommand inputCommand() {
        try {
            String[] inputValues = inputView.inputCommand().split(" ");
            return GameCommandType.of(inputValues);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return inputCommand();
        }
    }

    public void gameStart() {
        if (isGameRunning()) {
            throw new IllegalArgumentException("이미 게임이 진행중입니다.");
        }

        chessGame.gameStart();
        outputView.printWelcomeMessage();

        while (chessGame.isGameRunning()) {
            BoardDto boardDto = BoardDto.from(chessGame.piecePositions());
            outputView.printBoard(boardDto);
            playTurn();
        }
    }

    private boolean isGameRunning() {
        return chessGame.isGameRunning();
    }

    private void playTurn() {
        try {
            GameCommand gameCommand = inputCommand();
            gameCommand.execute(this);
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            playTurn();
        }
    }

    public void movePiece(final Position source, final Position destination) {
        if (!chessGame.isGameRunning()) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }

        chessGame.movePiece(source, destination);
    }

    public void endGame() {
        chessGame.gameEnd();
    }
}
