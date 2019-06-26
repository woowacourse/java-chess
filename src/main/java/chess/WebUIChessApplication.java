package chess;

import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.*;
import chess.domain.pieces.Piece;
import chess.utils.DBUtil;
import chess.dto.PieceDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/templates");
//        externalStaticFileLocation("src/main/resources/templates");
        port(8080);

        DataSource dataSource = DBUtil.getDataSource();

        PieceDao pieceDao = new PieceDao(dataSource);
        GameDao gameDao = new GameDao(dataSource);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Integer> gameIds = gameDao.findAllId();
            model.put("gameIds", gameIds);
            return render(model, "main.html");
        });

        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Game game = new Game(BoardFactory.init());
            gameDao.add();
            int gameId = gameDao.findMaxId();
            for (Point point : game.getBoard().keySet()) {
                PieceDto pieceDto = new PieceDto(point, game.getBoard().get(point));
                pieceDao.add(gameId, pieceDto);
            }
            req.session().attribute("gameId", gameId);
            Map<Point, Piece> board = game.getBoard();
            Map<String, String> convertedBoard = new HashMap<>();
            for (Point point : board.keySet()) {
                convertedBoard.put(point.convertPosition(), board.get(point).getSymbol());
            }
            Gson gson = new Gson();
            model.put("board", gson.toJson(convertedBoard));
            model.put("turn", game.getTurn() == Team.WHITE ? "백" : "흑");
            model.put("whiteScore", game.calculateScore(Team.WHITE));
            model.put("blackScore", game.calculateScore(Team.BLACK));
            return render(model, "home.html");
        });

        get("/continue/:gameId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int gameId = Integer.parseInt(req.params(":gameId"));
            req.session().attribute("gameId", gameId);
            List<PieceDto> pieceDtos = pieceDao.findPieceById(gameId);
            Map<Point, Piece> board = new HashMap<>();
            pieceDtos.forEach(vo -> board.put(vo.getPoint(), vo.getPiece()));
            Team turn = gameDao.findTurnByGameId(gameId) ? Team.WHITE : Team.BLACK;
            Game game = new Game(board, turn);

            if (!game.isKingAlive()) {
                game.changeTurn();
                model.put("gameEnd", true);
                model.put("winner", game.getTurn());
            }

            Map<String, String> convertedBoard = new HashMap<>();
            for (Point point : board.keySet()) {
                convertedBoard.put(point.convertPosition(), board.get(point).getSymbol());
            }
            Gson gson = new Gson();
            model.put("board", gson.toJson(convertedBoard));
            model.put("turn", game.getTurn() == Team.WHITE ? "백" : "흑");
            model.put("whiteScore", game.calculateScore(Team.WHITE));
            model.put("blackScore", game.calculateScore(Team.BLACK));
            return render(model, "home.html");
        });

        get("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            Point start = new Point(source);
            Point end = new Point(target);

            int gameId = req.session().attribute("gameId");
            List<PieceDto> pieceDtos = pieceDao.findPieceById(gameId);
            Map<Point, Piece> board = new HashMap<>();
            pieceDtos.forEach(vo -> board.put(vo.getPoint(), vo.getPiece()));
            Team turn = gameDao.findTurnByGameId(gameId) ? Team.WHITE : Team.BLACK;
            Game game = new Game(board, turn);

            game.move(start, end);

            pieceDao.deletePieceByPosition(gameId, end);
            pieceDao.updatePosition(gameId, start, end);
            pieceDao.insertBlank(gameId, start);

            Map<Point, Piece> movedBoard = game.getBoard();


            if (!game.isKingAlive()) {
                model.put("gameEnd", true);
                model.put("winner", game.getTurn());
            }
            game.changeTurn();
            gameDao.toggleTurnById(gameId);

            model.put("turn", game.getTurn() == Team.WHITE ? "백" : "흑");

            Gson gson = new Gson();
            Map<String, String> result = new HashMap<>();
            for (Point point : movedBoard.keySet()) {
                result.put(point.convertPosition(), movedBoard.get(point).getSymbol());
            }
            model.put("board", gson.toJson(result));

            model.put("whiteScore", game.calculateScore(Team.WHITE));
            model.put("blackScore", game.calculateScore(Team.BLACK));

            return render(model, "home.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
