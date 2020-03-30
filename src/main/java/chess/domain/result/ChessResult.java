package chess.domain.result;

import chess.domain.board.Line;
import chess.domain.board.Position;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;

import java.util.*;
import java.util.stream.Collectors;

public class ChessResult {

    private static final int MINIMUM_PAWN_COUNT = 2;
    private Map<PlayerColor, Score> result;

    public ChessResult(Map<PlayerColor, Score> result) {
        this.result = result;
    }

    public static ChessResult from(Map<Position, GamePiece> board) {
        return new ChessResult(calculateScore(board));
    }

    private static Map<PlayerColor, Score> calculateScore(Map<Position, GamePiece> board) {
        Map<PlayerColor, Score> scores = new HashMap<>();
        List<GamePiece> gamePieces = new ArrayList<>(board.values());

        Map<GamePiece, Integer> gameWhitePiecesCount = getGamePieceCount(gamePieces, PlayerColor.WHITE);
        Map<GamePiece, Integer> gameBlackPiecesCount = getGamePieceCount(gamePieces, PlayerColor.BLACK);

        int sameColumnWhitePawnCount = getSameColumnPawnCount(board, PlayerColor.WHITE);
        int sameColumnBlackPawnCount = getSameColumnPawnCount(board, PlayerColor.BLACK);

        scores.put(PlayerColor.WHITE, Score.of(gameWhitePiecesCount, sameColumnWhitePawnCount));
        scores.put(PlayerColor.BLACK, Score.of(gameBlackPiecesCount, sameColumnBlackPawnCount));

        return scores;
    }

    private static Map<GamePiece, Integer> getGamePieceCount(List<GamePiece> gamePieces, PlayerColor playerColor) {
        return gamePieces.stream()
                .distinct()
                .filter(gamePiece -> gamePiece != EmptyPiece.getInstance())
                .filter(gamePiece -> gamePiece.is(playerColor))
                .collect(Collectors.toMap(gamePiece -> gamePiece, gamePiece -> Collections.frequency(gamePieces, gamePiece)));
    }

    private static int getSameColumnPawnCount(Map<Position, GamePiece> board, PlayerColor playerColor) {
        return Line.listsByColumn(board).stream()
                .map(column -> column.countPawnOf(playerColor))
                .filter(count -> count >= MINIMUM_PAWN_COUNT)
                .reduce(0, Integer::sum);
    }

    public Map<PlayerColor, Score> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
