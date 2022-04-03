package chess.web.controller;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.vo.TeamColor;
import chess.web.dto.PieceDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BoardController {

    private final Board board;

    public BoardController(Board board) {
        this.board = board;
    }

    public Object printCurrentBoard(Request request, Response response) {
        List<PieceDto> pieces = new ArrayList<>();
        for (Position position : getAllPosition()) {
            addPieceDto(pieces, position);
        }
        return render(toModel(pieces), "board.html");
    }

    private List<Position> getAllPosition() {
        List<Position> positions = new ArrayList<>();
        for (Rank rank : getReverseOrderRanks()) {
            positions.addAll(Arrays.stream(File.values())
                    .map(file -> Position.of(file, rank))
                    .collect(Collectors.toList()));
        }
        return positions;
    }

    private void addPieceDto(List<PieceDto> pieces, Position position) {
        try {
            pieces.add(PieceDto.of(board.findPieceInPosition(position)));
        } catch (IllegalArgumentException e) {
            pieces.add(new PieceDto("", ""));
        }
    }

    private Map<String, Object> toModel(List<PieceDto> pieces) {
        Map<String, Object> model = new HashMap<>();
        model.put("pieces", pieces);
        model.put("turnColor", board.getCurrentTurnTeamColor());
        return model;
    }

    public String move(Request request, Response response) {
        chess.web.controller.Request requestBody = chess.web.controller.Request.of(request.body());
        Position from = Position.from(requestBody.get("from"));
        Position to = Position.from(requestBody.get("to"));
        board.movePiece(from, to);
        response.redirect("/chess");
        return "";
    }

    public String status(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        model.put("whiteTotalScore", board.getTotalPoint(TeamColor.WHITE));
        model.put("blackTotalScore", board.getTotalPoint(TeamColor.BLACK));
        return render(model, "status.html");
    }

    public String end(Request request, Response response) {
        Spark.stop();
        return "end";
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<Rank> getReverseOrderRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
