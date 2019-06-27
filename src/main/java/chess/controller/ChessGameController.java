package chess.controller;

import chess.service.BoardInitializer;
import chess.service.ContinueGameInitializer;
import chess.service.NewGameInitializer;
import chess.service.PieceMover;
import chess.service.dto.ChessBoardDto;
import chess.service.dto.MoveResultDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Route;

import java.sql.SQLException;

public class ChessGameController {

    public static Route newGame = (request, response) -> {
        request.session(true);
        request.session().attribute("isNewGame", true);
        response.redirect("/chessgame.html");
        return null;
    };

    public static Route continueGame = (request, response) -> {
        request.session(true);
        request.session().attribute("isNewGame", false);
        response.redirect("/chessgame.html");
        return null;
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        boolean isNewGame = request.session().attribute("isNewGame");
        try {
            if (isNewGame) {
                return new Gson().toJson(makeChessBoardDto(new NewGameInitializer(), request).getInitWebBoard());
            }
            return new Gson().toJson(makeChessBoardDto(new ContinueGameInitializer(), request).getInitWebBoard());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    private static ChessBoardDto makeChessBoardDto(BoardInitializer initializer, Request request) throws SQLException {
        return initializer.initialize(request);
    }

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResultDto moveResultDto = new MoveResultDto();
        try {
            PieceMover pieceMover = new PieceMover();
            moveResultDto = pieceMover.movePiece(request);
        } catch (Exception e) {
            moveResultDto.setSuccess(false);
            response.status(500);
        }
        return new Gson().toJson(moveResultDto);
    };
}
