package chess.domain.Controller;

import chess.domain.board.Board;
import chess.domain.board.BoardDAO;
import chess.domain.board.BoardDTO;
import chess.domain.command.Command;
import chess.domain.player.User;
import chess.domain.player.UserDAO;
import chess.domain.result.ChessResultDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebController {

    private UserDAO userDAO;
    private BoardDAO boardDAO;

    public WebController() {
        this.userDAO = new UserDAO();
        this.boardDAO = new BoardDAO();
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
                Command startCommand = Command.from(Arrays.asList("start",
                        req.queryParams("user1"), req.queryParams("user2")));

                User firstUser = startCommand.getFirstUser();
                User secondUser = startCommand.getSecondUser();
                model.put("user1", firstUser.getName());
                model.put("user2", secondUser.getName());

                userDAO.addUser(firstUser);
                userDAO.addUser(secondUser);

                Board board = createInitialOrFindIfBoardExist(firstUser, secondUser);

                model.put("board", BoardDTO.create(board));
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Command moveCommand = Command.from(Arrays.asList("move",
                        req.queryParams("user1"), req.queryParams("user2"),
                        req.queryParams("source"), req.queryParams("target")));

                User firstUser = moveCommand.getFirstUser();
                User secondUser = moveCommand.getSecondUser();
                model.put("user1", firstUser.getName());
                model.put("user2", secondUser.getName());

                Board board = boardDAO.findByUsers(firstUser, secondUser);
                board = board.move(moveCommand.getSource(), moveCommand.getTarget());

                model.put("board", BoardDTO.create(board));

                if (board.isFinished()) {
                    board = Board.createEmpty().placeInitialPieces();
                }
                boardDAO.updateBoard(board, firstUser, secondUser);
            } catch (Exception e) {
                model.put("error", e.getMessage());
            }

            return render(model, "index.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Command statusCommand = Command.from(Arrays.asList("status",
                        req.queryParams("user1"), req.queryParams("user2")));

                User firstUser = statusCommand.getFirstUser();
                User secondUser = statusCommand.getSecondUser();
                model.put("user1", firstUser.getName());
                model.put("user2", secondUser.getName());

                Board board = boardDAO.findByUsers(firstUser, secondUser);

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

    private Board createInitialOrFindIfBoardExist(User firstUser, User secondUser) throws SQLException {
        if (boardDAO.isBoardExist(firstUser, secondUser)) {
            return boardDAO.findByUsers(firstUser, secondUser);
        }
        Board board = Board.createEmpty().placeInitialPieces();
        boardDAO.saveBoard(board, firstUser, secondUser);
        return board;
    }
}
