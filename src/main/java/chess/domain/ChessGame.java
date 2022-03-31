package chess.domain;

import chess.domain.State.Finish;
import chess.domain.State.Ready;
import chess.domain.State.State;
import chess.domain.piece.Color;
import java.util.List;

public class ChessGame {

    private State state;
    private ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.state = new Ready();
        this.chessBoard = chessBoard;
    }

    public State playGameByCommand(GameCommand gameCommand) {
        return state = state.proceed(chessBoard, gameCommand);
    }

    public State checkFinished() {
        if (isEndGame()) {
            State winnerState = state;
            state = new Finish();
            return winnerState;
        }
        return state;
    }

    public boolean isEndGame() {
        return chessBoard.isEnd();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public double calculateScore(Color color) {
        List<Pieces> piecesOnColumns = chessBoard.getPiecesOnColumns(color);
        ScoreCalculator calculator = ScoreCalculator.getInstance();

        double score = calculator.calculateColumns(piecesOnColumns);
        return score;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
