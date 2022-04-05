package chess;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.Controller.ChessController;
import chess.Controller.command.ParsedCommand;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.util.JsonParser;
import chess.util.ViewUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.json.simple.JSONObject;

public class WebApplication {
    public static String STATUS = "dev";

    public static void main(String[] args) {
        port(8080);

        if (STATUS.equals("dev")) {
            String projectDirectory = System.getProperty("user.dir");
            String staticDirectory = "/src/main/resources/static";
            externalStaticFileLocation(projectDirectory + staticDirectory);
        } else {
            staticFileLocation("/static");
        }

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return ViewUtil.render(model, "/index.html");
        });

        get("/user/name/:userName", (req, res) -> {
            final String userName = req.params(":userName");
            final ChessController chess = new ChessController();
            final int userId = chess.initGame(userName);
            final PiecesDto pieces = chess.getCurrentBoardState(userId);
            req.session().attribute("user-id", userId);
            return JsonParser.makePiecesToJsonArray(pieces);
        });

        get("/game/command/:command", (req, res) -> {
            final int userId = req.session().attribute("user-id");
            final String command = req.params(":command");
            final Optional<String> startPosition = Optional.ofNullable(req.queryParams("start"));
            final Optional<String> endPosition = Optional.ofNullable(req.queryParams("end"));
            final String rawCommand = command + " " + startPosition.orElse("") + " " + endPosition.orElse("");
            final ParsedCommand parsedCommand = new ParsedCommand(rawCommand);

            final ChessController chess = new ChessController();
            try {
                if (command.equals("start") || command.equals("move")) {
                    final PiecesDto piecesDto = chess.doActionAboutPieces(parsedCommand, userId);
                    return JsonParser.makePiecesToJsonArray(piecesDto);
                }
                final ScoreDto scoreDto = chess.doActionAboutScore(parsedCommand, userId);
                final JSONObject responseObject = JsonParser.scoreToJson(scoreDto, chess.getCurrentStatus(userId));
                if (command.equals("end")) {
                    chess.finishGame(userId);
                }
                return responseObject;
            } catch (IllegalArgumentException exception) {
                res.status(400);
                return JsonParser.errorToJson(exception.getMessage());
            }
        });

    }


}

