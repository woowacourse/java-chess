package chess;

import chess.dao.ChessDAO;
import chess.database.DBConnector;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.view.WebInputParser;
import chess.domain.view.WebOutputView;
import chess.dto.ChessDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static Connection conn = DBConnector.getConnection();
    private static ChessDAO chessDAO = new ChessDAO(conn);
    private static ChessGame chessGame;

    public static void main(String[] args) {
        staticFiles.location("/templates");

        chessGameSetting();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/first", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.put("turn", WebOutputView.printTurn(chessGame.getTurn()));
                model.put("blackscore", chessGame.getStatus(Team.BLACK));
                model.put("whitescore", chessGame.getStatus(Team.WHITE));
                model.put("board", WebOutputView.printBoard(chessGame.getBoard()));

                ChessDTO chessDTO = chessGame.toDTO();
                chessDAO.addChessGame(chessDTO);
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "chess.html");
        });

        post("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Position source = WebInputParser.getSourcePosition(req.queryParams("source"));
                Position target = WebInputParser.getTargetPosition(req.queryParams("target"));

                chessGame.play(source, target);

                if (chessGame.isGameEnd()) {
                    model.put("winner", WebOutputView.printTurn(Team.switchTeam(chessGame.getTurn())));
                    chessDAO.deleteChessGame();
                    chessGame = new ChessGame();
                    return render(model, "winner.html");
                }

                ChessDTO chessDTO = chessGame.toDTO();
                chessDAO.deleteChessGame();
                chessDAO.addChessGame(chessDTO);

                model.put("turn", WebOutputView.printTurn(chessGame.getTurn()));
                model.put("blackscore", chessGame.getStatus(Team.BLACK));
                model.put("whitescore", chessGame.getStatus(Team.WHITE));
                model.put("board", WebOutputView.printBoard(chessGame.getBoard()));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "chess.html");
        });
    }

    private static void chessGameSetting() {
        try {
            if (chessDAO.isTableEmpty()) {
                chessGame = new ChessGame();
            }
            if (!chessDAO.isTableEmpty()) {
                chessGame = chessDAO.findChessGame();
                chessDAO.deleteChessGame();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
