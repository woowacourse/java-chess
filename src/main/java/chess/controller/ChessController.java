package chess.controller;

import chess.service.MoveService;
import chess.service.StatusService;
import chess.controller.dto.GameDto;
import chess.controller.dto.MessageDto;
import chess.controller.dto.MoveDto;
import chess.controller.dto.StatusDto;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.Spark.get;
import static spark.Spark.patch;


public class ChessController {
    private static final Gson gson = new Gson();
    private static final String CHESS_GAME = "chessGame";

    public void run() {
        patch("/start", this::start, gson::toJson);
        patch("/move", this::move, gson::toJson);
        patch("/end", this::end, gson::toJson);
        get("/status", this::status, gson::toJson);
    }

    public Object start(Request request, Response response) {
        setNewGameToSessionIfSessionIsNewOrIsFinished(request);
        ChessGame chessGame = getChessGameFromSession(request);

        try {
            chessGame.start();
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    public Object move(Request request, Response response) {
        MoveDto moveDto = gson.fromJson(request.body(), MoveDto.class);

        String source = moveDto.getSource();
        String target = moveDto.getTarget();

        ChessGame chessGame = getChessGameFromSession(request);

        MoveService moveService = new MoveService(chessGame);

        try {
            moveService.move(source, target);
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    public Object status(Request request, Response response) {
        ChessGame chessGame = getChessGameFromSession(request);

        StatusService statusService = new StatusService(chessGame);
        StatusDto statusDto = null;

        try {
            statusDto = statusService.getStatus();
        } catch(RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        return statusDto;
    }

    public Object end(Request request, Response response) {
        ChessGame chessGame = getChessGameFromSession(request);

        chessGame.end();

        return new MessageDto("finish");
    }

    void setNewGameToSessionIfSessionIsNewOrIsFinished(Request request) {
        if(request.session().isNew() || getChessGameFromSession(request).isFinished()) {
            request.session().attribute(CHESS_GAME,
                    new ChessGame(new Board(PieceFactory.createPieces()))
            );
        }
    }

    ChessGame getChessGameFromSession(Request request) {
        return request.session().attribute(CHESS_GAME);
    }

}
