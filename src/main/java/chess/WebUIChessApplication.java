package chess;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;
import chess.controller.PieceController;
import chess.domain.board.Board;
import chess.domain.board.BoardDTO;
import chess.domain.chess.Chess;
import chess.domain.chess.ChessDAO;
import chess.domain.chess.ChessDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionDTO;
import chess.domain.service.ChessService;
import chess.domain.service.PieceService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final HandlebarsTemplateEngine TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
    private static final Logger logger = LoggerFactory.getLogger(WebUIChessApplication.class);

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessController chessController = new ChessController();
        PieceController pieceController = new PieceController();

        ChessDAO chessDAO = new ChessDAO();
        ChessService chessService = new ChessService();
        PieceService pieceService = new PieceService();

        get("/", (req, res) -> render(new HashMap<>(), "main.html"));

        path("/chess", () -> {
            path("", () -> {
                before("/*", (req, res) -> logger.info("Received chess api call"));
                get("", (req, res) -> chessController.get());
                post("", (req, res) -> chessController.save());
                put("", (req, res) -> chessController.update());
                delete("", (req, res) -> chessController.delete());
            });

            path("/:chessId/piece", () -> {
                before("/*", (req, res) -> logger.info("Received chess api call"));
                get("", (req, res) -> pieceController.get());
                post("", (req, res) -> pieceController.save());
                put("", (req, res) -> pieceController.update());
                delete("", (req, res) -> pieceController.delete());
            });
        });

        post("/chess", (req, res) -> {
            // chess delete, piece delete
            chessDAO.deleteIfPreviousChessExists();

            // chese post
            chessDAO.insert();

            // piece post
            pieceService.saveAll(chessService.findChessId());
            res.status(201);
            res.redirect("/chess/view");
            Response response = new Response(HttpStatus.Code.CREATED, "체스가 성공적으로 생성되었습니다.");
            return GSON.toJson(response);
        });

        get("/chess/view", (req, res) -> render(new HashMap<>(), "chess.html"));

        get("/chess", (req, res) -> {
            // piece get
            Long chessId = chessService.findChessId();
            BoardDTO boardDTO = pieceService.makeBoard(chessId);

            // chess get
            ChessDTO chessDTO = chessService.makeChess(boardDTO);
            Response response = new Response(HttpStatus.Code.OK, chessDTO);
            return GSON.toJson(response);
        });

        post("/chess/move", (req, res) -> {
            Long chessId = chessService.findChessId();

            // chess get
            BoardDTO boardDTO = pieceService.makeBoard(chessId);
            Board board = Board.from(boardDTO);
            String turn = chessDAO.findTurnByChessId();

            // position request param
            MovePositionDTO movePositionDTO = GSON.fromJson(req.body(), MovePositionDTO.class);
            MovePosition movePosition = new MovePosition(movePositionDTO);
            Chess chess = new Chess(board, turn).move(movePosition);

            // piece patch
            chessDAO.updateTurn(chessId);

            // piece patch
            pieceDAO.update(movePositionDTO);
            if (chess.isKindDead()) {

                // chess delete
                chessDAO.deleteIfPreviousChessExists();
                return GSON.toJson("king-dead");
            }
            return GSON.toJson(movePosition);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
