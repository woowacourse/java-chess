package chess.controller;

import chess.model.ChessBoard;
import chess.model.ChessBoardInitializer;
import chess.model.ChessPosition;
import chess.model.GameCommand;
import chess.view.InputView;
import chess.view.MoveArguments;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        GameCommand gameCommand = GameCommand.createFirstGameCommand(inputView.readGameCommand());
        if (gameCommand.isEnd()) {
            return;
        }
        ChessBoardInitializer initializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(initializer.create());
        outputView.printChessBoard(chessBoard);
        while (!gameCommand.isEnd()) {
            List<String> inputs = inputView.readMoveArguments();
            gameCommand = GameCommand.createMoveCommand(inputs.get(0));
            if (gameCommand.isEnd()) {
                return;
            }
            MoveArguments moveArguments = MoveArguments.from(inputs);
            ChessPosition source = moveArguments.createSourcePosition();
            ChessPosition target = moveArguments.createTargetPosition();
            chessBoard.move(source, target);
            outputView.printChessBoard(chessBoard);
        }
    }
}
