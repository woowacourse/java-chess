package chess.domain.player;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.game.ScoreCalculator;
import chess.domain.piece.Piece;

public class Players {

    private final List<Player> players;

    public Players(final Player... players) {
        this(List.of(players));
    }

    public Players(final List<Player> players) {
        validatePlayerColorNotDuplicated(players);
        this.players = players;
    }

    private void validatePlayerColorNotDuplicated(final List<Player> players) {
        if (isPlayerColorDuplicated(players)) {
            throw new IllegalArgumentException("플레이어의 색상이 중복됩니다.");
        }
    }

    private boolean isPlayerColorDuplicated(final List<Player> players) {
        final List<Color> colors = players.stream()
                .map(Player::getColor)
                .collect(Collectors.toUnmodifiableList());
        return colors.stream()
                .anyMatch(color -> Collections.frequency(colors, color) > 1);
    }

    public static Players initialize(final PlayerFactory playerFactory) {
        return new Players(playerFactory.createPlayers());
    }

    public void move(final Color color, final Position source, final Position target) {
        final Player player = findPlayerByColor(color);
        validatePositionNotPlacedByOtherPlayer(player, source);

        findEnemyPlayerByPosition(player, target).ifPresentOrElse(
                (enemyPlayer) -> attackPlayer(player, enemyPlayer, source, target),
                () -> movePlayer(player, source, target)
        );
    }

    private void validatePositionNotPlacedByOtherPlayer(final Player originPlayer, final Position position) {
        if (findEnemyPlayerByPosition(originPlayer, position).isPresent()) {
            throw new IllegalArgumentException("다른 플레이어의 기물은 움직일 수 없습니다.");
        }
    }

    private Optional<Player> findEnemyPlayerByPosition(final Player originPlayer, final Position position) {
        return players.stream()
                .filter(player -> !player.equals(originPlayer))
                .filter(player -> player.contains(position))
                .findAny();
    }

    private void attackPlayer(final Player player, final Player enemyPlayer,
                              final Position source, final Position target) {
        final List<Position> routeToAttack = player.calculateRouteToAttack(source, target);
        validateRouteNotBlockedBeforeArrival(routeToAttack);
        player.attack(source, target, enemyPlayer);
    }

    private void movePlayer(final Player player, final Position source, final Position target) {
        final List<Position> routeToMove = player.calculateRouteToMove(source, target);
        validateRouteNotBlockedBeforeArrival(routeToMove);
        player.move(source, target);
    }

    private void validateRouteNotBlockedBeforeArrival(final List<Position> route) {
        if (isRouteBlockedBeforeArrival(route)) {
            throw new IllegalStateException("목적지까지의 경로를 다른 기물이 가로막고 있습니다.");
        }
    }

    private boolean isRouteBlockedBeforeArrival(final List<Position> route) {
        final List<Position> routeBeforeArrival = route.subList(0, route.size() - 1);
        return routeBeforeArrival.stream()
                .anyMatch(this::isPiecePlacedAtPosition);
    }

    private boolean isPiecePlacedAtPosition(final Position position) {
        return players.stream()
                .anyMatch(player -> player.contains(position));
    }

    public boolean isPlayerAbleToPromotePawn(final Color color) {
        final Player player = findPlayerByColor(color);
        return player.isPromotablePawnExist();
    }

    public void promotePawn(final Color color, final String pieceName) {
        final Player player = findPlayerByColor(color);
        player.promotePawn(pieceName);
    }

    private Player findPlayerByColor(final Color color) {
        return players.stream()
                .filter(player -> player.isColorSame(color))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("해당 색상의 플레이어를 찾을 수 없습니다."));
    }

    public boolean isOnlyOneKingLeft() {
        return countPlayersWithKingAlive() == 1;
    }

    private int countPlayersWithKingAlive() {
        return (int) players.stream()
                .filter(Player::isKingAlive)
                .count();
    }

    public Map<Color, Double> calculatePlayerScores(final ScoreCalculator scoreCalculator) {
        return players.stream()
                .collect(Collectors.toMap(
                        Player::getColor,
                        player -> player.calculateScore(scoreCalculator)
                ));
    }

    public Map<Position, Piece> getPiecesByPlayer(final Color color) {
        return findPlayerByColor(color).getPieces();
    }

    public List<Player> getPlayers() {
        return players;
    }
}
