package chess.machine;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.generator.ChessSpaceGenerator;
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
        ChessBoard chessBoard = new ChessBoard(new ChessSpaceGenerator());
        command.conductCommand(chessBoard, outputView);

        playChess(chessBoard);
    }

    private void validateFirstCommand(Command command) {
        if (command.getClass() != Start.class) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }
    }

    private void playChess(ChessBoard chessBoard) {
        while (chessBoard.isActive()) {
            Command command = inputView.readCommand();
            command.conductCommand(chessBoard, outputView);
        }
    }
}
