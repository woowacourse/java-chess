package chess.domain.grid;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.GameState;
import chess.domain.state.Ready;

public class ChessGame {
    private final Grid grid;
    private GameState gameState;
    private boolean gameOver;
    private Color winner;

    public Grid grid() {
        return grid;
    }

    public ChessGame(Grid grid) {
        this.grid = grid;
        gameState = new Ready();
    }

    public void start(){
        gameState = gameState.start();
    }

    public void end(){
        gameState = gameState.end();
    }

    public void status(){
        gameState = gameState.status();
    }

    public void move(final Piece sourcePiece, final Piece targetPiece){
        gameState = gameState.move(this, sourcePiece.position(), targetPiece.position());
    }

    public boolean isFinished(){
        return gameState.isFinished();
    }

    public void winner(Color color) {
        this.winner = color;
        gameOver = true;
    }

    public Color getWinner(){
        return this.winner;
    }

    public boolean isGameOver(){
        return gameOver;
    }
}
