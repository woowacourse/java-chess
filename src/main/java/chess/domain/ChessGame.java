package chess.domain;

import chess.domain.State.Finish;
import chess.domain.State.Ready;
import chess.domain.State.State;
import chess.domain.piece.Color;
import java.util.List;

public class ChessGame {

    private State state;
    private final ChessBoard chessBoard;

    public ChessGame(final ChessBoard chessBoard) {
        this.state = new Ready();
        this.chessBoard = chessBoard;
    }

    public State playGameByCommand(final GameCommand gameCommand) {
        return state = state.proceed(chessBoard, gameCommand);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public State getWinnerState() {
        if (isEndGameByPiece()) {
            final State winnerState = state;
            state = new Finish();
            return winnerState;
        }
        return state;
    }

    public boolean isEndGameByPiece() {
        return chessBoard.isEnd();
    }

    public double calculateScore(final Color color) {
        final List<Pieces> piecesOnColumns = chessBoard.getPiecesOnColumns(color);
        final ScoreCalculator calculator = ScoreCalculator.getInstance();

        return calculator.calculateColumns(piecesOnColumns);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
