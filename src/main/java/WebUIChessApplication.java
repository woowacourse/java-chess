import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import controller.WebMenuController;
import dao.GameDao;
import domain.ChessGame;
import domain.dto.BoardDto;
import domain.dto.StatusDto;
import domain.piece.objects.Piece;
import domain.piece.objects.PieceFactory;
import domain.piece.position.Position;
import json.PiecesDto;
import json.ResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.OutputView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        ChessGame game = new ChessGame();
        WebMenuController menuController = new WebMenuController();
        GameDao gameDao = new GameDao();

        staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/startClick", (req, res) -> {
            if (gameDao.selectGameCount() == 1) {
                String gameInfoJason = gameDao.getGameInfo();
                System.out.println(gameInfoJason);
                Map<Position, Piece> data = convertJsonToPieces(gameInfoJason);
                game.start(data);
                return render(new HashMap<>(), "start.html");
            }
            menuController.run("start", game);
            gameDao.insertGameInfo(getResultDto(game, menuController));
            return render(new HashMap<>(), "start.html");
        });

        get("/start", (req, res) -> render(new HashMap<>(), "game.html"));

        get("/data", (req, res) -> {
            ResultDto resultDto = getResultDto(game, menuController);
            String jsonData = new ObjectMapper().writeValueAsString(resultDto);
            OutputView.showBoard(new BoardDto(game.getBoard()));
            if (game.isRunning()) {
                gameDao.updateGame(jsonData);
                return new ObjectMapper().writeValueAsString(resultDto);
            }
            gameDao.deleteGame();
            return new ObjectMapper().writeValueAsString(resultDto);
        });

        post("/movedata", (req, res) -> {
            JsonObject jsonObject = (JsonObject) JsonParser.parseString(req.body());
            String command = "move " + jsonObject.get("source").getAsString() + " " + Position.of(jsonObject.get("target").getAsString());
            menuController.run(command, game);
            ResultDto resultDto = getResultDto(game, menuController);
            String jsonData = new ObjectMapper().writeValueAsString(resultDto);
            if (game.isRunning()) {
                gameDao.updateGame(jsonData);
                return new ObjectMapper().writeValueAsString(resultDto);
            }
            gameDao.deleteGame();
            return jsonData;
        });

        get("/status", (req, res) -> {
            ResultDto result = getResultDto(game, menuController);
            return new ObjectMapper().writeValueAsString(result);
        });
    }

    private static Map<Position, Piece> convertJsonToPieces(String gameInfoJason) throws IOException {
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(gameInfoJason);
        String response = jsonObject.get("response").getAsString();
        JsonElement pieces2 = ((JsonObject) JsonParser.parseString(response)).get("pieces");
        System.out.println(pieces2);
        JsonArray asJsonArray = pieces2.getAsJsonArray();
        Map<Position, Piece> data = new HashMap<>();
        for (JsonElement jsonElement : asJsonArray) {
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