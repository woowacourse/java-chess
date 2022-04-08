package chess.domain;

import chess.domain.board.Position;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.Score;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {
    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        this.state = this.state.start();
    }

    public void load(Map<Position, Piece> board, boolean whiteTurn) {
        this.state = this.state.load(board, whiteTurn);
    }

    public void move(Position sourcePosition, Position targetPosition) {
        this.state = this.state.move(sourcePosition, targetPosition);
    }

    public void end() {
        this.state = this.state.end();
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public Map<Position, Piece> getBoardSquares() {
        return this.state.getBoard().getSquares();
    }

    public Map<Camp, Score> getScores() {
        return this.state.getScores();
    }

    public Camp getWinner() {
        return this.state.getWinner();
    }
}
