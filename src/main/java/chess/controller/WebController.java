package chess.controller;

import chess.domain.CommandAsString;
import chess.domain.game.Game;
import chess.domain.game.state.InitialState;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WebController {

    private Game game;

    public WebController() {
        this.game = new Game(new InitialState());
    }

    public Map<String, String> startGame() {
        CommandAsString command = new CommandAsString("start");
        game = game.execute(command);
        Result result = game.result(command);
        return convertToStringMap(result.infoAsMap());
    }

    public Map<String, String> move(String source, String target) {
        CommandAsString command = new CommandAsString("move " + source + " " + target);
        try {
            game = game.execute(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
        Result result = game.result(command);
        return convertToStringMap(result.infoAsMap());
    }

    private Map<String, String> convertToStringMap(Map<Position, Piece> coordinates) {
        Map<String, String> stringBoard = new HashMap<>();
        for (Entry<Position, Piece> entry : coordinates.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            stringBoard.put(position.name(), piece.getName());
        }
        return stringBoard;
    }
}
