package chess.controller;

import chess.domain.Position;
import chess.domain.TeamColor;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessResult;
import chess.util.StringPositionConverter;
import chess.view.BoardDto;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static chess.WebUIChessApplication.render;

public class WebChessAction {

    private static WebChessAction instance;
    private ChessGame chessGame;

    private WebChessAction() {
    }

    public static WebChessAction getInstance() {
        if (instance == null) {
            instance = new WebChessAction();
        }
        return instance;
    }

    public String index(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public String start(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        chessGame = new ChessGame();
        BoardDto dto = new BoardDto(chessGame.getPieces(), chessGame.getBoardSize(), chessGame.getCurrentColor());
        model.put("board", dto);
        model.put("currentTeam", chessGame.getCurrentColor());
        return render(model, "game.html");
    }

    public String move(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        Position currentPosition = StringPositionConverter
                .convertToPosition(req.queryParams("source"));
        Position targetPosition = StringPositionConverter
                .convertToPosition(req.queryParams("target"));

        chessGame.move(currentPosition, targetPosition);
        boolean checked = chessGame.checked();
        boolean isKingDead = chessGame.isKingDead();
        BoardDto board = new BoardDto(chessGame.getPieces(), chessGame.getBoardSize(), chessGame.getCurrentColor());

        model.put("board", board);
        model.put("currentTeam", chessGame.getCurrentColor());
        model.put("checked", checked);
        model.put("isKingDead", isKingDead);
        return render(model, "game.html");
    }

    public String end(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public String status(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        ChessResult chessResult = chessGame.result();
        TeamColor winner = TeamColor.BLACK;
        if (chessResult.getWhiteTeamScore() > chessResult.getBlackTeamScore()) {
            winner = TeamColor.WHITE;
        }
        BoardDto board = new BoardDto(chessGame.getPieces(), chessGame.getBoardSize(), chessGame.getCurrentColor());
        model.put("board", board);
        model.put("currentTeam", chessGame.getCurrentColor());
        model.put("result", chessResult);
        model.put("winner", winner.toString());
        return render(model, "game.html");
    }
}
