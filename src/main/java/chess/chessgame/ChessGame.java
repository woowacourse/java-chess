package chess.chessgame;

import chess.dto.ScoreDto;
import chess.piece.Color;
import chess.piece.Piece;
import chess.state.Finish;
import chess.state.Ready;
import chess.state.State;

import java.util.Map;

public class ChessGame {

    private static final double MINUS_SCORE_OF_SAME_Y_PAWN = 0.5;

    private final Turn turn;
    private State state;

    public ChessGame() {
        this.state = new Ready();
        this.turn = new Turn();
    }

    public void start() {
        state = state.start();
    }

    public void move(MovingPosition movingPosition) {
        state = state.move(movingPosition, turn);
    }

    public void end() {
        state = new Finish(state.getChessboard());
    }

    public Map<Position, Piece> getChessBoard() {
        return state.getChessboard().getBoard();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public ScoreDto computeScore() {
        double scoreOfBlack = state.computeScore(Color.BLACK, MINUS_SCORE_OF_SAME_Y_PAWN);
        double scoreOfWhite = state.computeScore(Color.WHITE, MINUS_SCORE_OF_SAME_Y_PAWN);

        if (scoreOfBlack > scoreOfWhite) {
            return new ScoreDto(scoreOfBlack, scoreOfWhite, Color.BLACK);
        }
        if (scoreOfBlack < scoreOfWhite) {
            return new ScoreDto(scoreOfBlack, scoreOfWhite, Color.WHITE);
        }

        return new ScoreDto(scoreOfBlack, scoreOfWhite);
    }


}
