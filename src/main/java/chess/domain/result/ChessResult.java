package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessResult {

    private static final int MINIMUM_PAWN_COUNT = 2;
    private Map<PlayerColor, Score> result;

    public ChessResult(Map<PlayerColor, Score> result) {
        this.result = result;
    }

    public static ChessResult from(Board board) {
        return new ChessResult(calculateScore(board));
    }

    private static Map<PlayerColor, Score> calculateScore(Board board) {
        Map<PlayerColor, Score> scores = new HashMap<>();

        Map<GamePiece, Integer> gameWhitePiecesCount = board.countEachGamePiece(PlayerColor.WHITE);
        Map<GamePiece, Integer> gameBlackPiecesCount = board.countEachGamePiece(PlayerColor.BLACK);

        int sameColumnWhitePawnCount = getSameColumnPawnCount(board, PlayerColor.WHITE);
        int sameColumnBlackPawnCount = getSameColumnPawnCount(board, PlayerColor.BLACK);

        scores.put(PlayerColor.WHITE, Score.of(gameWhitePiecesCount, sameColumnWhitePawnCount));
        scores.put(PlayerColor.BLACK, Score.of(gameBlackPiecesCount, sameColumnBlackPawnCount));

        return scores;
    }

    private static int getSameColumnPawnCount(Board board, PlayerColor playerColor) {
        return board.getColumns()
                .stream()
                .map(column -> column.countPawnOf(playerColor))
                .filter(count -> count >= MINIMUM_PAWN_COUNT)
                .reduce(0, Integer::sum);
    }

    public Map<PlayerColor, Score> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
