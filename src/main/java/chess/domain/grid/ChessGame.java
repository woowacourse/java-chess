package chess.domain.grid;

import chess.domain.grid.gridStrategy.EmptyGridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.BlackTurn;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.domain.state.WhiteTurn;
import chess.entity.Chess;

import java.util.Map;

public final class ChessGame {
    private static final int POSITION_START_INDEX = 1;
    private static final int POSITION_END_INDEX = 3;
    private static final String TURN_BLACK = "BLACK";
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

    public final void load(Chess chess) {
        String nameAndPosition = chess.getChess();
        String turn = chess.getTurn();
        grid = new Grid(new EmptyGridStrategy());
        for (int i = 0; i < nameAndPosition.length(); i += POSITION_END_INDEX) {
            char name = nameAndPosition.charAt(i);
            String position = nameAndPosition.substring(i + POSITION_START_INDEX, i + POSITION_END_INDEX);
            Position piecePosition = new Position(position);
            grid.assign(PieceMapper.of(name, piecePosition), piecePosition);
        }
        assignTurn(turn);
    }

    private void assignTurn(String turn) {
        if (turn.equals(TURN_BLACK)) {
            gameState = new BlackTurn();
            return;
        }
        gameState = new WhiteTurn();
    }
}
