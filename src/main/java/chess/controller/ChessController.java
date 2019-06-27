package chess.controller;

import chess.dto.ChessBoardDto;
import chess.dto.ChessPositionDto;
import chess.application.ChessService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController implements Controller {
    private static ChessController chessController;
    private ChessService chessService = ChessService.getInstance();

    private ChessController() {
    }

    public static ChessController getInstance() {
        if (Objects.isNull(chessController)) {
            return new ChessController();
        }
        return chessController;
    }

    public void init() {
        get("/", this::main);
        get("/chessBoard", this::getChessBoard);
        post("/chessBoard", this::postChessBoard);
        get("/chessScore", this::chessScorePage);
        post("/newRound", this::nextChessRoundPage);
    }

    private String main(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int roundId = chessService.getLastRoundId();
        req.session().attribute("roundId", roundId);
        return render(model, "index.html");
    }

    private String getChessBoard(Request req, Response res) {
        Gson gson = new Gson();
        int roundId = req.session().attribute("roundId");

        return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
    }

    private String postChessBoard(Request req, Response res) {
        Gson gson = new Gson();
        ChessPositionDto chessPositionDto = gson.fromJson(req.body(), ChessPositionDto.class);

        int roundId = req.session().attribute("roundId");

        try {
            ChessBoardDto chessBoardDTO = ChessService.getInstance().getChessBoard(chessPositionDto, roundId);
            return gson.toJson(chessBoardDTO);
        } catch (IllegalArgumentException e) {
            return "error";
        }
    }

    private String chessScorePage(Request req, Response res) {
        Gson gson = new Gson();

        int roundId = req.session().attribute("roundId");

        return gson.toJson(ChessService.getInstance().getChessScore(roundId));
    }

    private String nextChessRoundPage(Request req, Response res) {
        Gson gson = new Gson();
        int roundId = req.session().attribute("roundId");

        roundId++;

        ChessService.getInstance().addRound(roundId);
        req.session().attribute("roundId", roundId);

        return gson.toJson(ChessService.getInstance().getChessBoardDTO(roundId));
    }
}
