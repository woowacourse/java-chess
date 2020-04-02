package chess.domain.gamestatus;

import chess.domain.score.Score;

public interface GameStatus {

    GameStatus start();

    GameStatus move(String fromPosition, String toPosition);

    String getBoardString();

    Score scoring();

    GameStatus finish();
}
