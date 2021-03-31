package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class EndResult implements Result {

    private final Board board;

    public EndResult(final Board board) {
        this.board = board;
    }

    @Override
    public String infoAsString() {
        return renderEnding(board);
    }

    @Override
    public Map<Position, Piece> infoAsMap() {
        return null;
    }

    private String renderEnding(Board board) {
        final Piece winnerKing = board.remainingKing();
        if (winnerKing.isColor(Color.WHITE)) {
            return Color.WHITE.getName() + "이 승리했습니다.";
        }
        return Color.BLACK.getName() + "이 승리했습니다.";
    }
}
