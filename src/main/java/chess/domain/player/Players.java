package chess.domain.player;

import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    private Scores scores = new Scores();

    private Players(final Board board) {
        this.players = Arrays.asList(
                new Player(positions(board, Owner.BLACK), Owner.BLACK),
                new Player(positions(board, Owner.WHITE), Owner.WHITE)
        );
    }

    public static Players init(final Board board) {
        return new Players(board);
    }

    private static List<Position> positions(final Board board, final Owner owner) {
        return Arrays.stream(Vertical.values())
                .flatMap(v -> Arrays.stream(Horizontal.values())
                        .filter(h -> board.of(v, h).isOwner(owner))
                        .map(h -> new Position(v, h)))
                .collect(Collectors.toList());
    }

    public void move(final Position source, final Position target) {
        final Player turn = player(source);
        final Player other = other(turn);

        turn.move(source, target);
        other.captured(target);
    }

    private Player player(final Position position) {
        return players.stream()
                .filter(player -> player.has(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기물이 아닙니다."));
    }

    private Player other(final Player turn) {
        return players.stream()
                .filter(player -> !player.equals(turn))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Owner ownerOf(final Position source) {
        return player(source).owner();
    }

    public Scores scores(final Board board) {
        players.stream()
                .forEach(player -> scores = scores.update(player, player.score(board)));
        return scores;
    }

    public boolean anyKingDead(final Board board) {
        return players.stream()
                .anyMatch(player -> player.isDead(board));
    }
}
