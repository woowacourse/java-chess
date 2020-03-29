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
import chess.domain.piece.ChessPiece;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.GamePiece;
import chess.domain.player.PlayerColor;

public class ChessResult {

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

        int sameFileWhitePawnCount = getSameColumnPawnCount(board, PlayerColor.WHITE);
        int sameFileBlackPawnCount = getSameColumnPawnCount(board, PlayerColor.BLACK);

        scores.put(PlayerColor.WHITE, Score.of(gameWhitePiecesCount, sameFileWhitePawnCount));
        scores.put(PlayerColor.BLACK, Score.of(gameBlackPiecesCount, sameFileBlackPawnCount));

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
        Map<Integer, Integer> sameColumnPawnCount = new HashMap<>();
        for (int i = 0; i < Column.values().length; i++) {
            sameColumnPawnCount.put(i, 0);
        }
        List<GamePiece> gamePieces = new ArrayList<>(board.values());

        int rowLength = Row.values().length;
        for (int i = 0; i < gamePieces.size(); i++) {
            GamePiece gamePiece = gamePieces.get(i);
            if (ChessPiece.isPawn(gamePiece) && gamePiece.is(playerColor)) {
                sameColumnPawnCount.computeIfPresent(i % rowLength, (key, value) -> value + 1);
            }
        }

        return sameColumnPawnCount.values()
                .stream()
                .filter(count -> count >= 2)
                .reduce(0, Integer::sum);
    }

    public Map<PlayerColor, Score> getResult() {
        return Collections.unmodifiableMap(result);
    }
}
