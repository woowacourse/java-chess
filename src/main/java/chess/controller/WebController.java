package chess.controller;

import chess.dao.GameDao;
import chess.domain.CommandAsString;
import chess.domain.game.Game;
import chess.domain.game.state.GameState;
import chess.domain.game.state.InitialState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.domain.result.Score;
import chess.dto.GameStateDto;
import chess.view.OutputView;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WebController {

    private Game game;

    public WebController() {
        this.game = new Game(new InitialState());
    }

    public Map<String, String> startGame() {
        game = new Game(new InitialState());
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

    public List<String> calculatePath(String source) {
        CommandAsString command = new CommandAsString("show " + source);
        try {
            game = game.execute(command);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
        }
        Result result = game.result(command);
        return convertToStringList(result.infoAsList());
    }

    public String showTurn() {
        final GameState currentState = game.getState();
        return currentState.currentState();
    }

    public void save(GameDao gameDao) {
        try {
            gameDao.updateGame("1", new GameStateDto(game.getState()));
        } catch (SQLException e) {
            OutputView.printSqlError(e);
        }

    }

    public Map<String, String> load(GameDao gameDao) {
        CommandAsString command = new CommandAsString("nothing");
        try {
            game = gameDao.findGameByGameId("1");
        } catch (SQLException e) {
            OutputView.printSqlError(e);
        }
        Result result = game.result(command);
        return convertToStringMap(result.infoAsMap());
    }

    public String showScore(final Color color) {
        CommandAsString command = new CommandAsString("status");
        Result result = game.result(command);
        Score score = result.infoAsScore(color);
        return score.toString();
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

    private List<String> convertToStringList(List<Position> pathPositions) {
        return pathPositions.stream()
                .map(Position::name)
                .collect(Collectors.toList());
    }
}
