package chess.domain.Controller;

import chess.domain.board.Board;
import chess.domain.board.BoardDTO;
import chess.domain.board.Position;
import chess.domain.player.User;
import chess.domain.result.ChessResultDTO;
import chess.repository.ChessDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {

    private ChessDAO chessDAO;

    public WebController() {
        this.chessDAO = new ChessDAO();
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", BoardDTO.create(Board.createEmpty()));

            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            try {
                chessDAO.saveUsers(req.queryParams("user1"), req.queryParams("user2"));
                List<User> users = chessDAO.findUsers(req.queryParams("user1"), req.queryParams("user2"));

                User firstUser = users.get(0);
                User secondUser = users.get(1);

                model.put("user1", firstUser.getName());
                model.put("user2", secondUser.getName());

                Board board = chessDAO.createOrLoadBoard(firstUser, secondUser);

                model.put("board", BoardDTO.create(board));
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                List<User> users = chessDAO.findUsers(req.queryParams("user1"), req.queryParams("user2"));

                User firstUser = users.get(0);
                User secondUser = users.get(1);

                model.put("user1", firstUser.getName());
                model.put("user2", secondUser.getName());

                Board board = chessDAO.loadBoard(firstUser, secondUser);
                board = board.move(Position.from(req.queryParams("source")),
                        Position.from(req.queryParams("target")));

                model.put("board", BoardDTO.create(board));

                if (board.isFinished()) {
                    board = Board.createEmpty().placeInitialPieces();
                }
                chessDAO.saveBoard(board, firstUser, secondUser);
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                List<User> users = chessDAO.findUsers(req.queryParams("user1"), req.queryParams("user2"));

                User firstUser = users.get(0);
                User secondUser = users.get(1);

                model.put("user1", firstUser.getName());
                model.put("user2", secondUser.getName());

                Board board = chessDAO.loadBoard(firstUser, secondUser);

                model.put("result", ChessResultDTO.create(board.calculateResult()));
                model.put("board", BoardDTO.create(board));
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
