package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Owner;

import java.util.Arrays;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Scores scores = new Scores();

    public Players() {
        this.players = Arrays.asList(
                PlayerInitializer.initPlayer(Owner.BLACK),
                PlayerInitializer.initPlayer(Owner.WHITE)
        );
    }

    public void move(final Position source, final Position target) {
        final Player turn = player(source);
        turn.move(source, target);
        other(turn).captured(target);
    }

    private Player player(final Position position){
        return players.stream()
                .filter(player -> player.has(position))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("기물이 아닙니다."));
    }

    private Player other(final Player turn){
        return players.stream()
                .filter(player -> !player.equals(turn))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Owner ownerOf(final Position source){
        return player(source).owner();
    }

    public Scores scores(final Board board){
        players.stream()
                .forEach(player -> scores = scores.update(player, player.score(board)));
        return scores;
    }

    public boolean anyKingDead(final Board board) {
        return players.stream()
                .anyMatch(player -> player.isDead(board));
    }
}
