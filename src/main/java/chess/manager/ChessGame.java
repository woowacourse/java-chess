package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.player.Player;
import chess.domain.player.Scores;
import chess.domain.player.Players;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChessGame {
    private Players players;
    private boolean isGameEnd;

    public ChessGame() {
    }

    public void initNewGame() {
        players = new Players();
        isGameEnd = false;
    }

    public void move(final Position source, final Position target) {
        players.validateTurn(source);
        players.move(source, target);
        players.changeTurn();
        isGameEnd = players.isEnd();
    }

    public Scores scores() {
        return players.scores();
    }

    public List<Position> reachablePositions(final Position source) {
        return players.getReachablePositions(source);
    }

    public Board board() {
        return players.getBoard();
    }

    public void makeGameEnd() {
        isGameEnd = true;
    }

    public boolean isEnd() {
        return isGameEnd;
    }

    public Queue<Player> winner(){
        return new LinkedList<>(players.winner());
    }
}
