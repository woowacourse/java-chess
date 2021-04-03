package chess;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionDAO;
import chess.domain.position.MovePositionDTO;
import chess.domain.user.User;
import chess.domain.user.UserDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebUIChessApplication.class);

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");

        UserDAO userDAO = new UserDAO();
        ChessDAO chessDAO = new ChessDAO();
        MovePositionDAO movePositionDAO = new MovePositionDAO();

        get("/", (req, res) -> render(new HashMap<>(), "form.html"));

        post("/users", (req, res) -> {
            final String name = req.queryParams("name");
            User user = new User(name);
            userDAO.addUser(user);
            res.cookie("/chess", "name", name, 3600, false, true);
            res.redirect("/main");
            return "OK";
        });

        get("/main", (req, res) -> render(new HashMap<>(), "main.html"));

        post("/chess/new", (req, res) -> {
            String name = req.cookie("name");
            Long userId = userDAO.findIdByName(name);
            Optional<Long> chessId = chessDAO.findIdByUserId(userId);
            if (chessId.isPresent()) {
                chessDAO.deletePreviousChess(chessId.get());
            }
            chessDAO.saveChess(userId);
            res.redirect("/chess");
            return "OK";
        });

        get("/chess/continue", (req, res) -> {
            String name = req.cookie("name");
            Long userId = userDAO.findIdByName(name);
            Long chessId = chessDAO.findIdByUserId(userId)
                                   .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
            return "OK";
        });

        get("/chess", (req, res) -> {
            String name = req.cookie("name");
            Long userId = userDAO.findIdByName(name);
            Chess chess = Chess.createWithEmptyBoard().start();
            Long chessId = chessDAO.findIdByUserId(userId)
                                   .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
            chess = chessDAO.getChess(chessId, chess);
            Set<Map.Entry<String, String>> board = BoardDTO.from(chess).getPieceDTOs();
            Map<String, Object> model = new HashMap<>();
            model.put("board", board);
            return render(model, "chess.html");
        });

        post("/chess/move", (req, res) -> {
            String name = req.cookie("name");
            Long userId = userDAO.findIdByName(name);
            MovePositionDTO movePositionDTO = GSON.fromJson(req.body(), MovePositionDTO.class);
            MovePosition movePosition =
                    new MovePosition(movePositionDTO.getSource(), movePositionDTO.getTarget());
            Chess chess = Chess.createWithEmptyBoard()
                               .start();
            Long chessId = chessDAO.findIdByUserId(userId)
                                   .orElseThrow(() -> new IllegalStateException(
                                           "해당 이름으로 진행 중인 게임이 없습니다."));
            chess = chessDAO.getChess(chessId, chess);
            chess = chess.move(movePosition);
            movePositionDAO.move(userId, movePositionDTO);
            if (chess.isKindDead()) {
                chessDAO.deletePreviousChess(chessId);
                return GSON.toJson("king-dead");
            }
            return GSON.toJson(movePosition);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
