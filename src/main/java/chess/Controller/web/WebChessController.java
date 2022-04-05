package chess.Controller.web;

import chess.Controller.ChessController;
import chess.Controller.command.Command;
import chess.Controller.command.ParsedCommand;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.util.JsonParser;
import chess.util.ViewUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.simple.JSONObject;
import spark.Request;
import spark.Route;

public class WebChessController {

    public static final Route renderMainPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(model, "/index.html");
    };

    public static final Route findUserHistory = (req, res) -> {
        final String userName = req.params(":userName");
        final ChessController chess = new ChessController();
        final int userId = chess.initGame(userName);
        final PiecesDto pieces = chess.getCurrentBoardState(userId);
        req.session().attribute("user-id", userId);
        return JsonParser.makePiecesToJsonArray(pieces);
    };

    public static final Route runCommand = (req, res) -> {
        final int userId = req.session().attribute("user-id");
        final ParsedCommand parsedCommand = parseRequestToCommand(req);
        try {
            return doCommandAction(userId, parsedCommand);
        } catch (IllegalArgumentException exception) {
            res.status(400);
            return JsonParser.errorToJson(exception.getMessage());
        }
    };

    private static ParsedCommand parseRequestToCommand(final Request req) {
        final String command = req.params(":command");
        final Optional<String> startPosition = Optional.ofNullable(req.queryParams("start"));
        final Optional<String> endPosition = Optional.ofNullable(req.queryParams("end"));
        final String rawCommand = command + " " + startPosition.orElse("") + " " + endPosition.orElse("");
        return new ParsedCommand(rawCommand);
    }

    private static JSONObject doCommandAction(final int userId, final ParsedCommand parsedCommand) {
        final ChessController chess = new ChessController();
        final Command command = parsedCommand.getCommand();
        if (command == Command.START || command == Command.MOVE) {
            return doActionAboutPieces(userId, parsedCommand, chess);
        }
        return doActionAboutScore(userId, parsedCommand, chess);

    }

    private static JSONObject doActionAboutPieces(final int userId, final ParsedCommand parsedCommand,
                                                  final ChessController chess) {
        final PiecesDto piecesDto = chess.doActionAboutPieces(parsedCommand, userId);
        return JsonParser.makePiecesToJsonArray(piecesDto);
    }

    private static JSONObject doActionAboutScore(final int userId, final ParsedCommand parsedCommand,
                                                 final ChessController chess) {
        final ScoreDto scoreDto = chess.doActionAboutScore(parsedCommand, userId);
        final JSONObject responseObject = JsonParser.scoreToJson(scoreDto, chess.getCurrentStatus(userId));
        if (parsedCommand.getCommand() == Command.END) {
            chess.finishGame(userId);
        }
        return responseObject;
    }

}
