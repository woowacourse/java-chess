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
        board.move(command.source(), command.target());

        players.updatePositions(command.source(), command.target());
        players.changeTurn();
    }

    public List<Position> getReachablePositions(final ShowCommand command) {
        return board.getAblePositionsToMove(command.source());
    }

    public Status calculateStatus() {
        return players.getStatus(board);
    }

    public boolean isEnd(){
        return players.isKingDead(board);
    }
}
