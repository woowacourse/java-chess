package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.game.state.GameState;
import chess.domain.game.state.ReadyToStart;
import chess.domain.position.Position;

public class ChessGame {

    private GameState state;

    public ChessGame() {
        this.state = new ReadyToStart();
    }

    public void startGame() {
        this.state = state.start();
    }

    public ScoreResult showStatus() {
        return state.status();
    }

    public void movePiece(Position from, Position to) {
        this.state = state.move(from, to);
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
