package chess.gamestate;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.view.OutputView;

public abstract class Running implements GameState {

    protected final ChessBoard chessBoard;
    protected final Color color;

    protected Running(ChessBoard chessBoard, Color color) {
        this.chessBoard = chessBoard;
        this.color = color;
    }

    protected Running(Running running) {
        this(running.chessBoard, running.color);
    }

    public static Running createFirstTurnRunning(ChessBoard chessBoard) {
        return new WhiteRunning(chessBoard);
    }

    @Override
    public GameState run(String requestCommand) {
        Command command = Command.toCommand(requestCommand);
        if (command.isEnd()) {
            return new End();
        }
        if (command.isStatus()) {
            OutputView.printChessBoardStatus(chessBoard.calcualteScoreStatus());
            return this;
        }
        if (command.isMove()) {
            movePieceByCommand(command.movePosition(requestCommand));
            return changeMoveNextState();
        }
        throw new IllegalArgumentException("게임 진행상태에서 불가능한 명령어입니다.");
    }

    private void movePieceByCommand(MovePosition movePosition) {
        chessBoard.movePiece(movePosition.getSource(), movePosition.getTarget(), color);
        OutputView.printChessBoard(chessBoard.getPieces());
    }

    private GameState changeMoveNextState() {
        if (chessBoard.isPromotionStatus(color)) {
            OutputView.printPromotionGuide();
            return new Promotion(this);
        }
        if (chessBoard.isFinished()) {
            OutputView.printWinnerAndGameEnd(chessBoard.winner());
            return new End();
        }
        return otherState(this.chessBoard);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    abstract protected Running otherState(ChessBoard chessBoard);
}
