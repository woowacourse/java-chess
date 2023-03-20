package chess.view.output;

import chess.domain.piece.Camp;
import chess.domain.piece.King;
import org.junit.jupiter.api.Test;

class BlackPieceSymbolViewTest {
    @Test
    void name() {
        String viewSymbolBy = BlackPieceSymbolView.getViewSymbolBy(new King(Camp.WHITE));
        System.out.println(viewSymbolBy);
    }
}
