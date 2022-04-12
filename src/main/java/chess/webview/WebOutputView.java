package chess.webview;

import chess.domain.Team;
import java.util.HashMap;
import java.util.Map;

public class WebOutputView {

    public static Map<String, Object> makeStatusModel(Map<Team, Double> teamScore, Team winner) {
        Map<String, Object> model = new HashMap<>();
        model.put("whiteScore", teamScore.get(Team.WHITE));
        model.put("blackScore", teamScore.get(Team.BLACK));
        model.put("winner", Team.of(winner));
        return model;
    }
}
