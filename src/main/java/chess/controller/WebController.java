package chess.controller;

import static java.lang.Integer.parseInt;

import chess.domain.event.MoveCommand;
import chess.dto.CreateGameDto;
import chess.dto.GameCountDto;
import chess.dto.GameDto;
import chess.dto.GameResultDto;
import chess.dto.SearchResultDto;
import chess.service.ChessService;
import spark.Request;

public class WebController {

    private final ChessService chessService = new ChessService();

    public GameCountDto countGames() {
        return chessService.countGames();
    }

    public CreateGameDto initGame() {
        return chessService.initGame();
    }

    public SearchResultDto searchGame(Request request) {
        int gameId = parseInt(request.queryParams("game_id"));
        return chessService.searchGame(gameId);
    }

    public GameDto findGame(Request request) {
        int gameId = parseInt(request.params("id"));
        return chessService.findGame(gameId);
    }

    public GameDto playGame(Request request) {
        int gameId = parseInt(request.params("id"));
        String body = request.body();
        MoveCommand moveCommand = MoveCommand.ofJson(body);

        return chessService.playGame(gameId, moveCommand);
    }
    public GameResultDto findGameResult(Request request) {
        int gameId = parseInt(request.params("id"));
        return chessService.findGameResult(gameId);
    }
}
