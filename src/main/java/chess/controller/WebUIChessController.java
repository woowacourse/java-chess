package chess.controller;

import chess.ChessGame;
import chess.dao.CommandDAO;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDTO;
import chess.dto.StatusDTO;
import chess.dto.PointsDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.put;

public final class WebUIChessController {
    private static final HandlebarsTemplateEngine HANDLEBARS_TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
    private static final Gson GSON = new Gson();

    private final CommandDAO commandDAO = new CommandDAO(
            "localhost:13306",
            "db_chess",
            "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8",
            "root",
            "root"
    );
    private ChessGame chessGame = new ChessGame();
    private String roomId;

    public void run() {
        mainPage();
        start();
        move();
        status();
    }

    private void mainPage() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void start() {
        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            roomId = req.queryParams("roomId");
            System.out.println("입력받은 방 코드는 : " + roomId);
            chessGame = getChessGameByRoomId(roomId);

            model.put("pieces", getPieceDTOs());
            return render(model, "chess.html");
        });
    }

    private ChessGame getChessGameByRoomId(final String roomId) throws SQLException {
        ChessGame chessGame = new ChessGame();
        for (PointsDTO points : commandDAO.getCommandsByRoomId(roomId)) {
            String start_point = points.getStartPoint();
            String end_point = points.getEndPoint();
            chessGame.move(new Position(start_point), new Position(end_point));
        }
        return chessGame;
    }

    private void status() {
        get("/status", (req, res) -> {
            StatusDTO statusDTO = new StatusDTO(
                    String.valueOf(chessGame.getScoreByTeam(Team.WHITE)),
                    String.valueOf(chessGame.getScoreByTeam(Team.BLACK)),
                    getWinner(),
                    chessGame.isKingDieEnd()
            );
            if (chessGame.isKingDieEnd()) {
                commandDAO.deleteCommandsByRoomId(roomId);
            }
            return GSON.toJson(statusDTO);
        });
    }

    private String getWinner() {
        if (chessGame.isKingDieEnd()) {
            return String.valueOf(chessGame.winner());
        }
        return "NoWinner";
    }

    private List<PieceDTO> getPieceDTOs() {
        List<PieceDTO> pieceDTOGroup = new ArrayList<>();
        for (Piece piece : getEntirePieces()) {
            Position piecePosition = piece.getPosition();
            PieceDTO pieceDTO = new PieceDTO(piece.getTeam(), piecePosition.getRow() + String.valueOf(piecePosition.getColumn()), piece.getInitial());
            pieceDTOGroup.add(pieceDTO);
        }
        return pieceDTOGroup;
    }

    private List<Piece> getEntirePieces() {
        Board board = chessGame.getBoard();
        List<Piece> pieces = board.piecesByTeam(Team.BLACK).toList();
        pieces.addAll(board.piecesByTeam(Team.WHITE).toList());
        return pieces;
    }

    private void move() {
        put("/move", (req, res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);

            String startPoint = (String) requestBody.get("startPoint");
            String endPoint = (String) requestBody.get("endPoint");
            chessGame.move(new Position(startPoint), new Position(endPoint));
            commandDAO.addCommand(roomId, startPoint, endPoint);
            return GSON.toJson(getPieceDTOs());
        });
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
