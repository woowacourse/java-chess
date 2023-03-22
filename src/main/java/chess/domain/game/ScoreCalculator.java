package chess.domain.game;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private final static int MAX_POSITION = 8;
    private final static int MIN_POSITION = 1;
    private final static int PAWN_CORRECTION_NUMBER = 1;
    private final static double PAWN_CORRECTION_SCORE = 0.5d;

    private final Map<Position, Piece> board;
    private final Team team;

    public ScoreCalculator(Map<Position, Piece> board, Team team) {
        this.board = board;
        this.team = team;
    }

    public double calculateScore() {
        double score = 0;
        for (int file = MIN_POSITION; file <= MAX_POSITION; file++) {
            score += calculateScoreInSameFile(file);
        }
        return score;
    }

    private double calculateScoreInSameFile(int file) {
        List<Piece> piecesByFile = new ArrayList<>();
        for (int rank = MIN_POSITION; rank <= MAX_POSITION; rank++) {
            Position position = new Position(rank, file);
            addPiece(piecesByFile, position);
        }
        return calculatePieces(piecesByFile);
    }

    private void addPiece(List<Piece> piecesByFile, Position position) {
        if (haveNoPieceInPosition(position)) {
            return;
        }
        Piece piece = board.get(position);
        if (piece.isSameTeam(team)) {
            piecesByFile.add(board.get(position));
        }
    }

    private double calculatePieces(List<Piece> pieces) {
        double score = pieces.stream()
                .mapToDouble(piece -> PieceScore.getScore(piece.type()))
                .sum();
        double pawnCount = pieces.stream()
                .filter(piece -> piece.type() == PieceType.PAWN)
                .count();
        if (pawnCount > PAWN_CORRECTION_NUMBER) {
            return score - pawnCount * PAWN_CORRECTION_SCORE;
        }
        return score;
    }

    public boolean haveNoPieceInPosition(Position position) {
        return !board.containsKey(position);
    }
}
