package chess.controller;

import chess.application.dto.ChessBoardDto;
import chess.application.dto.ChessPositionDto;
import chess.application.ChessService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController implements Controller {
    private static class ChessControllerLazyHolder {
        private static final ChessController INSTANCE = new ChessController();
    }

    public static ChessController getInstance() {
        return ChessControllerLazyHolder.INSTANCE;
    }

    public void init() {
        get("/", this::getMain);
        get("/chessBoard", this::getChessBoard);
        post("/chessBoard", this::postChessBoard);
        get("/chessScore", this::getChessScore);
        post("/newRound", this::postNextChessRound);
    }

    private String getMain(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        int roundId = ChessService.getInstance().getLastRoundId();
        request.session().attribute("roundId", roundId);
        return render(model, "index.html");
    }

    private String getChessBoard(Request request, Response response) {
        Gson gson = new Gson();
        int roundId = request.session().attribute("roundId");

        return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
    }

    private String postChessBoard(Request request, Response response) {
        Gson gson = new Gson();
        ChessPositionDto chessPositionDto = gson.fromJson(request.body(), ChessPositionDto.class);

        int roundId = request.session().attribute("roundId");

        try {
            ChessBoardDto chessBoardDTO = ChessService.getInstance().getChessBoard(chessPositionDto, roundId);
            return gson.toJson(chessBoardDTO);
        } catch (IllegalArgumentException e) {
            return "error";
        }
    }

    private String getChessScore(Request request, Response response) {
        Gson gson = new Gson();

        int roundId = request.session().attribute("roundId");

        return gson.toJson(ChessService.getInstance().getChessScore(roundId));
    }

    private String postNextChessRound(Request request, Response response) {
        Gson gson = new Gson();
        int roundId = request.session().attribute("roundId");

        roundId++;

        ChessService.getInstance().addRound(roundId);
        request.session().attribute("roundId", roundId);

        return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
    }
}
