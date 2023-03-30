package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final Player whitePlayer, final Player blackPlayer) {
        return new Players(List.of(whitePlayer, blackPlayer));
    }

    public Player getAnotherPlayer(final Player findPlayer) {
        return players.stream()
                .filter(player -> !player.isSameColor(findPlayer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("상대 플레이어를 찾을 수 없습니다."));
    }

    public Player getPlayerByColor(final Color color) {
        return players.stream().filter(player -> player.isSameColor(color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 색의 플레이어가 없습니다."));
    }

    public boolean notEveryKingAlive() {
        return players.stream().anyMatch(Player::isKingDead);
    }

    public String getWinnerColorName() {
        Player loser = players.stream().filter(Player::isKingDead)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("아직 블랙, 화이트 킹이 모두 살아있습니다!"));
        return getAnotherPlayer(loser).getColorName();
    }

    public double calculateScore(final Color color) {
        return getPlayerByColor(color)
                .getTotalScore()
                .getValue();
    }

    public Player findPlayerByPosition(final Position findPosition) {
        return players.stream()
                .filter(player -> player.hasPositionPiece(findPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }

    public List<Position> getAllPosition() {
        return players.stream()
                .flatMap(player -> player.getPieces().stream().map(Piece::getPosition))
                .collect(toList());
    }

    public Position findPosition(final Position foundPosition) {
        return getAllPosition().stream()
                .filter(position -> position.equals(foundPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("위치를 다시 확인해주세요."));
    }
}
