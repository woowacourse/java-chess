package controller;

import chess.dao.ChessTurnDAO;
import chess.domain.board.GameOverException;
import chess.domain.piece.PieceColor;
import chess.dto.ChessGameDTO;
import chess.service.ChessGameService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
        public static final String HOME_PATH = "/";
        public static final String CHESSGAME_PATH ="/chessgame";
        public static final String MOVE_PATH ="/move";
        public static final String STATUS_PATH ="/status";

        public static String home(Request request, Response response) throws SQLException {
            Map<String, Object> model = new HashMap<>();
            model.put("games", ChessTurnDAO.getInstance().selectChessGames());
            return render(model, "index.html");
        }

        private static String render(Map<String, Object> model, String templatePath) {
                return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
        }

        public static String chessGame(Request request, Response response) throws SQLException {
            Map<String, Object> model = new HashMap<>();

            int id = ChessGameService.getInstance().getId(request.queryParams("id"));
            ChessGameDTO chessGameDTO = ChessGameService.getInstance().getGame(id);

            request.session().attribute("gameId", id);
            model.put("boardJson", new Gson().toJson(chessGameDTO.getBoard().getBoard()));
            model.put("turn", chessGameDTO.getTurn());
            return render(model, "chessgame.html");
        }

        public static String move(Request request, Response response) throws SQLException {
            Map<String, Object> model = new HashMap<>();

            int id = request.session().attribute("gameId");
            ChessGameDTO chessGameDTO = ChessGameService.getInstance().getGame(id);

            try {
                chessGameDTO = ChessGameService.getInstance().move(id, request.queryParams("from"), request.queryParams("to"));
            } catch (GameOverException e) {
                model.put("winner", e.getMessage());
                return render(model, "gameover.html");
            } catch (RuntimeException e) {
                model.put("error", e.getMessage());
            }

            model.put("boardJson", new Gson().toJson(chessGameDTO.getBoard().getBoard()));
            model.put("turn", chessGameDTO.getTurn());
            return render(model, "chessgame.html");
        }

        public static String status(Request request, Response response) throws SQLException {
            Map<String, Object> model = new HashMap<>();

            int id = request.session().attribute("gameId");
            ChessGameDTO chessGameDTO = ChessGameService.getInstance().getStatus(id);

            model.put("boardJson", new Gson().toJson(chessGameDTO.getBoard().getBoard()));
            model.put("turn", chessGameDTO.getTurn());
            model.put("whiteStatus", chessGameDTO.getStatus().get(PieceColor.WHITE));
            model.put("blackStatus", chessGameDTO.getStatus().get(PieceColor.BLACK));
            return render(model, "chessgame.html");
        }
}
