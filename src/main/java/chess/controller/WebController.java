package chess.controller;

import chess.dao.GameDAO;
import chess.dao.Serializer;
import chess.domain.ChessGameManager;
import chess.domain.position.Position;
import chess.dto.*;
import chess.exception.HandledException;
import org.json.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {
    private final ChessGameManager chessGameManager;
    private final GameDAO gameDAO = new GameDAO();
    private final Serializer serializer = Serializer.getInstance();

    public WebController(ChessGameManager chessGameManager) {
        this.chessGameManager = chessGameManager;
    }

    public void run() {
        get("/", this::responseHomePage);
        get("/newgame", this::createNewGame, serializer::toJson);
        post("/move", this::move, serializer::toJson);
        get("/save", this::saveGame, serializer::toJson);
        get("/load", this::loadGame, serializer::toJson);
    }

    private String responseHomePage(Request request, Response response) {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private CommonDto<?> handleExpectedException(Supplier<CommonDto<?>> supplier, int statusCodeOnError) {
        try {
            return supplier.get();
        } catch (HandledException e) {
            return new CommonDto<>(
                    statusCodeOnError,
                    e.getMessage(),
                    new NoneItem());
        }
    }

    private CommonDto<?> createNewGame(Request request, Response response) {
        return handleExpectedException(() -> {
            chessGameManager.start();
            return new CommonDto<>(
                    StatusCode.OK,
                    "새로운 게임을 시작합니다.",
                    NewGameResponse.from(chessGameManager));
        }, StatusCode.BAD_REQUEST);
    }

    private CommonDto<?> move(Request request, Response response) {
        return handleExpectedException(() -> {
            JSONObject jsonObject = new JSONObject(request.body());
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");

            chessGameManager.move(Position.of(from), Position.of(to));

            return new CommonDto<>(
                    StatusCode.OK,
                    "기물을 이동했습니다.",
                    RunningGameResponse.from(chessGameManager));
        }, StatusCode.BAD_REQUEST);
    }

    private CommonDto<?> saveGame(Request request, Response response) {
        return handleExpectedException(() -> {
            try {
                gameDAO.saveGame(ChessBoardDto.from(chessGameManager.getBoard()), chessGameManager.getCurrentTurnColor());
                return new CommonDto<>(
                        StatusCode.CREATE,
                        "게임을 저장했습니다.",
                        new NoneItem());
            } catch (SQLException e) {
                return new CommonDto<>(
                        StatusCode.INTERNAL_SERVER_ERROR,
                        "알 수 없는 서버 내부 오류입니다. 에러 메세지: " + e.getMessage(),
                        new NoneItem());
            }
        }, StatusCode.BAD_REQUEST);
    }

    private CommonDto<?> loadGame(Request request, Response response) {
        return handleExpectedException(()-> {
            try {
                SavedGameData savedGameData = gameDAO.loadGame();
                chessGameManager.load(savedGameData.getChessBoardDto().toChessBoard(), savedGameData.getCurrentTurnColor());
                return new CommonDto<>(
                        StatusCode.OK,
                        "게임을 불러왔습니다.",
                        RunningGameResponse.from(chessGameManager));
            } catch (SQLException e) {
                return new CommonDto<>(
                        StatusCode.INTERNAL_SERVER_ERROR,
                        "알 수 없는 서버 내부 오류입니다. 에러 메세지: " + e.getMessage(),
                        new NoneItem());
            }
        }, StatusCode.BAD_REQUEST);
    }
}
