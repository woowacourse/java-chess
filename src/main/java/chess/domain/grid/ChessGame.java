package chess.domain.grid;

import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.BlackTurn;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.WhiteTurn;

import java.util.Map;

public class ChessGame {
    private Grid grid;
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

    public Color turn(){
        if(gameState instanceof WhiteTurn){
            return Color.WHITE;
        }
        if(gameState instanceof BlackTurn){
            return Color.BLACK;
        }
        return null;
    }

    public void reset(){
        this.gameState = new Ready();
        this.gameOver = false;
        this.winner = null;
        this.grid = new Grid(new NormalGridStrategy());
    }

    public Map<String, String> pieceMap(){
        return grid.pieceMap();
    }

    public final double score(final Color color) {
        return grid.score(color);
    }
}
