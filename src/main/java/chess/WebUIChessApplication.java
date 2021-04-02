package chess;

import chess.domain.ChessBoard;
import chess.domain.dao.ChessBoardDAO;
import chess.domain.dao.ChessBoardDTOForDAO;
import chess.domain.dto.MovementDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PieceMaker;
import chess.domain.piece.Piece;
import chess.domain.pieceinformations.State;
import chess.domain.pieceinformations.TeamColor;
import chess.domain.position.Position;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.*;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final ChessBoardDAO chessBoardDao = new ChessBoardDAO();
    private static ChessBoard chessBoard = new ChessBoard();

    public static void main(String[] args) {
        staticFileLocation("/static");
        Gson gson = new Gson();
        Connection connection = chessBoardDao.getConnection();

        post("/chessboard", (req, res) -> {
            chessBoard.move(gson.fromJson(req.body(), MovementDto.class));
            return chessBoard.getChessBoardDto();
        }, gson::toJson);

        post("/chessboard/save", (req, res) -> {
            chessBoardDao.removePositions();
            Map<String, PieceDto> chessBoardDto = chessBoard.getChessBoardDto();
            List<ChessBoardDTOForDAO> results = new ArrayList<>();
            for (Map.Entry<String, PieceDto> eachInfo : chessBoardDto.entrySet()) {
                PieceDto value = eachInfo.getValue();
                results.add(new ChessBoardDTOForDAO(eachInfo.getKey(), value.getTeamColor(), value.getPieceType(), value.getAlive()));
            }
            chessBoardDao.addPositions(results);
            return chessBoard.result();
        }, gson::toJson);

        get("/chessboard", (req, res) -> {
            res.type("application/json");
            return chessBoard.getChessBoardDto();
        }, gson::toJson);

        get("/chessboard/result", (req, res) -> {
            res.type("application/json");
            return chessBoard.result();
        }, gson::toJson);

        get("/chessboard/end", (req, res) -> {
            res.type("application/json");
            chessBoard.terminate();
            chessBoardDao.removePositions();
            return chessBoard.result();
        }, gson::toJson);

        get("/chessboard/saved", (req, res) -> {
            res.type("application/json");
            List<ChessBoardDTOForDAO> previousGame = chessBoardDao.findByGameId("1");

            Map<Position, Piece> boardFromDB = new LinkedHashMap<>();
            for (ChessBoardDTOForDAO eachInfo : previousGame) {
                boardFromDB.put(Position.valueOf(eachInfo.getPosition()),
                        PieceMaker.getInstance(eachInfo.getPieceType(),
                                TeamColor.getInstance(eachInfo.getTeamColor()),
                                Position.valueOf(eachInfo.getPosition()),
                                State.getInstance(eachInfo.getAlive())
                        ));
            }
            if (boardFromDB.isEmpty()) {
                chessBoard = new ChessBoard();
                return chessBoard.getChessBoardDto();
            }
            chessBoard = new ChessBoard(boardFromDB);
            return chessBoard.getChessBoardDto();
        }, gson::toJson);


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
