package chess.controller;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.board.Row;
import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.game.GameStatus;
import chess.domain.game.Team;
import chess.domain.move.Coordinate;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private static Board board;
    private static BoardDAO boardDAO = new BoardDAO();

    public static String index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("blackScore", 38);
        model.put("whiteScore", 38);

        return render(model, "index.html");
    }

    public static Object newGame(Request request, Response response) throws SQLException {
        boardDAO.initialize();
        board = BoardFactory.createBoard();
        boardDAO.updateDB(board);
        return generateJSON(board);
    }


    private static String generateJSON(Board board) {
        JSONObject jsonObject = new JSONObject();
        JSONArray chessPieceArray = new JSONArray();

        List<Row> rows = board.getBoard();
        for (int i = 0; i < rows.size(); i++) {
            List<ChessPiece> chessPieces = rows.get(i).getChessPieces();
            for (int j = 0; j < chessPieces.size(); j++) {
                ChessPiece chessPiece = chessPieces.get(j);
                if (!(chessPiece instanceof Blank)) {
                    JSONObject chessPieceInfo = new JSONObject();
                    chessPieceInfo.put("name", chessPiece.getName());
                    chessPieceInfo.put("team", chessPiece.getTeam().getTeamName());
                    chessPieceInfo.put("x", i + 1);
                    chessPieceInfo.put("y", j + 1);
                    chessPieceArray.add(chessPieceInfo);
                }
            }
        }
        jsonObject.put("chessPieces", chessPieceArray);
        System.out.println(jsonObject.toJSONString() + " <<");

        return jsonObject.toJSONString();
    }

    public static Object move(Request request, Response response) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        QueryParamsMap map = request.queryMap();
        String startX = map.get("startX").value();
        String startY = map.get("startY").value();
        String targetX = map.get("targetX").value();
        String targetY = map.get("targetY").value();
        Position startPosition = Position.of(Coordinate.of(startX), Coordinate.of(startY));
        Position targetPosition = Position.of(Coordinate.of(targetX), Coordinate.of(targetY));

        MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

        try {
            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            return error.getMessage();
        }
        boardDAO.updateDB(board);
        return generateJSON(board);
    }

    public static String continueGame(Request request, Response response) throws SQLException {
        board = BoardFactory.createContinueBoard(boardDAO);
        return generateJSON(board);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
