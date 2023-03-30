package chess.domain;

import static chess.domain.GameState.DONE;
import static chess.domain.GameState.PLAY;
import static chess.domain.GameState.READY;

import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

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

    public double calculateScore(final List<Piece> pieces, final List<Integer> counts) {
        return chessGame.calculateScoreBy(pieces, counts);
    }

    public List<Team> winningTeams(final Map<Team, Double> scores) {
        return chessGame.determineWinningTeam(scores);
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
