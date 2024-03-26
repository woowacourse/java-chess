package chess.machine;

import chess.domain.chessGame.ChessGame;
import chess.domain.chessGame.generator.ChessSpaceGenerator;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessMachine {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessMachine(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printStartGameMessage();
        outputView.printCommandGuideMessage();

        Command command = inputView.readCommand();
        validateFirstCommand(command);
        ChessGame chessGame = new ChessGame(new ChessSpaceGenerator());
        command.conductCommand(chessGame, outputView);

        playChess(chessGame);
        printStatus(chessGame);
    }

    private void validateFirstCommand(Command command) {
        if (command.getClass() != Start.class) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }
    }

    private void playChess(ChessGame chessGame) {
        while (chessGame.isActive()) {
            Command command = inputView.readCommand();
            command.conductCommand(chessGame, outputView);
        }
    }

    private void printStatus(ChessGame chessGame) {
        Command command = Status.of("status");
        command.conductCommand(chessGame, outputView);
    }
}
