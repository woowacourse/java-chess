package chess.controller;

import chess.domain.game.ChessGame;
import chess.domain.game.OperationType;
import chess.domain.game.Operations;
import chess.domain.position.PositionFactory;
import chess.view.OutputView;

import static chess.view.InputView.inputOperation;

public class ChessController {
    public void run() {
        OutputView.printOperationsFormat();
        if (!beforeStart()) {
            return;
        }
        execute();
        OutputView.printFinish();
    }

    private boolean beforeStart() {
        OperationType operationType = inputOperation().getOperationType();

        operationType.checkFirstOperations();
        if (operationType.isEnd()) {
            OutputView.printFinish();
            return false;
        }
        return true;
    }

    private void execute() {
        ChessGame chessGame = new ChessGame();
        OutputView.printBoard(chessGame.createBoard());

        while (executeOperation(chessGame)) {
            OutputView.printBoard(chessGame.createBoard());
            OutputView.printScore(chessGame.calculateScore());
        }
        if (chessGame.isKingDead()) {
            OutputView.printFinishByKingDead(chessGame.getAliveKingColor());
        }
    }

    private boolean executeOperation(ChessGame chessGame) {
        Operations operations = inputOperation();
        OperationType operationType = operations.getOperationType();

        if(operationType.isMove()) {
            chessGame.move(PositionFactory.of(operations.getFirstArgument()), PositionFactory.of(operations.getSecondArgument()));
        }
        return (operationType.canExecuteMore() && !chessGame.isKingDead());
    }
}