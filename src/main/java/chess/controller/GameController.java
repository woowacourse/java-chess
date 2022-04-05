package chess.controller;

import chess.game.Game;
import chess.piece.detail.Color;
import chess.view.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameController {

    public Map<Color, Double> move(final Game game, final String input) {
        if (game.isRunning()) {
            final List<String> splitInput = Arrays.asList(input.split(" "));
            return game.run(splitInput.get(0), splitInput);
        }
        return null;
    }

    public void restart(final Game game) {

    }
}
