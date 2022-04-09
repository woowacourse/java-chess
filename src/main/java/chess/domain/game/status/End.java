package chess.domain.game.status;

import chess.domain.game.Status;

public class End implements GameStatus {
    @Override
    public Status checkStatus() {
        return Status.END;
    }
}
