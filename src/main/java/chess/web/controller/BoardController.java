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
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BoardController {

    private final PieceDao pieceDao;
    private final TeamColorDao teamColorDao;

    public BoardController(PieceDao pieceDao, TeamColorDao teamColorDao) {
        this.pieceDao = pieceDao;
        this.teamColorDao = teamColorDao;
    }

    public Object printCurrentBoard(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        Board board = getBoard();
        List<PieceDto> pieces = PiecesDto.of(board)
                .getValue();
        model.put("pieces", pieces);
        model.put("currentTurn", board.getCurrentTurnTeamColor());
        model.put("whiteTotalScore", board.getTotalPoint(TeamColor.WHITE));
        model.put("blackTotalScore", board.getTotalPoint(TeamColor.BLACK));
        return render(model, "board.html");
    }

    public String move(Request request, Response response) {
        Board board = getBoard();
        RequestBody requestBody = RequestBody.of(request.body());
        Position from = Position.from(requestBody.get("from"));
        Position to = Position.from(requestBody.get("to"));
        board.movePiece(from, to);
        isCheckmate(response, board);
        pieceDao.updatePieces(board);
        teamColorDao.update(board.getCurrentTurnTeamColor());
        response.redirect("/chess");
        return "";
    }

    private void isCheckmate(Response response, Board board) {
        if (board.hasOneKing()) {
            response.redirect("/chess/reset");
        }
    }

    public String reset(Request request, Response response) {
        Board board = Board.create();
        pieceDao.updatePieces(board);
        teamColorDao.update(board.getCurrentTurnTeamColor());
        response.redirect("/chess");
        return "end";
    }

    private Board getBoard() {
        List<Piece> pieces = pieceDao.findAll();
        TeamColor currentTurn = teamColorDao.findCurrentTurn();
        return new Board(pieces, currentTurn);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
