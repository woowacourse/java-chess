package chess.domain.game.status;

import chess.domain.game.Status;

public class Ready implements GameStatus {
    @Override
    public Status checkStatus() {
        return Status.READY;
    }
}
