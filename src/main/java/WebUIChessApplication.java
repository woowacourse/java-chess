import com.google.gson.Gson;
import com.google.gson.JsonObject;
import controller.WebMenuController;
import dao.GameDao;
import domain.ChessGame;
import domain.piece.objects.Piece;
import domain.piece.objects.PieceFactory;
import domain.piece.position.Position;
import domain.score.Score;
import dto.PieceDto;
import dto.PiecesDto;
import dto.ResultDto;
import dto.StatusDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    static int gameID;

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        WebMenuController menuController = new WebMenuController();
        GameDao gameDao = new GameDao();
        Gson gson = new Gson();

        staticFiles.location("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/roomNumber", (req, res) -> gameDao.findGames());

        get("/startClick", (req, res) -> {
            String input = req.queryParams("roomNumber");
            if ("new".equals(input)) {
                menuController.run("start", game);
                ResultDto resultDto = getResultDto(game, menuController);
                gameDao.insertNewGameInfo(resultDto);
                gameID = gameDao.lastGameID();
                return render(new HashMap<String, Object>() {{
                    put("roomNumber", gameID);
                }}, "start.html");
            }
            gameID = Integer.parseInt(input);
            ResultDto resultDto = gameDao.selectGameInfo(gameID);
            Map<Position, Piece> data = convertPiecesDtoToPieces(resultDto.getPiecesDto());
            game.load(data, resultDto.getPiecesDto().isTurn());
            return render(new HashMap<>(), "start.html");
        });

        get("/start", (req, res) -> render(new HashMap<>(), "game.html"));

        get("/showData", (req, res) -> gson.toJson(getResultDto(game, menuController)));

        post("/movedata", (req, res) -> {
            JsonObject jsonObject = gson.fromJson(req.body(), JsonObject.class);
            String source = jsonObject.get("source").getAsString();
            String target = jsonObject.get("target").getAsString();
            String command = "move " + source + " " + Position.of(target);
            menuController.run(command, game);
            if (game.isRunning()) {
                ResultDto resultDto = getResultDto(game, menuController);
                gameDao.updateGame(resultDto, source, target, gameID);
                return gson.toJson(resultDto);
            }

            gameDao.deleteGame(gameID);
            return gson.toJson(getResultDto(game, menuController));
        });

        get("/status", (req, res) -> gson.toJson(getResultDto(game, menuController)));
    }

    private static Map<Position, Piece> convertPiecesDtoToPieces(PiecesDto piecesDto) {
        Map<Position, Piece> data = new HashMap<>();
        for (PieceDto pieceDto : piecesDto.getPieces()) {
            Position position = Position.of(pieceDto.getPosition());
            Piece piece = PieceFactory.findPiece(pieceDto.getPieceName());
            data.put(position, piece);
        }
        return data;
    }

    private static ResultDto getResultDto(ChessGame game, WebMenuController menuController) {
        Map<Boolean, Score> scoreMap = game.piecesScore();
        return new ResultDto(new PiecesDto(game.getBoard().getBoard(),
                new StatusDto(scoreMap.get(true).getValue(), scoreMap.get(false).getValue()),
                !game.isRunning(), game.getTurn()), menuController.getErrorMessage());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}