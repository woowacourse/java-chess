package chess.controller;

import chess.domain.ChessGameManager;
import chess.domain.position.Position;
import chess.dto.CommonDto;
import chess.dto.ErrorResponse;
import chess.dto.GameStatusDto;
import chess.dto.StatusCode;
import chess.exception.DomainException;
import com.google.gson.Gson;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class WebController {
    private final Gson gson = new Gson();
    private final ChessGameManager chessGameManager;

    public WebController(ChessGameManager chessGameManager) {
        this.chessGameManager = chessGameManager;
    }

    public void run() {
        get("/newgame", this::receiveNewGameRequest, gson::toJson);
        post("/move", this::receiveMoveRequest, gson::toJson);
    }

    private CommonDto<?> receiveNewGameRequest(Request request, Response response) {
        try {
            chessGameManager.start();
            return new CommonDto<GameStatusDto>(
                    StatusCode.OK,
                    "새로운 게임을 시작합니다.",
                    GameStatusDto.from(chessGameManager));
        } catch (DomainException e) {
            return new CommonDto<ErrorResponse>(
                    StatusCode.BAD_REQUEST,
                    e.getMessage(),
                    new ErrorResponse());
        }
    }

    private CommonDto<?> receiveMoveRequest(Request request, Response response) {
        try {
            JSONObject jsonObject = new JSONObject(request.body());
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");

            chessGameManager.move(Position.of(from), Position.of(to));
            return new CommonDto<GameStatusDto>(
                    StatusCode.OK,
                    "기물을 이동했습니다.",
                    GameStatusDto.from(chessGameManager));
        } catch (DomainException e) {
            return new CommonDto<ErrorResponse>(
                    StatusCode.BAD_REQUEST,
                    e.getMessage(),
                    new ErrorResponse());
        }
    }
}
