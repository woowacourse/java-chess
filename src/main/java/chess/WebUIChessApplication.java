package chess;

import chess.model.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get("/newgame.html", (req, res) -> {
            ChessDAO chessDAO = ChessDAO.getInstance();
            chessDAO.deleteAll();
            chessDAO.insertInit();
            Map<String, Object> model = new HashMap<>();
            model.put("board", chessDAO.selectByTurn(1).getPieces());

            ChessGame game = new ChessGame(new NewGameCreateStrategy(), 1);

            ScoreResult scoreResult = game.calculateScore();

            model.put("score", scoreResult);
            req.session().attribute("game", game);

            return render(model, "newgame.html");
        });

        get("/game.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessDAO chessDAO = ChessDAO.getInstance();
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            ChessGame game;
            game = req.session().attribute("game");

            if (!Objects.isNull(source)) { // 이어서하기
                game.movePiece(source, target);
                BoardDTO boardDTO = new BoardDTO(game.convertToList());
                chessDAO.updateBoard(boardDTO);
                if (game.checkKingDead()) {
                    String winningTeamColor = game.askWinningTeamColor(chessDAO.getLatestTurn());
                    model.put("winner", winningTeamColor);
                    chessDAO.deleteAll();

                    return render(model, "end.html");
                }

                model.put("board", boardDTO.getPieces());
            }
            if (Objects.isNull(source)) { // 게임진행
                BoardDTO boardDTO = chessDAO.selectByTurn(chessDAO.getLatestTurn());
                game = new ChessGame(new ContinueGameCreateStrategy(boardDTO), chessDAO.getLatestTurn());

                Collections.reverse(boardDTO.getPieces());

                model.put("board", boardDTO.getPieces());
            }
            req.session().attribute("game", game);

            ScoreResult scoreResult = game.calculateScore();
            model.put("score", scoreResult);

            return render(model, "game.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

