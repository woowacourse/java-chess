package chess.webview;

import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebOutputView {
    private static final String INITIAL_PAGE_PATH = "../public/index.html";
    private static final String BOARD_PAGE_PATH = "board.html";

    public static String goInitialPage() {
        return render(null, INITIAL_PAGE_PATH);
    }

    public static String goBoardPage(Map<ChessBoardPosition, ChessPiece> boardData) {
        return render(makeBoardModel(boardData), BOARD_PAGE_PATH);
    }

    public static String goStatusPage(Map<Team, Double> teamScore, Team winner) {
        Map<String, Object> model = WebOutputView.makeStatusModel(teamScore, winner);
        return render(model, "status.html");
    }

    private static Map<String, Object> makeStatusModel(Map<Team, Double> teamScore, Team winner) {
        Map<String, Object> model = new HashMap<>();
        model.put("whiteScore", teamScore.get(Team.WHITE));
        model.put("blackScore", teamScore.get(Team.BLACK));
        model.put("winner", Team.of(winner));
        return model;
    }

    private static Map<String, Object> makeBoardModel(Map<ChessBoardPosition, ChessPiece> mapData) {
        Map<String, Object> boardModel = new HashMap<>();
        for (Entry<ChessBoardPosition, ChessPiece> entry : mapData.entrySet()) {
            boardModel.put(chessBoardToString(entry.getKey()), ChessPieceImagePath.of(entry.getValue()));
        }
        return boardModel;
    }

    private static String chessBoardToString(ChessBoardPosition chessBoardPosition) {
        return ColumnName.of(chessBoardPosition.getColumn()) + RowName.of(chessBoardPosition.getRow());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
