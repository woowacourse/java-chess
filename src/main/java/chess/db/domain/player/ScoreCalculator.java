package chess.db.domain.player;

import static chess.beforedb.domain.piece.type.PieceType.PAWN;

import chess.beforedb.domain.position.type.File;
import chess.db.domain.board.PiecePositionFromDB;
import chess.db.domain.piece.PawnEntity;
import chess.db.domain.piece.PieceEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final int MIN_COUNT_FOR_PAWN_HALF_SCORE = 2;

    public double getCalculatedScore(List<PiecePositionFromDB> piecesPositions) {
        double totalScore = 0;
        for (File file : File.values()) {
            totalScore += getScoreOfFile(piecesPositions, file);
        }
        return totalScore;
    }

    private double getScoreOfFile(List<PiecePositionFromDB> piecesPositions, File file) {
        List<PieceEntity> allPiecesInFile = getAllPiecesInFile(piecesPositions, file);
        List<PieceEntity> pawnsInFile = getAllPawnsInFile(allPiecesInFile);
        List<PieceEntity> piecesInFileExceptPawn
            = getPiecesInFileExceptPawn(allPiecesInFile, pawnsInFile);

        double scoreOfPawnsInFile = getScoreOfPawnsInFile(pawnsInFile);
        double scoreOfPiecesExceptPawnInFile
            = getScoreOfPiecesExceptPawnInFile(piecesInFileExceptPawn);

        return scoreOfPawnsInFile + scoreOfPiecesExceptPawnInFile;
    }

    private List<PieceEntity> getAllPiecesInFile(List<PiecePositionFromDB> piecesPositions,
        File file) {

        return piecesPositions.stream()
            .filter(piecePosition -> piecePosition.getFile() == file)
            .map(piecePosition ->
                PieceEntity.of(piecePosition.getPieceType(), piecePosition.getTeamColor()))
            .collect(Collectors.toList());
    }

    private List<PieceEntity> getAllPawnsInFile(List<PieceEntity> allPiecesInFile) {
        return allPiecesInFile.stream()
            .filter(pieceInFile -> pieceInFile.getPieceType() == PAWN)
            .collect(Collectors.toList());
    }

    private List<PieceEntity> getPiecesInFileExceptPawn(List<PieceEntity> allPiecesInFile,
        List<PieceEntity> pawnsInFile) {

        List<PieceEntity> allPiecesInFileExceptPawn = new ArrayList<>(allPiecesInFile);
        allPiecesInFileExceptPawn.removeAll(pawnsInFile);
        return allPiecesInFileExceptPawn;
    }

    private double getScoreOfPawnsInFile(List<PieceEntity> pawnsInFile) {
        int pawnsCountInFile = pawnsInFile.size();
        if (pawnsCountInFile >= MIN_COUNT_FOR_PAWN_HALF_SCORE) {
            return PawnEntity.halfScore() * (double) pawnsCountInFile;
        }
        return PawnEntity.defaultScore() * (double) pawnsCountInFile;
    }

    private double getScoreOfPiecesExceptPawnInFile(List<PieceEntity> piecesInFileExceptPawn) {
        double scoreSum = 0;
        for (PieceEntity pieceEntity : piecesInFileExceptPawn) {
            scoreSum += pieceEntity.getScore();
        }
        return scoreSum;
    }
}
