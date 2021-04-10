package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
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
        return board.coordinates();
    }

    @Override
    public List<Position> infoAsList() {
        throw new IllegalArgumentException("게임이 종료된 상태입니다.");
    }

    @Override
    public Score infoAsScore(final Color color) {
        throw new IllegalArgumentException("게임이 종료된 상태입니다.");
    }

    private String renderEnding(Board board) {
        if (board.kingCount() == 2) {
            return "승자가 결정되지 않았습니다.";
        }
        final Piece winnerKing = board.remainingKing();
        if (winnerKing.isColor(Color.WHITE)) {
            return Color.WHITE.getName() + "이 승리했습니다.";
        }
        return Color.BLACK.getName() + "이 승리했습니다.";
    }
}
