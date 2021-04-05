package chess.controller;

import chess.domain.ChessGameManager;
import chess.domain.position.Position;
import chess.dto.CommonDto;
import chess.dto.ErrorResponse;
import chess.dto.GameStatusDto;
import chess.dto.StatusCode;
import chess.exception.DomainException;
import com.google.gson.Gson;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    private final Gson gson = new Gson();
    private final ChessGameManager chessGameManager;

    public WebController(ChessGameManager chessGameManager) {
        this.chessGameManager = chessGameManager;
    }

    public void run() {
        get("/", this::responseHomePage);
        get("/newgame", this::createNewGame, gson::toJson);
        post("/move", this::move, gson::toJson);
    }

    private String responseHomePage(Request request, Response response) {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private CommonDto<?> createNewGame(Request request, Response response) {
        try {
            chessGameManager.start();
            return new CommonDto<GameStatusDto>(
                    StatusCode.OK,
                    "새로운 게임을 시작합니다.",
                    GameStatusDto.from(chessGameManager));
        } catch (DomainException e) {
            return new CommonDto<ErrorResponse>(
                    StatusCode.BAD_REQUEST,
                    e.getMessage(),
                    new ErrorResponse());
        }
    }

    private CommonDto<?> move(Request request, Response response) {
        try {
            JSONObject jsonObject = new JSONObject(request.body());
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");

            chessGameManager.move(Position.of(from), Position.of(to));

            return new CommonDto<GameStatusDto>(
                    StatusCode.OK,
                    "기물을 이동했습니다.",
                    GameStatusDto.from(chessGameManager));
        } catch (DomainException e) {
            return new CommonDto<ErrorResponse>(
                    StatusCode.BAD_REQUEST,
                    e.getMessage(),
                    new ErrorResponse());
        }
    }
}
