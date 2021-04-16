package chess.service;

import chess.controller.web.dto.*;
import chess.dao.CommandDao;
import chess.domain.game.BoardFactory;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.location.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {
    private final CommandDao commandDao;

    public ChessService(CommandDao commandDao) {
        this.commandDao = commandDao;
    }

    public void move(Long roomId, String command) {
        Game game = newGame(roomId);

        try {
            String[] commands = command.split(Command.SPACE_REGEX);
            String from = commands[1];
            String to = commands[2];
            game.move(Position.from(from), Position.from(to));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        commandDao.insert(roomId, command);
    }

    public Map<String, Object> load(Long roomId) {
        Game game = newGame(roomId);
        Map<String, Object> model = makeBoardModel(game, "");
        model.put("room", new RoomDto(roomId, ""));
        return model;
    }

    private Game newGame(Long roomId) {
        Game game = new Game(BoardFactory.create());
        List<String> commands = commandDao.selectAll(roomId);
        for (String command : commands) {
            action(game, command);
        }
        return game;
    }

    private void action(Game game, String command) {
        String[] commands = command.split(Command.SPACE_REGEX);
        String from = commands[1];
        String to = commands[2];

        game.move(Position.from(from), Position.from(to));
    }

    private Map<String, Object> makeBoardModel(Game game, String errorMessage) {
        return setModel(
                errorMessage,
                new BoardDto(game.allBoard()).getMaps(),
                new ScoreDtos(game.score()).getScoreDtos(),
                new ColorDto(game.currentPlayer()),
                game.isEnd()
        );
    }

    private Map<String, Object> setModel(String errorMessage,
                                         Map<PositionDto, PieceDto> board,
                                         List<ScoreDto> score,
                                         ColorDto color,
                                         boolean isFinished) {
        Map<String, Object> model = new HashMap<>();

        setBoard(board, model);
        setGameInformation(errorMessage, score, color, isFinished, model);

        return model;
    }

    private void setGameInformation(String errorMessage,
                                    List<ScoreDto> score,
                                    ColorDto color,
                                    boolean isFinished,
                                    Map<String, Object> model) {
        model.put("scores", score);
        model.put("turn", color);
        model.put("error", new ErrorDto(errorMessage));

        if (isFinished) {
            model.put("winner", color);
        }
    }

    private void setBoard(Map<PositionDto, PieceDto> board, Map<String, Object> model) {
        for (PositionDto positionDTO : board.keySet()) {
            model.put(positionDTO.getKey(), board.get(positionDTO));
        }
    }
}