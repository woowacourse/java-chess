package chess.domain.game.status;

import chess.domain.game.Status;

public class Playing implements GameStatus {
    @Override
    public Status checkStatus() {
        return Status.PLAYING;
    }
}
