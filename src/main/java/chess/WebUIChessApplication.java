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

import chess.domain.chess.ChessDAO;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebUIChessApplication.class);

    public static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessDAO chessDAO = new ChessDAO();

        get("/", (req, res) -> render(new HashMap<>(), "main.html"));

        post("/chess/new", (req, res) -> {
            chessDAO.deleteIfPreviousChessExists();
            chessDAO.save();
            res.status(201);
            res.redirect("/chess");
            Response response = new Response(HttpStatus.Code.CREATED, "체스가 성공적으로 생성되었습니다.");
            return GSON.toJson(response);
        });

        get("/chess", (req, res) -> {
            chessDAO.findAnyChessId()
                    .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
            Response response = new Response(HttpStatus.Code.OK, "OK");
            return GSON.toJson(response);
        });

        //        get("/chess", (req, res) -> {
        //            Chess chess = Chess.createWithEmptyBoard().start();
        //            Long chessId = chessDAO.findAnyChessId()
        //                                   .orElseThrow(() -> new IllegalStateException("진행 중인 게임이 없습니다."));
        //            chess = chessDAO.getChess(chessId, chess);
        //            Set<Map.Entry<String, String>> board = BoardDTO.from(chess).getPieceDTOs();
        //            Map<String, Object> model = new HashMap<>();
        //            model.put("board", board);
        //            return render(model, "chess.html");
        //        });
        //
        //        post("/chess/move", (req, res) -> {
        //            String name = req.cookie("name");
        //            MovePositionDTO movePositionDTO = GSON.fromJson(req.body(), MovePositionDTO.class);
        //            MovePosition movePosition =
        //                    new MovePosition(movePositionDTO.getSource(), movePositionDTO.getTarget());
        //            Chess chess = Chess.createWithEmptyBoard()
        //                               .start();
        //            Long chessId = chessDAO.findIdByUserId(userId)
        //                                   .orElseThrow(() -> new IllegalStateException(
        //                                           "해당 이름으로 진행 중인 게임이 없습니다."));
        //            chess = chessDAO.getChess(chessId, chess);
        //            chess = chess.move(movePosition);
        //            movePositionDAO.move(userId, movePositionDTO);
        //            if (chess.isKindDead()) {
        //                chessDAO.deletePreviousChess(chessId);
        //                return GSON.toJson("king-dead");
        //            }
        //            return GSON.toJson(movePosition);
        //        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
