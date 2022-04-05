package chess.web.controller;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.vo.TeamColor;
import chess.web.dao.PieceDao;
import chess.web.dao.TeamColorDao;
import chess.web.dto.PieceDto;
import chess.web.dto.PiecesDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BoardController {

    public BoardController() {}

    public Object printCurrentBoard(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        Board board = getBoard();
        List<PieceDto> pieces = PiecesDto.of(board)
                .getValue();
        model.put("pieces", pieces);
        model.put("turnColor", board.getCurrentTurnTeamColor());
        return render(model, "board.html");
    }

    public String move(Request request, Response response) {
        Board board = getBoard();
        chess.web.controller.Request requestBody = chess.web.controller.Request.of(request.body());
        Position from = Position.from(requestBody.get("from"));
        Position to = Position.from(requestBody.get("to"));
        board.movePiece(from, to);
        PieceDao.updatePieces(board);
        TeamColorDao.update(board.getCurrentTurnTeamColor());
        response.redirect("/chess");
        return "";
    }

    public String status(Request request, Response response) {
        Board board = getBoard();
        Map<String, Object> model = new HashMap<>();
        model.put("whiteTotalScore", board.getTotalPoint(TeamColor.WHITE));
        model.put("blackTotalScore", board.getTotalPoint(TeamColor.BLACK));
        return render(model, "status.html");
    }

    public String end(Request request, Response response) {
        Spark.stop();
        return "end";
    }

    private Board getBoard() {
        List<Piece> pieces = PieceDao.findAll();
        TeamColor currentTurn = TeamColorDao.findCurrentTurn();
        return new Board(pieces, currentTurn);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
