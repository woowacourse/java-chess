package domain.game;

import domain.game.state.BlackTurn;
import domain.game.state.GameState;
import domain.game.state.WhiteTurn;
import domain.position.Position;

public class ChessGame {
    private GameState state;
    private final Board board;

    protected ChessGame(GameState state, Board board) {
        this.state = state;
        this.board = board;
    }

    public ChessGame() {
        this(new WhiteTurn(), BoardInitializer.init());
    }

    public void move(Position source, Position destination) {
        state.move(board, source, destination);
        state = nextStateOf(state);
    }


    private GameState nextStateOf(GameState state) {
        if (state.isTurnOf(TeamColor.WHITE)) {
            return new BlackTurn();
        }
        return new WhiteTurn();
    }


    public TeamColor currentPlayingTeam() {
        return state.currentTurn();
    }
}
