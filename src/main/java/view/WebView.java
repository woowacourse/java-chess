package view;

import model.board.Board;
import model.board.Position;
import model.game.Game;
import model.game.Player;
import model.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WebView {
    public static String printIndexPage(final Game game) {
        final Map<String, Object> model = defaultModel(game);
        model.put("board", WebView.drawBoard(game));
        model.put("uiButtons", WebView.drawChoiceButton() + WebView.drawRestartButton());
        model.put("submitUrl", "/select");
        return render(model, "game.html");
    }

    public static String printSelectPage(final Game game, final Position position) {
        final Map<String, Object> model = defaultModel(game);
        model.put("board", WebView.drawBoard(game, game.getPossibleDestinationsOf(position)));
        model.put("uiButtons", WebView.drawConfirmOrCancelButtons() + WebView.drawRestartButton());
        model.put("submitUrl", "/confirm");
        return render(model, "game.html");
    }

    public static String printWrongChoicePage(final Game game, final String errorMessage) {
        final Map<String, Object> model = defaultModel(game);
        model.put("board", WebView.drawBoard(game));
        model.put("uiButtons", WebView.drawChoiceButton() + WebView.drawRestartButton());
        model.put("submitUrl", "/select");
        model.put("message", errorMessage);
        return render(model, "game.html");
    }

    public static String printWrongChoicePage(final Game game, final Position position, final String errorMessage) {
        final Map<String, Object> model = defaultModel(game);
        model.put("board", WebView.drawBoard(game, game.getPossibleDestinationsOf(position)));
        model.put("uiButtons", WebView.drawConfirmOrCancelButtons() + WebView.drawRestartButton());
        model.put("submitUrl", "/confirm");
        model.put("message", errorMessage);
        return render(model, "game.html");
    }

    public static String printEndPage(final Game game) {
        final Map<String, Object> model = defaultModel(game);
        model.put("board", WebView.drawBoard(game));
        model.put("uiButtons", WebView.drawRestartButton());
        model.put("submitUrl", "/");
        model.put("message", game.turn().team().toggle() + "이 승리하였습니다.");
        return render(model, "game.html");

    }

    private static Map<String, Object> defaultModel(final Game game) {
        return new HashMap<String, Object>() {{
            put("turn", game.turn().team() + " (" + game.turn().count() + ")");
            put("scoreOfWhite", game.getCurrentScore(Player.WHITE));
            put("scoreOfBlack", game.getCurrentScore(Player.BLACK));
        }};
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String drawBoard(Game game, List<Position> possibleChoices) {
        final StringBuilder result = new StringBuilder();
        for (int i = Board.WIDTH; i > 0; i--) {
            result.append(drawRow(game.board(), i));
            result.append(drawRadioButtons(possibleChoices, i));
        }
        return result.toString();
    }

    private static String drawBoard(final Game game) {
        return drawBoard(
                game,
                game.board().getPieces()
                            .filter(p -> p.team() == game.turn().team())
                            .map(Piece::position)
                            .collect(Collectors.toList())
        );
    }

    private static String drawRow(final Board board, final int number) {
        final StringBuilder result = new StringBuilder("<tr>");
        for (int i = 0; i < Board.WIDTH; i++) {
            final Optional<Piece> piece = board.getPieceAt(Position.of(String.valueOf((char) (i + 'a')) + number));
            if (piece.isPresent()) {
                result.append(colorTile(i, number) + piece.get().toString() + "</td>");
                continue;
            }
            result.append(colorTile(i, number) + "&nbsp;</td>");
        }
        result.append("</tr>");
        return result.toString();
    }

    private static String colorTile(final int row, final int col) {
        return ((row + col) % 2 == 0) ? "<td>" : "<td bgcolor=\"#cc881c\">";
    }

    private static String drawRadioButtons(final List<Position> possibleChoices, final int number) {
        final StringBuilder result = new StringBuilder("<tr height=\"15px\" style=\"font-size: 0px;\">");
        for (int i = 0; i < Board.WIDTH; i++) {
            String position = String.valueOf((char) (i + 'a')) + number;
            if (possibleChoices.contains(Position.of(position))) {
                result.append(
                        "<td height=\"15px\" style=\"border-top: 0px;\">"
                                + "<input type=\"radio\" name=\"choice\" value=\"" + position + "\"></td>"
                );
            } else {
                result.append("<td style=\"border-top: 0px\" />");
            }
        }
        result.append("</tr>");
        return result.toString();
    }

    public static String drawChoiceButton() {
        return "<input type=\"submit\" value=\"선택\" style=\"width: 150px; font-size: 25px;\"/>"
                + "<br /><br /><br />";
    }

    public static String drawConfirmOrCancelButtons() {
        return "<input type=\"submit\" value=\"확인\" style=\"width: 150px; font-size: 25px;\"/>"
                + "<a href=\"/cancel\"><input type=\"button\" value=\"취소\" style=\"width: 150px; font-size: 25px;\"/></a>"
                + "<br /><br />";
    }

    public static String drawRestartButton() {
        return "<a href=\"/restart\">" +
                "<input type=\"button\" value=\"재시작\" style=\"width: 150px; font-size: 25px;\"/>" +
                "</a>";
    }
}