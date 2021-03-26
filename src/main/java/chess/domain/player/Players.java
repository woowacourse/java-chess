package chess.domain.player;

import chess.domain.piece.PieceColor;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public Player currentPlayer(PieceColor currentColor) {
        return players.stream()
            .filter(player -> player.isColor(currentColor))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 색을 가진 플레이어가 존재하지 않습니다."));
    }
}
