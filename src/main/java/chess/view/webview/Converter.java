package chess.view.webview;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Converter {

    private Converter() {
    }

    public static Map<String, Object> convertToWebViewPiece(final Map<Position, Piece> pieces) {
        return pieces.keySet()
                .stream()
                .collect(Collectors.toMap(Position::toString, pieces::get));
    }
}
