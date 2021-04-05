package chess;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

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

        ChessDAO chessDAO = new ChessDAO();
        ChessService chessService = new ChessService();
        PieceService pieceService = new PieceService();

        get("/", (req, res) -> render(new HashMap<>(), "main.html"));

        post("/chess/new", (req, res) -> {
            chessDAO.deleteIfPreviousChessExists();
            chessDAO.insert();
            pieceService.saveAll(chessService.findChessId());
            res.status(201);
            res.redirect("/chess/view");
            Response response = new Response(HttpStatus.Code.CREATED, "체스가 성공적으로 생성되었습니다.");
            return GSON.toJson(response);
        });

        get("/chess/view", (req, res) -> render(new HashMap<>(), "chess.html"));

        get("/chess", (req, res) -> {
            Long chessId = chessService.findChessId();
            BoardDTO boardDTO = pieceService.makeBoard(chessId);
            ChessDTO chessDTO = chessService.makeChess(boardDTO);
            Response response = new Response(HttpStatus.Code.OK, chessDTO);
            return GSON.toJson(response);
        });

        post("/chess/move", (req, res) -> {
            Long chessId = chessService.findChessId();
            BoardDTO boardDTO = pieceService.makeBoard(chessId);
            Board board = Board.from(boardDTO);
            String turn = chessDAO.findTurnByChessId();

            MovePositionDTO movePositionDTO = GSON.fromJson(req.body(), MovePositionDTO.class);
            MovePosition movePosition = new MovePosition(movePositionDTO);
            Chess chess = new Chess(board, turn).move(movePosition);
            chessDAO.updateTurn(chessId);
            pieceDAO.update(movePositionDTO);
            if (chess.isKindDead()) {
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
