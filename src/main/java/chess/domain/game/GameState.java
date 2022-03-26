package chess.domain.game;

import chess.dto.Response;

import java.util.List;

public interface GameState {

    GameState start();

    GameState finish();

    boolean isRunnable();

    GameState move(List<String> arguments);

    GameState status();

    Response getResponse();
}
