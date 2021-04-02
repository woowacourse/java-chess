package chess.controller;

import chess.ChessGame;
import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDTO;
import chess.dto.StatusDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.put;

public class WebUIChessController {
    private ChessGame chessGame = new ChessGame();

    public WebUIChessController() {
    }

    public void run() {
        start();
        move();
        status();
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
            return gson.toJson(statusDTO);
        });
    }

    private String getWinner() {
        if (chessGame.isKingDieEnd()) {
            return String.valueOf(chessGame.winner());
        }
        return "NoWinner";
    }

    public void start() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            getPieces(model);
            return render(model, "chess.html");
        });
    }

    private void getPieces(final Map<String, Object> model) {
        List<PieceDTO> pieceDTOGroup = new ArrayList<>();

        for (Piece piece : getEntirePieces()) {
            Position piecePosition = piece.getPosition();
            PieceDTO pieceDTO = new PieceDTO(piece.getTeam(), piecePosition.getRow() + String.valueOf(piecePosition.getColumn()), piece.getInitial());
            pieceDTOGroup.add(pieceDTO);
        }
        model.put("pieces", pieceDTOGroup);
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

            Map<String, Object> model = new HashMap<>();
            String startPoint = (String) requestBody.get("startPoint");
            String endPoint = (String) requestBody.get("endPoint");

            Position startPosition = position(startPoint);
            Position endPosition = position(endPoint);
            chessGame.move(startPosition, endPosition);

            getPieces(model);
            return gson.toJson(model.get("pieces"));
            //return render(model, "chess.html");
        });
    }

    private Position position(final String point) {
        return new Position(
                Character.getNumericValue(point.charAt(0)),
                Character.getNumericValue(point.charAt(1))
        );
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
