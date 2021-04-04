import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.WebMenuController;
import dao.GameDao;
import domain.ChessGame;
import domain.dto.PiecesDto;
import domain.dto.ResultDto;
import domain.dto.StatusDto;
import domain.piece.objects.Piece;
import domain.piece.objects.PieceFactory;
import domain.piece.position.Position;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    static int gameID;

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        WebMenuController menuController = new WebMenuController();
        GameDao gameDao = new GameDao();

        staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/roomNumber", (req, res) -> gameDao.findGames());

        get("/startClick", (req, res) -> {
            String roomNumber = req.queryParams("roomNumber");
            if (roomNumber.equals("new")) {
                menuController.run("start", game);
                ResultDto resultDto = getResultDto(game, menuController);
                gameDao.insertNewGameInfo(resultDto);
                gameID = gameDao.lastGameID();
                return render(new HashMap<String, Object>(){{put("roomNumber", gameID);}}, "start.html");
            }
            JsonObject responseObject = getJsonObject(gameDao, roomNumber);
            JsonElement pieces = responseObject.get("pieces");
            Map<Position, Piece> data = convertJsonToPieces(pieces.getAsJsonArray());
            game.load(data, responseObject.get("turn").getAsBoolean());
            gameID = Integer.parseInt(roomNumber);
            return render(new HashMap<>(), "start.html");
        });

        get("/start", (req, res) -> render(new HashMap<>(), "game.html"));

        get("/showData", (req, res) -> {
            ResultDto resultDto = getResultDto(game, menuController);
            return new ObjectMapper().writeValueAsString(resultDto);
        });

        post("/movedata", (req, res) -> {
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(req.body());
            String command = "move " + jsonObject.get("source").getAsString() + " "
                    + Position.of(jsonObject.get("target").getAsString());
            menuController.run(command, game);
            ResultDto resultDto = getResultDto(game, menuController);
            String jsonData = new ObjectMapper().writeValueAsString(resultDto);
            if (game.isRunning()) {
                gameDao.updateGame(jsonData, gameID);
                return new ObjectMapper().writeValueAsString(resultDto);
            }
            gameDao.deleteGame(gameID);
            return jsonData;
        });

        get("/status", (req, res) -> {
            ResultDto result = getResultDto(game, menuController);
            return new ObjectMapper().writeValueAsString(result);
        });
    }

    private static JsonObject getJsonObject(GameDao gameDao, String roomNumber) throws SQLException {
        String gameInfoJason = gameDao.selectGameInfo(roomNumber);
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(gameInfoJason);
        String response = jsonObject.get("response").getAsString();
        JsonObject responseObject = (JsonObject) JsonParser.parseString(response);
        return responseObject;
    }

    private static Map<Position, Piece> convertJsonToPieces(JsonArray gameInfoJason) {
        Map<Position, Piece> data = new HashMap<>();
        for (JsonElement jsonElement : gameInfoJason) {
            JsonObject element = jsonElement.getAsJsonObject();
            data.put(Position.of(element.get("position").getAsString()),
                    PieceFactory.findPiece(element.get("pieceName").getAsString()));
        }
        return data;
    }

    private static ResultDto getResultDto(ChessGame game, WebMenuController menuController) throws JsonProcessingException {
        return new ResultDto(new PiecesDto(game.getBoard().getBoard(),
                new StatusDto(game.piecesScore()), game.isRunning(), game.getTurn()), menuController.getErrorMessage());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}