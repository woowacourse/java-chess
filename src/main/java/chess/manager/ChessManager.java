package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.player.Players;

import java.util.List;

public class ChessManager {
    private final Players players;

    public ChessManager() {
        this.players = new Players();
    }

    public Board getBoard() {
        return players.getBoard();
    }

    public void move(final MoveCommand command) {
        players.validateTurn(command.source());
        players.move(command.source(), command.target());
        players.changeTurn();
    }

    public boolean isEnd() {
        return players.isEnd();
    }

    public Status calculateStatus() {
        return new Status(players.whitePlayerScore(), players.blackPlayerScore());
    }

    public List<Position> getReachablePositions(ShowCommand command) {
        return players.getReachablePositions(command.source());
    }
}
