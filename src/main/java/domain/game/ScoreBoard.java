package domain.game;

import domain.piece.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreBoard {
    private static final double DUPLICATE_PAWN_SCORE = 0.5d;

    private final Map<Position, Piece> chessBoard;
    private final GameState gameState;

    public ScoreBoard(Map<Position, Piece> chessBoard, GameState gameState) {
        this.chessBoard = chessBoard;
        this.gameState = gameState;
    }

    public Map<Side, Double> calculateScore() {
        Map<Side, Double> scores = new EnumMap<>(Side.class);

        List<Piece> whitePieces = collectPiecesBySide(Side.WHITE);
        List<Piece> blackPieces = collectPiecesBySide(Side.BLACK);

        scores.put(Side.WHITE, calculateScoreOneSidePieces(whitePieces) - calculateMinusScoreBySameRankPawn(Side.WHITE));
        scores.put(Side.BLACK, calculateScoreOneSidePieces(blackPieces) - calculateMinusScoreBySameRankPawn(Side.BLACK));

        return scores;
    }

    public Side calculateWinner(Map<Side, Double> scores) {
        if (gameState == GameState.KING_DEAD) {
            return calculateWinnerWhenKingDie();
        }
        return calculateWinnerWhenKingAllAlive(scores);
    }

    private List<Piece> collectPiecesBySide(Side side) {
        return chessBoard.values().stream()
                .filter(piece -> piece.isSameSide(side))
                .collect(Collectors.toList());
    }

    private double calculateScoreOneSidePieces(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::score)
                .sum();
    }

    private double calculateMinusScoreBySameRankPawn(Side side) {
        Map<File, Long> duplicatePawns = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(file.getText(), rank.getText())))
                .filter(position -> chessBoard.get(position).isSameSide(side) && chessBoard.get(position).isSameType(PieceType.PAWN))
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));

        return duplicatePawns.values().stream()
                .filter(duplicatePawnCount -> duplicatePawnCount > 1)
                .mapToDouble(duplicatePawnCount -> duplicatePawnCount * DUPLICATE_PAWN_SCORE)
                .sum();
    }

    private Side calculateWinnerWhenKingDie() {
        List<Piece> pieces = collectPiecesBySide(Side.WHITE);

        return pieces.stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .map(Piece::getSide)
                .findAny()
                .orElse(Side.WHITE.nextSide());
    }

    private Side calculateWinnerWhenKingAllAlive(Map<Side, Double> scores) {
        double whiteScore = scores.get(Side.WHITE);
        double blackScore = scores.get(Side.BLACK);

        if (whiteScore > blackScore) {
            return Side.WHITE;
        }
        if (whiteScore == blackScore) {
            return Side.NEUTRAL;
        }
        return Side.BLACK;
    }
}
