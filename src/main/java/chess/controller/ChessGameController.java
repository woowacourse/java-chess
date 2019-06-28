package chess.controller;

import chess.domain.pieces.PointFactory;
import chess.service.BoardInitializer;
import chess.service.ContinueGameInitializeService;
import chess.service.NewGameInitializeService;
import chess.service.PieceMoveService;
import chess.service.dto.ChessGameDto;
import chess.service.dto.MoveInfoDto;
import chess.service.dto.MoveResultDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.Map;

public class ChessGameController {

    public static Route newGame = (request, response) -> {
        request.session(true);
        request.session().attribute("isNewGame", true);
        return render(null, "/chessgame.html");
    };

    public static Route continueGame = (request, response) -> {
        request.session(true);
        request.session().attribute("isNewGame", false);
        return render(null, "/chessgame.html");
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        boolean isNewGame = request.session().attribute("isNewGame");
        try {
            ChessGameDto chessGameDto;
            if (isNewGame) {
                chessGameDto = makeNewGameDto(new NewGameInitializeService(), request);
                return new Gson().toJson(chessGameDto.getInitWebBoard());
            }
            chessGameDto = makeNewGameDto(new ContinueGameInitializeService(), request);
            return new Gson().toJson(chessGameDto.getInitWebBoard());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    private static ChessGameDto makeNewGameDto(BoardInitializer initializer, Request request) throws SQLException {
        ChessGameDto chessGameDto = initializer.initialize();
        request.session().attribute("chessGame", chessGameDto.getChessGame());
        return chessGameDto;
    }

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResultDto moveResultDto = new MoveResultDto(false, false);
        try {
            MoveInfoDto moveInfoDto = new MoveInfoDto(
                    request.session().attribute("chessGame"),
                    PointFactory.of(request.queryMap("source").value()),
                    PointFactory.of(request.queryMap("target").value()));
            PieceMoveService pieceMover = new PieceMoveService();
            moveResultDto = pieceMover.movePiece(moveInfoDto);
        } catch (Exception e) {
            response.status(500);
        }
        return new Gson().toJson(moveResultDto);
    };

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
