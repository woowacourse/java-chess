package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.player.Players;

import java.util.List;

public class ChessManager {
    private Players players;
    private boolean isGameEnd;

    public ChessManager() {
    }

    public void initNewGame(){
        players = new Players();
        isGameEnd = false;
    }

    public void move(final Position source, final Position target) {
        players.validateTurn(source);
        players.move(source, target);
        players.changeTurn();
        checkGameEnd();
    }

    private void checkGameEnd(){
        isGameEnd = players.isEnd();
    }

    public void makeGameEnd(){
        isGameEnd = true;
    }

    public Status calculateStatus() {
        return new Status(players.whitePlayerScore(), players.blackPlayerScore());
    }

    public List<Position> getReachablePositions(final Position source) {
        return players.getReachablePositions(source);
    }

    public Board board() {
        return players.getBoard();
    }

    public boolean isEnd() {
        return isGameEnd;
    }
}
