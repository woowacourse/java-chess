package chess;

import chess.dao.ChessDAO;
import chess.domain.grid.ChessGame;
import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.PositionDTO;
import chess.entity.Chess;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final Gson GSON = new Gson();
    private static final int SUCCESSFUL_RESPONSE = 200;
    private static final int CLIENT_ERROR_RESPONSE = 400;
    private static final String CHESS_ID = "1";

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
                return SUCCESSFUL_RESPONSE;
            } catch (IllegalArgumentException error) {
                return CLIENT_ERROR_RESPONSE;
            }
        });

        post("/start", (req, res) -> {
            chessGame.start();
            return SUCCESSFUL_RESPONSE;
        });

        post("/reset", (req, res) -> {
            chessGame.reset();
            return SUCCESSFUL_RESPONSE;
        });

        post("/save", (req, res) -> {
            Chess chess = new Chess(CHESS_ID, chessGame.gridStringify(), chessGame.turn().name());
            if (chessDAO.findByChessId(CHESS_ID) == null) {
                chessDAO.addChess(chess);
            }
            chessDAO.updateChess(chess, chess.getChess(), chess.getTurn());
            return SUCCESSFUL_RESPONSE;
        });

        post("/load", (req, res) -> {
            if (chessDAO.findByChessId(CHESS_ID) == null) {
                throw new SQLDataException("저장된 보드가 없습니다.");
            }
            Chess chess = chessDAO.findByChessId(CHESS_ID);
            chessGame.load(chess);
            return SUCCESSFUL_RESPONSE;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
