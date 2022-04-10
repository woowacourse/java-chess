package chess.domain.game;

import chess.domain.chessboard.ChessBoard;
import chess.domain.command.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.state.Finish;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private State state;
    private ChessBoard chessBoard;

    public ChessGame(final PiecesGenerator piecesGenerator) {
        init(piecesGenerator);
    }

    public void init(final PiecesGenerator piecesGenerator) {
        this.state = new Ready();
        this.chessBoard = new ChessBoard(piecesGenerator);
    }

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

    public Map<Color, Double> calculateScore() {
        final Map<Color, Double> scores = new HashMap<>();
        final ScoreCalculator calculator = new ScoreCalculator(chessBoard.getPieces());

        scores.put(Color.WHITE, calculator.calculate(Color.WHITE));
        scores.put(Color.BLACK, calculator.calculate(Color.BLACK));
        return scores;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public State getState() {
        return state;
    }
}
