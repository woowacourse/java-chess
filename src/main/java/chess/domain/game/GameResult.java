package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;

public class GameResult {

    private static final long INITIAL_KING_COUNT = 2;

    private final Map<Position, Piece> pieces;

    public GameResult(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static GameResult from(Map<Position, Piece> pieces) {
        return new GameResult(pieces);
    }

    public Score getScore(Color color) {
        return Score.calculate(pieces, color);
    }

    public Color getWinner() {
        if (isEndOfGame()) {
            return getWinnerOfEnd();
        }
        return Score.judgeWinner(getScore(Color.BLACK), getScore(Color.WHITE));
    }

    private boolean isEndOfGame() {
        long kingCount = pieces.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .count();
        return kingCount != INITIAL_KING_COUNT;
    }

    private Color getWinnerOfEnd() {
        Piece king = pieces.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("살아있는 왕이 없습니다."));
        return king.getColor();
    }
}
