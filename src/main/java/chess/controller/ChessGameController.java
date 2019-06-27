package chess.controller;

import chess.domain.ChessGame;
import chess.domain.pieces.PointFactory;
import chess.service.ContinueGameInitializer;
import chess.service.NewGameInitializer;
import chess.service.PieceMover;
import chess.service.dto.ChessBoardDto;
import chess.service.dto.MoveDto;
import chess.service.dto.MoveResultDto;
import com.google.gson.Gson;
import spark.Route;

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
        ChessGame chessGame;
        ChessBoardDto chessBoardDto;
        //request.session(true);
        try {
            if (isNewGame) {
                chessGame = new ChessGame();
                NewGameInitializer newGameInitializer = new NewGameInitializer();
                chessBoardDto = newGameInitializer.initialize(chessGame);
                request.session().attribute("chessGame", chessGame);
                return new Gson().toJson(chessBoardDto.getInitWebBoard());
            }

            ContinueGameInitializer continueGameInitializer = new ContinueGameInitializer();
            chessBoardDto = continueGameInitializer.initialize();
            chessGame = new ChessGame(chessBoardDto.getCurrentOfTurn(), chessBoardDto.getGameBoard());
            request.session().attribute("chessGame", chessGame);
            return new Gson().toJson(chessBoardDto.getInitWebBoard());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResultDto moveResultDto = new MoveResultDto();
        ChessGame chessGame = request.session().attribute("chessGame");
        try {
            MoveDto moveDto = new MoveDto();
            moveDto.setSource(PointFactory.of(request.queryMap("source").value()));
            moveDto.setTarget(PointFactory.of(request.queryMap("target").value()));
            moveDto.setChessGame(chessGame);

            PieceMover pieceMover = new PieceMover();
            moveResultDto = pieceMover.movePiece(moveDto);
        } catch (Exception e) {
            moveResultDto.setSuccess(false);
            response.status(500);
        }
        return new Gson().toJson(moveResultDto);
    };
}
