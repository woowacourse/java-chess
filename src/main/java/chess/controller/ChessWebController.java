package chess.controller;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFileLocation;

import chess.dto.MovablePositionDTO;
import chess.dto.MovePieceDTO;
import chess.dto.WebSimpleBoardDTO;
import chess.service.ChessService;
import chess.ResponseStatus;
import com.google.gson.Gson;
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
    }

    private Route roomRender() {
        return (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "room.html");
        };
    }

    private Route chessRender() {
        return (request, response) -> {
            try {
                Map<String, Object> model = new HashMap<>();
                Object boardId = request.queryParams("boardId");
                model.put("boardId", boardId);
                return render(model, "index.html");
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST.getStateCode());
            }
        };
    }

    private Route createBoard() {
        return (request, response) -> {
            try {
                WebSimpleBoardDTO webSimpleBoardDTO = gson.fromJson(request.body(), WebSimpleBoardDTO.class);
                response.status(ResponseStatus.OK.getStateCode());
                return chessService.createBoard(webSimpleBoardDTO);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST.getStateCode());
            }
        };
    }

    private Route joinBoard() {
        return (request, response) -> {
            try {
                WebSimpleBoardDTO webSimpleBoardDTO = gson.fromJson(request.body(), WebSimpleBoardDTO.class);
                response.status(ResponseStatus.OK.getStateCode());
                return gson.toJson(chessService.joinBoard(webSimpleBoardDTO));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return halt(ResponseStatus.BAD_REQUEST.getStateCode());
            }
        };
    }

    private Route movePiece() {
        return (request, response) -> {
            try {
                MovePieceDTO movePieceDTO = gson.fromJson(request.body(), MovePieceDTO.class);
                response.status(ResponseStatus.OK.getStateCode());
                return gson.toJson(chessService.movedPiece(movePieceDTO));
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST.getStateCode());
            }
        };
    }

    private Route movablePosition() {
        return (request, response) -> {
            try {
                MovablePositionDTO movablePositionDTO = gson.fromJson(request.body(), MovablePositionDTO.class);
                movablePositionDTO = chessService.movablePositions(movablePositionDTO);
                response.status(ResponseStatus.OK.getStateCode());
                return gson.toJson(movablePositionDTO);
            } catch (Exception e) {
                return halt(ResponseStatus.BAD_REQUEST.getStateCode());
            }
        };
    }

    private Route searchBoard() {
        return (request, response) -> {
            try {
                String playerName = request.queryParams("playerName");
                response.status();
                List<WebSimpleBoardDTO> webSimpleBoardDTOS = chessService.searchBoard(playerName);
                return gson.toJson(webSimpleBoardDTOS);
            } catch (Exception e) {
                e.printStackTrace();
                return halt(ResponseStatus.BAD_REQUEST.getStateCode());
            }
        };
    }



    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
