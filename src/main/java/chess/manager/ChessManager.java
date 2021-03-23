package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.player.Players;

import java.util.List;

public class ChessManager {
    private final Board board;
    private final Players players;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
        this.players = new Players();
    }

    public Board getBoard() {
        return board;
    }

    public void move(final MoveCommand command) {
        players.validateTurn(command.source());
        players.move(command.source(), command.target());
        players.changeTurn();
    }

    public boolean isEnd() {
        return players.isKingDead();
    }

    public Status calculateStatus() {
        return players.getStatus();
    }

    public List<Position> getReachablePositions(ShowCommand command) {
        return players.getReachablePositions(command.source());
    }
}
