package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.player.score.Score;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pieces {
    private final List<Piece> piecesExceptPawn = new ArrayList<>();
    private final Map<File, Pawn> pawns = new HashMap<>();

    public void add(Piece piece, Position position) {
        if (piece instanceof Pawn) {
            pawns.put(position.file(), (Pawn) piece);
            return;
        }
        piecesExceptPawn.add(piece);
    }

    public void remove(Piece piece, Position position) {
        if (piece instanceof Pawn) {
            pawns.remove(position.file());
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
        for (File file : File.values()) {
            int pawnsCountOfFile = countPawnOfFile(file);
            scoreSumOfPawns += getPawnScore(pawnsCountOfFile);
        }
        return scoreSumOfPawns;
    }

    private int countPawnOfFile(File file) {
        return (int) pawns.keySet().stream()
            .filter(fileOfPawn -> fileOfPawn == file)
            .count();
    }

    private double getPawnScore(int pawnsCountOfFile) {
        if (pawnsCountOfFile == 0) {
            return 0;
        }
        if (pawnsCountOfFile == 1) {
            return Pawn.defaultScore();
        }
        return pawnsCountOfFile * Pawn.halfScore();
    }
}
