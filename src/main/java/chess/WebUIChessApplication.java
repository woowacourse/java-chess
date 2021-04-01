package chess;

import chess.dao.Chess;
import chess.dao.ChessDAO;
import chess.domain.grid.ChessGame;
import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.PositionDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");
        ChessGame chessGame = new ChessGame(new Grid(new NormalGridStrategy()));
        ChessDAO chessDAO = new ChessDAO();

        get("/", (req, res) -> {
            return render(new HashMap<>(), "index.html");
        });

        get("/pieces", (req, res) -> {
            return chessGame.pieceMap();
        }, GSON::toJson);

        get("/turn", (req, res) -> {
            return chessGame.turn();
        }, GSON::toJson);

        get("/score/white", (req, res) -> {
            return chessGame.score(Color.WHITE);
        }, GSON::toJson);

        get("/score/black", (req, res) -> {
            return chessGame.score(Color.BLACK);
        }, GSON::toJson);

        get("/gameover", (req, res) -> {
            return Arrays.asList(chessGame.isGameOver(), chessGame.getWinner());
        }, GSON::toJson);

        post("/move", (req, res) -> {
            PositionDTO positionDTO = GSON.fromJson(req.body(), PositionDTO.class);
            String sourcePosition = positionDTO.getSourcePosition();
            String targetPosition = positionDTO.getTargetPosition();
            try {
                chessGame.move(chessGame.grid().piece(new Position(sourcePosition)),
                        chessGame.grid().piece(new Position(targetPosition)));
                return 200;
            } catch (IllegalArgumentException error) {
                return 400;
            }
        });

        post("/start", (req, res) -> {
            try {
                chessGame.start();
                return 200;
            } catch (IllegalArgumentException error) {
                return 201;
            }
        });

        post("/reset", (req, res) -> {
            try {
                chessGame.reset();
                return 200;
            } catch (IllegalArgumentException error) {
                return 201;
            }
        });

        post("/save", (req, res) -> {
            try {
                Chess chess = new Chess("1", chessGame.gridStringify(), chessGame.turn().name());
                if (chessDAO.findByChessId("1") == null) {
                    chessDAO.addChess(chess);
                }
                chessDAO.updateChess(chess, chess.getChess(), chess.getTurn());
                return 200;
            } catch (IllegalArgumentException error) {
                return 400;
            }
        });

        post("/load", (req, res) -> {
            try {
                if (chessDAO.findByChessId("1") == null) {
                    throw new SQLDataException("저장된 보드가 없습니다.");
                }
                Chess chess = chessDAO.findByChessId("1");
                chessGame.load(chess);

                return 200;
            } catch (IllegalArgumentException error) {
                return 201;
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
