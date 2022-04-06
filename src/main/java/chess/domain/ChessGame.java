package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.Finish;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Map;

public class ChessGame {

    private State state;
    private ChessBoard chessBoard;

    public ChessGame(final ChessBoard chessBoard) {
        this.state = new Ready();
        this.chessBoard = chessBoard;
    }

    public ChessGame(State state, ChessBoard chessBoard) {
        this.state = state;
        this.chessBoard = chessBoard;
    }

    public State playGameByCommand(final GameCommand gameCommand) {
        return this.state = state.proceed(chessBoard, gameCommand);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Color getWinner() {
        return chessBoard.getWinner();
    }

    public boolean isEndGameByPiece() {
        if (chessBoard.isEnd()) {
            state = new Finish();
        }
        return chessBoard.isEnd();
    }

    public double calculateScore(final Color color) {
        final ScoreCalculator calculator = new ScoreCalculator(chessBoard.getPieces());
        return calculator.calculate(color);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Map<String, Piece> chessBoardToMap() {
        return chessBoard.toMap();
    }

    public State getState() {
        return state;
    }
}
