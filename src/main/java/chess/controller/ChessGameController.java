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

    private static ChessGame chessGame;
    private static boolean isNewGame;

    public static Route newGame = (request, response) -> {
        response.redirect("/chessgame.html");
        isNewGame = true;
        return null;
    };

    public static Route continueGame = (request, response) -> {
        response.redirect("/chessgame.html");
        isNewGame = false;
        return null;
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        try {
            ChessBoardDto chessBoardDto;
            if (isNewGame) {
                chessGame = new ChessGame();
                NewGameInitializer newGameInitializer = new NewGameInitializer();
                chessBoardDto = newGameInitializer.initialize(chessGame);
                return new Gson().toJson(chessBoardDto.getInitWebBoard());
            }

            ContinueGameInitializer continueGameInitializer = new ContinueGameInitializer();
            chessBoardDto = continueGameInitializer.initialize();
            chessGame = new ChessGame(chessBoardDto.getCurrentOfTurn(), chessBoardDto.getGameBoard());
            return new Gson().toJson(chessBoardDto.getInitWebBoard());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResultDto moveResultDto = new MoveResultDto();
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
