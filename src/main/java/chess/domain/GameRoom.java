package chess.domain;

import static chess.domain.GameState.DONE;
import static chess.domain.GameState.PLAY;
import static chess.domain.GameState.READY;

import java.util.List;

public class GameRoom {

    private final int roomNumber;
    private final ChessGame chessGame;
    private GameState state;

    public GameRoom(final int roomNumber, final ChessGame chessGame, GameState state) {
        this.roomNumber = roomNumber;
        this.chessGame = chessGame;
        this.state = state;
    }

    public void move(String source, String target) {
        chessGame.movePiece(source, target);
    }

    public List<Team> winningTeams() {
        return chessGame.determineWinningTeam();
    }

    public void updateState() {
        if (this.state == PLAY) {
            this.state = DONE;
        }

        if (this.state == READY) {
            this.state = PLAY;
        }
    }

    public int roomNumber() {
        return roomNumber;
    }

    public ChessGame chessGame() {
        return chessGame;
    }

    public GameState state() {
        return state;
    }

    public Team turn() {
        return chessGame.team();
    }

    public Board board() {
        return chessGame.getBoard();
    }
}
