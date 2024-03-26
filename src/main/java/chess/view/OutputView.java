package chess.view;

import chess.domain.piece.Piece;
import java.util.List;

public class OutputView {

    private final MessageResolver messageResolver;

    public OutputView(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    public void printBoard(List<Piece> pieces) {
        System.out.println(messageResolver.resolveBoardMessage(pieces));
    }
}
