package chess.db.domain.piece;

import static chess.beforedb.domain.piece.type.PieceType.PAWN;

import chess.beforedb.domain.position.Position;
import chess.beforedb.domain.position.type.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PieceEntities {
    private final List<PieceEntity> piecesExceptPawn = new LinkedList<>();
    private final Map<File, List<PawnEntity>> pawns = new HashMap<>();

    public PieceEntities() {
        for (File file : File.values()) {
            pawns.put(file, new LinkedList<>());
        }
    }

    public void add(PieceEntity piece, Position position) {
        if (piece.getPieceType() == PAWN) {
            addPawn((PawnEntity) piece, position.file());
            return;
        }
        piecesExceptPawn.add(piece);
    }

    private void addPawn(PawnEntity pawn, File file) {
        List<PawnEntity> pawnsByFile = pawns.get(file);
        pawnsByFile.add(pawn);
        pawns.put(file, pawnsByFile);
    }

    public void remove(PieceEntity piece, Position position) {
        if (piece.getPieceType() == PAWN) {
            List<PawnEntity> pawnsByFile = pawns.get(position.file());
            pawnsByFile.remove(pawnsByFile.size() - 1);
            return;
        }
        piecesExceptPawn.remove(piece);
    }

    public double getScore() {
        return scoreExceptPawns() + scoreOfPawns();
    }

    private double scoreExceptPawns() {
        return piecesExceptPawn.stream()
            .mapToDouble(PieceEntity::getScore)
            .sum();
    }

    private double scoreOfPawns() {
        double scoreSumOfPawns = 0;
        for (List<PawnEntity> pawnsByFile : pawns.values()) {
            int pawnsCountOfFile = pawnsByFile.size();
            double pawnScore = getPawnScore(pawnsCountOfFile);
            scoreSumOfPawns += (pawnsCountOfFile * pawnScore);
        }
        return scoreSumOfPawns;
    }

    private double getPawnScore(int pawnsCountOfFile) {
        if (pawnsCountOfFile == 0) {
            return 0;
        }
        if (pawnsCountOfFile == 1) {
            return PawnEntity.defaultScore();
        }
        return PawnEntity.halfScore();
    }
}
