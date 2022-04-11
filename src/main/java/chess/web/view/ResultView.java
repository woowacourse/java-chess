package chess.web.view;

import chess.dto.GameResultDto;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResultView {

    private final Map<String, Object> resultView;

    public ResultView(Map<String, Object> statusView) {
        this.resultView = new LinkedHashMap<>();
        resultView.put("result", statusView);
    }

    public static ResultView of(GameResultDto gameResultDto) {
        Map<String, Object> statusView = new HashMap<>();

        statusView.put("winner", gameResultDto.getWinner());
        statusView.put("whiteScore", gameResultDto.getWhiteScore());
        statusView.put("blackScore", gameResultDto.getBlackScore());

        return new ResultView(statusView);
    }

    public Map<String, Object> getResultView() {
        return resultView;
    }

}
