package chess;

import chess.domain.BoardFactory;
import chess.domain.Game;
import chess.domain.Point;
import chess.domain.Team;
import chess.domain.pieces.Piece;
import chess.dto.PieceDto;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        port(8080);

        ChessGameService chessGameService = ChessGameService.getInstance();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Integer> gameIds = chessGameService.findAllId();
            model.put("gameIds", gameIds);
            return render(model, "main.html");
        });

        get("/home", (req, res) -> {
            Game game = new Game(BoardFactory.init());
            chessGameService.createNewGame();
            int gameId = chessGameService.findMaxId();
            for (Point point : game.getBoard().keySet()) {
                PieceDto pieceDto = new PieceDto(point, game.getBoard().get(point));
                chessGameService.add(gameId, pieceDto);
            }
            req.session().attribute("gameId", gameId);
            Map<String, Object> model = putBoardStatus(chessGameService, game);
            return render(model, "home.html");
        });

        get("/continue/:gameId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int gameId = Integer.parseInt(req.params(":gameId"));
            req.session().attribute("gameId", gameId);
            Game game = getGame(chessGameService, gameId);

            if (!game.isKingAlive()) {
                game.changeTurn();
                model.put("gameEnd", true);
                model.put("winner", game.getTurn());
            }
            model.putAll(putBoardStatus(chessGameService, game));
            return render(model, "home.html");
        });

        get("/move", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            Point start = new Point(source);
            Point end = new Point(target);

            int gameId = req.session().attribute("gameId");
            Game game = getGame(chessGameService, gameId);

            game.move(start, end);
            chessGameService.move(gameId, start, end);

            Map<String, Object> model = new HashMap<>();
            if (!game.isKingAlive()) {
                model.put("gameEnd", true);
                model.put("winner", game.getTurn());
            }

            game.changeTurn();
            chessGameService.toggleTurnById(gameId);

            model.putAll(putBoardStatus(chessGameService, game));

            return render(model, "home.html");
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });

        exception(Exception.class, (e, req, res) -> {
            try {
                res.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                res.redirect("/error?message=" + "URL Encoding Error");
            }
        });
    }

    private static Map<String, Object> putBoardStatus(ChessGameService chessGameService, Game game) {
        Map<String, Object> model = new HashMap<>();
        model.put("board", chessGameService.convertBoard(game.getBoard()));
        model.put("turn", game.getTurn() == Team.WHITE ? "백" : "흑");
        model.put("whiteScore", game.calculateScore(Team.WHITE));
        model.put("blackScore", game.calculateScore(Team.BLACK));
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Game getGame(ChessGameService chessGameService, int gameId) {
        List<PieceDto> pieceDtos = chessGameService.findPieceById(gameId);
        Map<Point, Piece> board = new HashMap<>();
        pieceDtos.forEach(vo -> board.put(vo.getPoint(), vo.getPiece()));
        Team turn = chessGameService.findTurnByGameId(gameId) ? Team.WHITE : Team.BLACK;
        return new Game(board, turn);
    }
}
