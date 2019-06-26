package chess.domain.view;

import chess.domain.position.Position;
import chess.domain.position.PositionManager;

public class WebInputParser {
    public static Position getSourcePosition(String sourceInput) {
        return PositionManager.getMatchPosition((int) sourceInput.charAt(0) - 48, (int) sourceInput.charAt(1) - 48);
    }

    public static Position getTargetPosition(String targetInput) {
        return PositionManager.getMatchPosition((int) targetInput.charAt(0) - 48, (int) targetInput.charAt(1) - 48);
    }
}
