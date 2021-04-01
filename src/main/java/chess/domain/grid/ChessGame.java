package chess.domain.grid;

import chess.dao.Chess;
import chess.domain.grid.gridStrategy.EmptyGridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.*;
import chess.domain.position.Position;
import chess.domain.state.BlackTurn;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.WhiteTurn;

import java.util.Map;

public final class ChessGame {
    private static final int POSITION_START_INDEX = 1;
    private static final int POSITION_END_INDEX = 3;
    private Grid grid;
    private GameState gameState;
    private boolean gameOver;
    private Color winner;

    public ChessGame(final Grid grid) {
        this.grid = grid;
        gameState = new Ready();
    }

    public final void reset() {
        this.gameState = new Ready();
        this.gameOver = false;
        this.winner = null;
        this.grid = new Grid(new NormalGridStrategy());
    }

    public final Grid grid() {
        return grid;
    }

    public final void start() {
        gameState = gameState.start();
    }

    public final void end() {
        gameState = gameState.end();
    }

    public final void status() {
        gameState = gameState.status();
    }

    public final void move(final Piece sourcePiece, final Piece targetPiece) {
        gameState = gameState.move(this, sourcePiece.position(), targetPiece.position());
    }

    public final boolean isFinished() {
        return gameState.isFinished();
    }

    public final void winner(final Color color) {
        this.winner = color;
        gameOver = true;
    }

    public final Color getWinner() {
        return this.winner;
    }

    public final boolean isGameOver() {
        return gameOver;
    }

    public final Color turn() {
        if (gameState instanceof WhiteTurn) {
            return Color.WHITE;
        }
        if (gameState instanceof BlackTurn) {
            return Color.BLACK;
        }
        return null;
    }

    public final Map<String, String> pieceMap() {
        return grid.pieceMap();
    }

    public final double score(final Color color) {
        return grid.score(color);
    }

    public final String gridStringify() {
        return grid.stringify();
    }

    public final void load(Chess chess){
        String nameAndPosition = chess.getChess();
        String turn = chess.getTurn();
        grid = new Grid(new EmptyGridStrategy());
        for(int i = 0; i < nameAndPosition.length(); i += POSITION_END_INDEX){
            char name = nameAndPosition.charAt(i);
            String position = nameAndPosition.substring(i+ POSITION_START_INDEX,i+ POSITION_END_INDEX);
            Position piecePosition = new Position(position);
            distributePieces(grid, name, piecePosition);
        }
        assignTurn(turn);

    }

    private void assignTurn(String turn) {
        if(turn.equals("BLACK")){
            gameState = new BlackTurn();
            return;
        }
        gameState = new WhiteTurn();
    }

    private void distributePieces(final Grid grid, char name, Position piecePosition){
        if(name=='b'){
            grid.assign(new Bishop(Color.WHITE, piecePosition), piecePosition);
        }
        if(name=='B'){
            grid.assign(new Bishop(Color.BLACK, piecePosition), piecePosition);
        }
        if(name=='k'){
            grid.assign(new King(Color.WHITE, piecePosition), piecePosition);
        }
        if(name=='K'){
            grid.assign(new King(Color.BLACK, piecePosition), piecePosition);
        }
        if(name=='p'){
            grid.assign(new Pawn(Color.WHITE, piecePosition), piecePosition);
        }
        if(name=='P'){
            grid.assign(new Pawn(Color.BLACK, piecePosition), piecePosition);
        }
        if(name=='q'){
            grid.assign(new Queen(Color.WHITE, piecePosition), piecePosition);
        }
        if(name=='Q'){
            grid.assign(new Queen(Color.BLACK, piecePosition), piecePosition);
        }
        if(name=='r'){
            grid.assign(new Rook(Color.WHITE, piecePosition), piecePosition);
        }
        if(name=='R'){
            grid.assign(new Rook(Color.BLACK, piecePosition), piecePosition);
        }
        if(name=='n'){
            grid.assign(new Knight(Color.WHITE, piecePosition), piecePosition);
        }
        if(name=='N'){
            grid.assign(new Knight(Color.BLACK, piecePosition), piecePosition);
        }
        if(name=='.'){
            grid.assign(new Empty(piecePosition), piecePosition);
        }
    }
}
