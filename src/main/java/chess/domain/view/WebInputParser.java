package chess.domain.view;

import chess.domain.position.Position;
import chess.domain.position.Positions;

public class WebInputParser {
    public static Position getSourcePosition(String sourceInput) {
        return Positions.matchWith((int) sourceInput.charAt(0) - '0', (int) sourceInput.charAt(1) - '0');
    }

    public static Position getTargetPosition(String targetInput) {
        return Positions.matchWith((int) targetInput.charAt(0) - '0', (int) targetInput.charAt(1) - '0');
    }
}
