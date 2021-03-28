package chess.manager;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Position;
import chess.domain.player.Player;
import chess.domain.player.Scores;
import chess.domain.player.Players;
import chess.domain.player.Turn;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChessGame {
    private final Players players;
    private final Board board;

    private boolean isGameEnd;
    private Turn turn = Turn.WHITE;

    public ChessGame() {
        this.board = BoardInitializer.initiateBoard();
        this.players = new Players();
    }

    public void initNewGame() {
        isGameEnd = false;
    }

    public void move(final Position source, final Position target) {
        validateTurn(source);

        board.movePiece(source, target);
        players.move(source, target);

        checkGameEnd();
        changeTurn();
    }

    private void validateTurn(final Position source) {
        turn.validate(players.ownerOf(source));
    }

    private void changeTurn(){
        turn = turn.change();
    }

    public Scores scores() {
        return players.scores(board);
    }

    public List<Position> reachablePositions(final Position source) {
        return board.getAblePositionsToMove(source);
    }

    public Board board() {
        return board;
    }

    private void checkGameEnd(){
        isGameEnd = players.anyKingDead(board);
    }

    public void setGameEnd() {
        isGameEnd = true;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public Queue<Player> winner(){
        return new LinkedList<>(scores().winner());
    }
}
