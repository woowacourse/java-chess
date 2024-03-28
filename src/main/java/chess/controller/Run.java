package chess.controller;

import chess.model.board.ChessBoard;
import chess.model.evaluation.PositionEvaluation;
import chess.model.board.Turn;
import chess.model.position.Position;
import chess.view.input.GameArguments;
import chess.view.input.GameCommand;
import chess.view.input.InputView;
import chess.view.input.MoveArguments;
import chess.view.output.OutputView;

public class Run implements GameState {
    private final ChessBoard chessBoard;
    private final Turn turn;

    public Run(ChessBoard chessBoard, Turn turn) {
        this.chessBoard = chessBoard;
        this.turn = turn;
    }

    @Override
    public GameState run(InputView inputView, OutputView outputView) {
        GameArguments gameArguments = inputView.readMoveArguments();
        GameCommand gameCommand = gameArguments.gameCommand();
        if (gameCommand.isEnd()) {
            return new End();
        }
        if (gameCommand.isMove()) {
            move(gameArguments.moveArguments(), outputView);
            return new Run(chessBoard, turn.getNextTurn());
        }
        evaluateCurrentBoard(outputView);
        return this;
    }

    private void move(MoveArguments moveArguments, OutputView outputView) {
        Position source = moveArguments.createSourcePosition();
        Position target = moveArguments.createTargetPosition();
        chessBoard.move(source, target, turn);
        outputView.printChessBoard(chessBoard);
    }

    private void evaluateCurrentBoard(OutputView outputView) {
        PositionEvaluation positionEvaluation = chessBoard.evaluateCurrentBoard();
        outputView.printPositionEvaluation(positionEvaluation);
    }

    @Override
    public boolean canContinue() {
        return chessBoard.canContinue();
    }
}
