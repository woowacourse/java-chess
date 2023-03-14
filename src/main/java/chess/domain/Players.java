package chess.domain;

import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final Pieces blackPieces, final Pieces whitePieces) {
        Player black = Player.from("black", blackPieces);
        Player white = Player.from("white", whitePieces);
        return new Players(List.of(black, white));
    }
}
