package chess.service;

import chess.Controller.ChessController;
import chess.Controller.command.Command;
import chess.Controller.command.ParsedCommand;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.util.ViewUtil;
import chess.util.json.JsonParser;
import java.util.HashMap;
import java.util.Map;
import spark.Request;
import spark.Route;

public class WebChessService {

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
        return JsonParser.getPiecesAndGameStatus(pieces);
    };

    public static final Route statusCommand = (req, res) -> calculateScoreAbout(req, "status");

    public static final Route endCommand = (req, res) -> calculateScoreAbout(req, "end");

    private static String calculateScoreAbout(final Request req, final String rawCommand) {
        final int userId = req.session().attribute("user-id");
        final ParsedCommand parsedCommand = parseRequestToCommand(rawCommand);
        return doCommandActionAboutScore(userId, parsedCommand);
    }

    private static ParsedCommand parseRequestToCommand(final String rawCommand) {
        return new ParsedCommand(rawCommand);
    }

    private static String doCommandActionAboutScore(final int userId, final ParsedCommand parsedCommand) {
        final ChessController chess = new ChessController();
        final ScoreDto scoreDto = chess.doActionAboutScore(parsedCommand, userId);
        final String responseObject = JsonParser.scoreToJson(scoreDto);
        if (parsedCommand.getCommand() == Command.END) {
            chess.finishGame(userId);
        }
        return responseObject;
    }

    public static final Route startCommand = (req, res) -> getPiecesAbout(req, "start");


    public static final Route moveCommand = (req, res) -> getPiecesAbout(req, "move");

    private static String getPiecesAbout(final Request req, final String rawCommand) {
        final int userId = req.session().attribute("user-id");
        ParsedCommand parsedCommand = parseRequestToCommand(rawCommand);
        if (rawCommand.equals("move")) {
            parsedCommand = parseRequestToMoveCommand(req);
        }
        return doCommandActionAboutPieces(userId, parsedCommand);
    }

    private static ParsedCommand parseRequestToMoveCommand(final Request req) {
        final String startPosition = req.queryParams("start");
        final String endPosition = req.queryParams("end");
        final String rawCommand = "move" + " " + startPosition + " " + endPosition;
        return parseRequestToCommand(rawCommand);
    }

    private static String doCommandActionAboutPieces(final int userId, final ParsedCommand parsedCommand) {
        final ChessController chess = new ChessController();
        final PiecesDto piecesDto = chess.doActionAboutPieces(parsedCommand, userId);
        if (parsedCommand.getCommand() == Command.MOVE) {
            return JsonParser.getPiecesAndGameStatus(piecesDto);
        }
        return JsonParser.makePiecesToJsonArray(piecesDto);
    }


}
