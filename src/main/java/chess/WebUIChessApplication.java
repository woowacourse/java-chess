package chess;

import chess.board.ChessBoard;
import chess.board.ChessBoardAdapter;
import chess.manager.ChessManager;
import chess.repository.InMemoryChessRepository;
import chess.repository.InMemoryMovementRepository;
import chess.service.ChessService;
import chess.web.dto.DefaultResponse;
import chess.web.dto.MoveRequest;
import chess.web.dto.MoveResponse;
import chess.web.dto.SaveResponse;
import chess.web.dto.TilesDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.delete;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final TilesDto EMPTY_BOARD = new TilesDto(new ChessManager(new ChessBoardAdapter(ChessBoard.empty())));

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        final ChessService chessService = new ChessService(new InMemoryChessRepository(), new InMemoryMovementRepository());

        //home
        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("tilesDto", EMPTY_BOARD);

            return render(model, "index.html");
        });

        //start 새게임 시작 및 저장
        post("/chessboard", (request, response) -> {
            SaveResponse saveResponse = chessService.save();

            return toJson(DefaultResponse.CREATED(saveResponse));
        });

        //get id list
        get("/chessboard", (request, response) -> {

            return null;
        });

        //load chessboard
        get("/chessboard/:id", (request, response) -> {

            return null;
        });

        // move
        post("/chessboard/move", (request, response) -> {
            MoveRequest moveRequest = gson.fromJson(request.body(), MoveRequest.class);

            MoveResponse moveResponse = chessService.move(moveRequest);
            return toJson(DefaultResponse.OK(moveResponse));
        });

        //surrender
        post("/surrender", (request, response) -> {

            return null;
        });

        //end
        delete("/end", (request, response) -> {

            return null;
        });

        exception(IllegalArgumentException.class, ((exception, request, response) -> {
            response.status(HttpStatus.Code.BAD_REQUEST.getCode());
            response.body(toJson(new DefaultResponse<>(HttpStatus.Code.BAD_REQUEST, null, exception.getMessage())));
        }));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String toJson(DefaultResponse<?> defaultResponse) {
        return gson.toJson(defaultResponse);
    }


}
