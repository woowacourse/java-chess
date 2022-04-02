package chess.model;

import static chess.domain.board.piece.Color.BLACK;
import static chess.domain.board.piece.Color.WHITE;
import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.PAWN;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import java.util.Map;

public class GameResult {

    private static final int SAME_FILE_PAWN_MIN_COUNT = 2;
    private static final double SAME_FILE_PAWN_DISADVANTAGE = 0.5;

    private final Color winnerColor;
    private final GameScore scoreResult;

    public GameResult(Map<Position, Piece> board) {
        this.winnerColor = chooseWinner(board);
        this.scoreResult = new GameScore(calculateScore(board, WHITE), calculateScore(board, BLACK));
    }

    private Color chooseWinner(Map<Position, Piece> board) {
        Piece king = getOnlyKingLeft(board);
        if (king.hasColorOf(WHITE)) {
            return WHITE;
        }
        return BLACK;
    }

    private Piece getOnlyKingLeft(Map<Position, Piece> board) {
        return board.values()
                .stream()
                .filter(piece -> piece.hasTypeOf(KING))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("킹이 하나도 없는 게임은 존재할 수 없습니다."));
    }

    public double calculateScore(Map<Position, Piece> board, Color color) {
        double defaultScore = calculateDefaultScore(board, color);
        int sameFilePawnCount = countAllSameFilePawns(board, color);

        return defaultScore - (sameFilePawnCount * SAME_FILE_PAWN_DISADVANTAGE);
    }

    private double calculateDefaultScore(Map<Position, Piece> board, Color color) {
        return board.values()
                .stream()
                .filter(piece -> piece.hasColorOf(color))
                .mapToDouble(Piece::toScore)
                .sum();
    }

    private int countAllSameFilePawns(Map<Position, Piece> board, Color color) {
        return  File.allFilesAscending()
                .stream()
                .mapToInt(file -> countPawnsOfSameFile(board, file, color))
                .filter(sameFilePawnCount -> sameFilePawnCount >= SAME_FILE_PAWN_MIN_COUNT)
                .sum();
    }

    private int countPawnsOfSameFile(Map<Position, Piece> board, File file, Color color) {
        return (int) Rank.allRanksDescending()
                .stream()
                .map(rank -> Position.of(file, rank))
                .filter(board::containsKey)
                .map(board::get)
                .filter(piece -> piece.hasColorOf(color))
                .filter(piece -> piece.hasTypeOf(PAWN))
                .count();
    }

    public Color getWinnerColor() {
        return winnerColor;
    }

    public double getWhiteScore() {
        return scoreResult.whiteScore();
    }

    public double getBlackScore() {
        return scoreResult.blackScore();
    }
}
