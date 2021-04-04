package chess;

import java.util.HashMap;
import java.util.List;
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
import chess.domain.piece.PieceDAO;
import chess.domain.piece.PieceDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionDTO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebUIChessApplication.class);

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessDAO chessDAO = new ChessDAO();
        PieceDAO pieceDAO = new PieceDAO();

        get("/", (req, res) -> render(new HashMap<>(), "main.html"));

        post("/chess/new", (req, res) -> {
            chessDAO.deleteIfPreviousChessExists();
            chessDAO.insert();

            Chess chess = Chess.createWithEmptyBoard()
                               .start();
            final Long chessId = chessDAO.findChessId()
                                         .orElseThrow(() -> new IllegalStateException(
                                                 "진행 중인 게임이 없습니다."));
            BoardDTO boardDTO = BoardDTO.from(chess);
            pieceDAO.saveAll(chessId, boardDTO);
            res.status(201);
            res.redirect("/chess/view");
            Response response = new Response(HttpStatus.Code.CREATED, "체스가 성공적으로 생성되었습니다.");
            return GSON.toJson(response);
        });

        get("/chess/view", (req, res) -> render(new HashMap<>(), "chess.html"));

        get("/chess", (req, res) -> {
            final Long chessId = chessDAO.findChessId()
                                         .orElseThrow(() -> new IllegalStateException(
                                                 "진행 중인 게임이 없습니다."));
            List<PieceDTO> pieceDTOS = pieceDAO.findPiecesByChessId(chessId);
            BoardDTO boardDTO = new BoardDTO(pieceDTOS);
            String lastTurn = chessDAO.findTurnByChessId();
            ChessDTO chessDTO = new ChessDTO(lastTurn, boardDTO);
            Response response = new Response(HttpStatus.Code.OK, chessDTO);
            return GSON.toJson(response);
        });

        post("/chess/move", (req, res) -> {
            final Long chessId = chessDAO.findChessId()
                                         .orElseThrow(() -> new IllegalStateException(
                                                 "진행 중인 게임이 없습니다."));
            List<PieceDTO> pieceDTOS = pieceDAO.findPiecesByChessId(chessId);
            BoardDTO boardDTO = new BoardDTO(pieceDTOS);
            Board board = Board.from(boardDTO);
            String turn = chessDAO.findTurnByChessId();

            MovePositionDTO movePositionDTO = GSON.fromJson(req.body(), MovePositionDTO.class);
            MovePosition movePosition =
                    new MovePosition(movePositionDTO.getSource(), movePositionDTO.getTarget());
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
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
