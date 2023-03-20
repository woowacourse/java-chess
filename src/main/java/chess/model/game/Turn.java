package chess.model.game;

import chess.model.piece.Camp;
import java.util.List;

public class Turn {

    private static final List<Camp> PLAYERS_CAMP = List.of(Camp.WHITE, Camp.BLACK);
    private static final int PLAYER_COUNT = PLAYERS_CAMP.size();

    private int count;

    public Turn() {
        this.count = 0;
    }

    public Camp findNextPlayer() {
        final int colorIndex = count++ % PLAYER_COUNT;

        return PLAYERS_CAMP.get(colorIndex);
    }
}
