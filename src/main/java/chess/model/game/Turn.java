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

    public void processNextTurn() {
        this.count++;
    }

    public Camp findPlayer() {
        final int campIndex = count % PLAYER_COUNT;

        return PLAYERS_CAMP.get(campIndex);
    }

    public Camp oppositeCamp() {
        final int oppositeCampIndex = count % PLAYER_COUNT;

        return PLAYERS_CAMP.get(oppositeCampIndex);
    }
}
