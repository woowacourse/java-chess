package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.ResponseStatus;
import chess.dto.MovablePositionDTO;
import chess.dto.MovePieceDTO;
import chess.dto.WebSimpleBoardDTO;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public final class ChessWebController {

    private final ChessService chessService;
    private final Gson gson;

    public ChessWebController() {
        this.chessService = new ChessService();
        gson = new Gson();
    }

    public void run() {
        staticFileLocation("/static");

        get("/", roomRender());

        get("/chess", chessRender());

        post("/api/create", createBoard());

        post("/api/join", joinBoard());

        put("/api/move", movePiece());

        post("/api/movablePositions", movablePosition());

        get("/api/search", searchBoard());

        exception(SQLException.class, (e, request, response) -> {
            e.printStackTrace();
            response.status(ResponseStatus.BAD_REQUEST.getStateCode());
        });

        exception(IllegalArgumentException.class, (e, request, response) -> {
            e.printStackTrace();
            response.status(ResponseStatus.BAD_REQUEST.getStateCode());
        });

        exception(IllegalStateException.class, (e, request, response) -> {
            e.printStackTrace();
            response.status(ResponseStatus.BAD_REQUEST.getStateCode());
        });

        exception(Exception.class, (e, request, response) -> {
            e.printStackTrace();
            response.status(ResponseStatus.BAD_REQUEST.getStateCode());
        });
    }

    private Route roomRender() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            response.status(ResponseStatus.OK.getStateCode());
            return render(model, "room.html");
        };
    }

    private Route chessRender() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Object boardId = request.queryParams("boardId");
            model.put("boardId", boardId);
            response.status(ResponseStatus.OK.getStateCode());
            return render(model, "index.html");
        };
    }

    private Route createBoard() {
        return (request, response) -> {
            WebSimpleBoardDTO webSimpleBoardDTO = gson
                .fromJson(request.body(), WebSimpleBoardDTO.class);
            response.status(ResponseStatus.OK.getStateCode());
            return chessService.createBoard(webSimpleBoardDTO);
        };
    }

    private Route joinBoard() {
        return (request, response) -> {
            WebSimpleBoardDTO webSimpleBoardDTO = gson
                .fromJson(request.body(), WebSimpleBoardDTO.class);
            response.status(ResponseStatus.OK.getStateCode());
            return gson.toJson(chessService.joinBoard(webSimpleBoardDTO));
        };
    }

    private Route movePiece() {
        return (request, response) -> {
            MovePieceDTO movePieceDTO = gson.fromJson(request.body(), MovePieceDTO.class);
            response.status(ResponseStatus.OK.getStateCode());
            return gson.toJson(chessService.movedPiece(movePieceDTO));
        };
    }

    private Route movablePosition() {
        return (request, response) -> {
            MovablePositionDTO movablePositionDTO = gson
                .fromJson(request.body(), MovablePositionDTO.class);
            response.status(ResponseStatus.OK.getStateCode());
            return gson.toJson(chessService.movablePositions(movablePositionDTO));
        };
    }

    private Route searchBoard() {
        return (request, response) -> {
            String playerName = request.queryParams("playerName");
            response.status(ResponseStatus.OK.getStateCode());
            List<WebSimpleBoardDTO> webSimpleBoardDTOS = chessService.searchBoard(playerName);
            return gson.toJson(webSimpleBoardDTOS);
        };
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
