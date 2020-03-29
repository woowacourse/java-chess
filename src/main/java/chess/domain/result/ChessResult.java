package chess.domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.piece.GamePiece;
import chess.domain.player.Player;

public class ChessResult {

    private Map<Player, Score> result;

    public ChessResult(Map<Player, Score> result) {
        this.result = result;
    }

    public static ChessResult from(Map<Position, GamePiece> board) {
        return new ChessResult(calculateScore(board));
    }

    private static Map<Player, Score> calculateScore(Map<Position, GamePiece> board) {
        Map<Player, Score> scores = new HashMap<>();
        List<GamePiece> gamePieces = new ArrayList<>(board.values());

        Map<GamePiece, Integer> gameWhitePiecesCount = getGamePieceCount(gamePieces, Player.WHITE);
        Map<GamePiece, Integer> gameBlackPiecesCount = getGamePieceCount(gamePieces, Player.BLACK);

        int sameFileWhitePawnCount = getSameColumnPawnCount(board, Player.WHITE);
        int sameFileBlackPawnCount = getSameColumnPawnCount(board, Player.BLACK);

        scores.put(Player.WHITE, Score.of(gameWhitePiecesCount, sameFileWhitePawnCount));
        scores.put(Player.BLACK, Score.of(gameBlackPiecesCount, sameFileBlackPawnCount));

        return scores;
    }

    private static Map<GamePiece, Integer> getGamePieceCount(List<GamePiece> gamePieces, Player player) {
        return gamePieces.stream()
                .distinct()
                .filter(gamePiece -> gamePiece != GamePiece.EMPTY)
                .filter(gamePiece -> gamePiece.is(player))
                .collect(Collectors.toMap(gamePiece -> gamePiece, gamePiece -> Collections.frequency(gamePieces, gamePiece)));
    }

    private static int getSameColumnPawnCount(Map<Position, GamePiece> board, Player player) {
        Map<Integer, Integer> sameColumnPawnCount = new HashMap<>();
        for (int i = 0; i < Column.values().length; i++) {
            sameColumnPawnCount.put(i, 0);
        }
        List<GamePiece> gamePieces = new ArrayList<>(board.values());

        int rowLength = Row.values().length;
        for (int i = 0; i < gamePieces.size(); i++) {
            GamePiece gamePiece = gamePieces.get(i);
            if (gamePiece.isPawn() && gamePiece.is(player)) {
                sameColumnPawnCount.computeIfPresent(i % rowLength, (key, value) -> value + 1);
            }
        }

        return sameColumnPawnCount.values()
                .stream()
                .filter(count -> count >= 2)
                .reduce(0, Integer::sum);
    }

    public Map<Player, Score> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
