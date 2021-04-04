package chess.controller;

import chess.ChessGame;
import chess.dao.CommandDAO;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDTO;
import chess.dto.StatusDTO;
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
    public static final int START_POINT_INDEX = 0;
    public static final int END_POINT_INDEX = 1;
    private final CommandDAO commandDAO = new CommandDAO();
    private ChessGame chessGame = new ChessGame();
    private String roomId;

    public WebUIChessController() {
        commandDAO.getConnection();
    }

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

    public void start() {
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
        List<List<String>> commands = commandDAO.getCommandsByRoomId(roomId);
        for (List<String> points : commands) {
            String start_point = points.get(START_POINT_INDEX);
            String end_point = points.get(END_POINT_INDEX);
            chessGame.move(position(start_point), position(end_point));
        }
        return chessGame;
    }

    public void status() {
        Gson gson = new Gson();
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
            return gson.toJson(statusDTO);
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

    public void move() {
        Gson gson = new Gson();
        put("/move", (req, res) -> {
            Map<String, Object> requestBody = gson.fromJson(req.body(), HashMap.class);

            String startPoint = (String) requestBody.get("startPoint");
            String endPoint = (String) requestBody.get("endPoint");
            Position startPosition = position(startPoint);
            Position endPosition = position(endPoint);
            chessGame.move(startPosition, endPosition);
            commandDAO.addCommand(roomId, startPoint, endPoint);
            return gson.toJson(getPieceDTOs());
        });
    }

    private Position position(final String point) {
        return new Position(
                Character.getNumericValue(point.charAt(START_POINT_INDEX)),
                Character.getNumericValue(point.charAt(END_POINT_INDEX))
        );
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return HANDLEBARS_TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }
}
