package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Players {
    private final List<Player> players;
    private final Board board;

    private Turn turn = Turn.WHITE;
    private Scores scores = new Scores();

    public Players() {
        this.players = Arrays.asList(
                PlayerInitializer.initPlayer(Owner.BLACK),
                PlayerInitializer.initPlayer(Owner.WHITE)
        );
        this.board = BoardInitializer.initiateBoard();
    }

    public void move(final Position source, final Position target) {
        checkIsKingDead(target);
        board.movePiece(source, target);
        updatePositions(source, target);
        updateScores();
    }

    private void checkIsKingDead(final Position target) {
        if (board.of(target).isKing()) {
            final Player otherPlayer = players.get(turn.otherIndex());
            otherPlayer.makeDead();
        }
    }

    private void updatePositions(final Position source, final Position target) {
        final Player turnPlayer = players.get(turn.index());
        final Player otherPlayer = players.get(turn.otherIndex());

        turnPlayer.move(source, target);
        otherPlayer.remove(target);
    }

    private void updateScores(){
        for(final Player player : players){
            scores = scores.update(player, player.score(board));
        }
    }

    public void changeTurn() {
        turn = turn.change();
    }

    public void validateTurn(final Position source) {
        final Player turnPlayer = players.get(turn.index());

        if (!turnPlayer.contains(source)) {
            throw new IllegalArgumentException("현재 순서의 사용자가 아닙니다.");
        }
    }

    public Scores scores(){
        return scores;
    }

    public List<Player> winner(){
        return scores.winner();
    }

    public List<Position> getReachablePositions(final Position source) {
        return board.getAblePositionsToMove(source);
    }

    public boolean isEnd() {
        return players.stream()
                .anyMatch(player -> player.isDead());
    }

    public Board getBoard() {
        return board;
    }
}
