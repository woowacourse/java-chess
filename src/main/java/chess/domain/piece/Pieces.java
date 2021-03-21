package chess.domain.piece;

import static chess.domain.piece.type.PieceType.PAWN;

import chess.domain.board.File;
import chess.domain.player.score.Score;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pieces {
    private final List<Piece> piecesExceptPawn = new LinkedList<>();
    private final Map<File, List<Pawn>> pawns = new HashMap<>();

    public Pieces() {
        for (File file : File.values()) {
            pawns.put(file, new LinkedList<>());
        }
    }

    public void add(Piece piece, Position position) {
        if (piece.type() == PAWN) {
            addPawn((Pawn) piece, position.file());
            return;
        }
        piecesExceptPawn.add(piece);
    }

    private void addPawn(Pawn pawn, File file) {
        List<Pawn> pawnsByFile = pawns.get(file);
        pawnsByFile.add(pawn);
        pawns.put(file, pawnsByFile);
    }

    public void remove(Piece piece, Position position) {
        if (piece.type() == PAWN) {
            List<Pawn> pawnsByFile = pawns.get(position.file());
            pawnsByFile.remove(pawnsByFile.size() - 1);
            return;
        }
        piecesExceptPawn.remove(piece);
    }

    public Score score() {
        return new Score(scoreExceptPawns() + scoreOfPawns());
    }

    private double scoreExceptPawns() {
        return piecesExceptPawn.stream()
            .mapToDouble(Piece::score)
            .sum();
    }

    private double scoreOfPawns() {
        double scoreSumOfPawns = 0;
        for (List<Pawn> pawnsByFile : pawns.values()) {
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
            return Pawn.defaultScore();
        }
        return Pawn.halfScore();
    }
}
