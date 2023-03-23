package domain.game;

import domain.piece.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Referee {

    private static final double DUPLICATE_PAWN_SCORE = 0.5d;

    public Map<Side, Double> calculateScore(Map<Position, Piece> chessBoard) {
        Map<Side, Double> scores = new EnumMap<>(Side.class);

        List<Piece> whitePieces = collectPiecesBySide(Side.WHITE, chessBoard);
        List<Piece> blackPieces = collectPiecesBySide(Side.BLACK, chessBoard);
        
        scores.put(Side.WHITE, calculateScoreOneSidePieces(whitePieces) - calculateMinusScoreBySameRankPawn(Side.WHITE, chessBoard));
        scores.put(Side.BLACK, calculateScoreOneSidePieces(blackPieces) - calculateMinusScoreBySameRankPawn(Side.BLACK, chessBoard));

        return scores;

    }

    private List<Piece> collectPiecesBySide(Side side, Map<Position, Piece> chessBoard) {
        return chessBoard.values().stream()
                .filter(piece -> piece.isSameSide(side))
                .collect(Collectors.toList());
    }

    private double calculateScoreOneSidePieces(List<Piece> pieces) {
        return pieces.stream()
                .mapToDouble(Piece::score)
                .sum();
    }

    private double calculateMinusScoreBySameRankPawn(Side side, Map<Position, Piece> chessBoard) {
        Map<File, Long> duplicatePawns = Arrays.stream(File.values())
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(file.getText(), rank.getText())))
                .filter(position -> chessBoard.get(position).isSameSide(side) && chessBoard.get(position).isSameType(PieceType.PAWN))
                .collect(Collectors.groupingBy(Position::getFile, Collectors.counting()));

        return duplicatePawns.values().stream()
                .filter(duplicatePawnCount -> duplicatePawnCount > 1)
                .mapToDouble(duplicatePawnCount -> duplicatePawnCount * DUPLICATE_PAWN_SCORE)
                .sum();
    }

}
