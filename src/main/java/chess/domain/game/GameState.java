package chess.domain.game;

import java.util.List;

import chess.dto.Response;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();

    GameState move(List<String> arguments);

    GameState status();

    Response getResponse();
}
