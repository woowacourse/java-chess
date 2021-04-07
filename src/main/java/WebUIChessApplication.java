import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import com.google.gson.Gson;
import dto.request.ChessGameRequestDto;
import dto.request.PiecesRequestDto;
import dto.request.ScoreRequestDto;
import exception.DataBaseException;
import exception.PieceMoveException;
import java.util.HashMap;
import java.util.Map;
import service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Gson GSON = new Gson();
    private static final ChessGameService chessGameService = new ChessGameService();

    public static void main(String[] args) {
        initServerConfiguration();
        getMainPage();
        putChessGame();
        putPieces();
        getScore();
        exceptionHandler();
    }

    private static void initServerConfiguration() {
        port(8080);
        staticFiles.location("/static");
        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.body(GSON.toJson(exception.getMessage()));
        });
    }

    private static void getMainPage() {
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });
    }

    private static void putChessGame() {
        put("/api/chessGame", (request, response) -> {
            response.type("application/json");
            ChessGameRequestDto chessGameRequestDto = GSON
                .fromJson(request.body(), ChessGameRequestDto.class);
            return chessGameService.putChessGame(chessGameRequestDto);
        }, GSON::toJson);
    }

    private static void putPieces() {
        put("/api/pieces", (request, response) -> {
            response.type("application/json");
            PiecesRequestDto piecesRequestDto = GSON
                .fromJson(request.body(), PiecesRequestDto.class);
            return chessGameService.putPiece(piecesRequestDto);
        }, GSON::toJson);
    }

    private static void getScore() {
        get("/api/score", (request, response) -> {
            response.type("application/json");
            return chessGameService.getScore(new ScoreRequestDto(request.queryString()));
        }, GSON::toJson);
    }

    private static void pieceMoveExceptionHandler() {
        exception(PieceMoveException.class, (exception, request, response) -> {
            response.status(500);
            response.body(exception.getMessage());
        });
    }

    private static void dataBaseExceptionHandler() {
        exception(DataBaseException.class, (exception, request, response) -> {
            response.status(500);
            response.body(exception.getMessage());
        });
    }

    private static void exceptionHandler() {
        pieceMoveExceptionHandler();
        dataBaseExceptionHandler();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}