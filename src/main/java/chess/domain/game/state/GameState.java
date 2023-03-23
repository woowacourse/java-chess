package chess.domain.game.state;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;

import chess.domain.board.Square;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class GameState {

    public static final GameState INIT = new GameState(BLACK, InitState.CACHE);
    public static final GameState START = new GameState(WHITE, StartState.CACHE);

    private final Color turn;
    private final ExecuteState executeState;

    public GameState(final Color turn, final ExecuteState executeState) {
        this.turn = turn;
        this.executeState = executeState;
    }

    public void start() {
        executeState.start();
    }

    public double calculateScoreOfColor(final Color color, final ChessGame chessGame) {
        return executeState.calculateScoreOfColor(color, chessGame);
    }

    public void move(final Square source, final Square destination, final ChessGame chessGame) {
        executeState.move(source, destination, chessGame);
    }

    public void end() {
        executeState.end();
    }

    public GameState nextTurnByMove() {
        return new GameState(turn.reverse(), StartState.CACHE);
    }

    public GameState terminate() {
        return new GameState(turn.reverse(), EndState.CACHE);
    }

    public GameState done() {
        return new GameState(turn.reverse(), DoneState.CACHE);
    }

    public boolean isTurn(final Piece piece) {
        return piece.isSameColor(turn);
    }

    public boolean isRunning() {
        return executeState.isRunning();
    }

    public Color getTurnColor() {
        return turn;
    }
}
