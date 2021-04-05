package chess.controller;

import chess.dao.GameDAO;
import chess.domain.ChessGameManager;
import chess.domain.position.Position;
import chess.dto.*;
import chess.exception.DomainException;
import com.google.gson.Gson;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    private final ChessGameManager chessGameManager;
    private final GameDAO gameDAO = new GameDAO();
    private final Gson gson = new Gson();

    public WebController(ChessGameManager chessGameManager) {
        this.chessGameManager = chessGameManager;
    }

    public void run() {
        get("/", this::responseHomePage);
        get("/newgame", this::createNewGame, gson::toJson);
        post("/move", this::move, gson::toJson);
        get("/save", this::saveGame, gson::toJson);
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

    private CommonDto<?> saveGame(Request request, Response response) {
        try {
            gameDAO.saveGame(ChessBoardDto.from(chessGameManager.getBoard()), chessGameManager.getCurrentTurnColor());
            return new CommonDto<NoneItem>(
                    StatusCode.OK,
                    "게임을 저장했습니다.",
                    new NoneItem());
        } catch (SQLException e) {
            return new CommonDto<ErrorResponse>(
                    StatusCode.BAD_REQUEST,
                    "게임을 데이터베이스에 저장하는 데에 오류가 발생했습니다.\n에러 메세지: " + e.getMessage(),
                    new ErrorResponse());
        }
    }
}
