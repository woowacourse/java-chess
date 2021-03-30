package chess.domain.player.score;

import static chess.domain.piece.type.PieceType.PAWN;

import chess.dao.entity.PiecePositionEntity;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.type.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final int MIN_COUNT_FOR_PAWN_HALF_SCORE = 2;

    public double getCalculatedScore(List<PiecePositionEntity> piecesPositions) {
        double totalScore = 0;
        for (File file : File.values()) {
            totalScore += getScoreOfFile(piecesPositions, file);
        }
        return totalScore;
    }

    private double getScoreOfFile(List<PiecePositionEntity> piecesPositions, File file) {
        List<Piece> allPiecesInFile = getAllPiecesInFile(piecesPositions, file);
        List<Piece> pawnsInFile = getAllPawnsInFile(allPiecesInFile);
        List<Piece> piecesInFileExceptPawn
            = getPiecesInFileExceptPawn(allPiecesInFile, pawnsInFile);

        double scoreOfPawnsInFile = getScoreOfPawnsInFile(pawnsInFile);
        double scoreOfPiecesExceptPawnInFile
            = getScoreOfPiecesExceptPawnInFile(piecesInFileExceptPawn);

        return scoreOfPawnsInFile + scoreOfPiecesExceptPawnInFile;
    }

    private List<Piece> getAllPiecesInFile(List<PiecePositionEntity> piecesPositions, File file) {
        return piecesPositions.stream()
            .filter(piecePosition -> piecePosition.getFile() == file)
            .map(piecePosition ->
                Piece.of(piecePosition.getPieceType(), piecePosition.getTeamColor()))
            .collect(Collectors.toList());
    }

    private List<Piece> getAllPawnsInFile(List<Piece> allPiecesInFile) {
        return allPiecesInFile.stream()
            .filter(pieceInFile -> pieceInFile.getPieceType() == PAWN)
            .collect(Collectors.toList());
    }

    private List<Piece> getPiecesInFileExceptPawn(List<Piece> allPiecesInFile,
        List<Piece> pawnsInFile) {

        List<Piece> allPiecesInFileExceptPawn = new ArrayList<>(allPiecesInFile);
        allPiecesInFileExceptPawn.removeAll(pawnsInFile);
        return allPiecesInFileExceptPawn;
    }

    private double getScoreOfPawnsInFile(List<Piece> pawnsInFile) {
        int pawnsCountInFile = pawnsInFile.size();
        if (pawnsCountInFile >= MIN_COUNT_FOR_PAWN_HALF_SCORE) {
            return Pawn.halfScore() * (double) pawnsCountInFile;
        }
        return Pawn.defaultScore() * (double) pawnsCountInFile;
    }

    private double getScoreOfPiecesExceptPawnInFile(List<Piece> piecesInFileExceptPawn) {
        double scoreSum = 0;
        for (Piece piece : piecesInFileExceptPawn) {
            scoreSum += piece.getScore();
        }
        return scoreSum;
    }
}
