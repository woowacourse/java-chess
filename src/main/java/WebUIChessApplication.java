import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.WebMenuController;
import dao.GameDao;
import domain.piece.position.Position;
import dto.ResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    static int gameID;
    static WebMenuController testController;

    public static void main(String[] args) {
        GameDao gameDao = new GameDao();
        Gson gson = new Gson();

        staticFiles.location("/static");
        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/roomNumber", (req, res) -> gameDao.findGames());

        get("/startClick", (req, res) -> {
            String input = req.queryParams("roomNumber");
            if ("new".equals(input)) {
                testController = new WebMenuController();
                ResultDto resultDto = testController.run("start");
                gameDao.insertNewGameInfo(resultDto);
                gameID = gameDao.lastGameID();
                return render(new HashMap<String, Object>() {{
                    put("roomNumber", gameID);
                }}, "start.html");
            }
            gameID = Integer.parseInt(input);
            ResultDto resultDto = gameDao.selectGameInfo(gameID);
            testController = new WebMenuController(resultDto.getPiecesDto());
            return render(new HashMap<>(), "start.html");
        });

        get("/start", (req, res) -> render(new HashMap<>(), "game.html"));

        get("/showData", (req, res) -> gson.toJson(testController.getResultDto()));

        post("/movedata", (req, res) -> {
            JsonObject jsonObject = gson.fromJson(req.body(), JsonObject.class);
            String source = jsonObject.get("source").getAsString();
            String target = jsonObject.get("target").getAsString();
            String command = "move " + source + " " + Position.of(target);
            ResultDto resultDto = testController.run(command);
            if (resultDto.isSuccess()) {
                if (!resultDto.isEnd()) {
                    gameDao.updateGame(resultDto, source, target, gameID);
                    return gson.toJson(resultDto);
                }
                gameDao.deleteGame(gameID);
                return gson.toJson(resultDto);
            }
            return gson.toJson(resultDto);
        });

        get("/status", (req, res) -> gson.toJson(testController.run("status")));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}