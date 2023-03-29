package chess.domain.pieces;

import static chess.domain.Team.BLACK;

import chess.domain.Team;
import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.BlackPawn;
import chess.domain.pieces.pawn.Pawn;
import chess.domain.pieces.pawn.WhitePawn;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import java.util.Arrays;
import java.util.List;

public enum PieceType {

    PAWN(Pawn.class, new Score(1)),
    ROOK(Rook.class, new Score(5)),
    KNIGHT(Knight.class, new Score(2.5)),
    BISHOP(Bishop.class, new Score(3)),
    QUEEN(Queen.class, new Score(9)),
    KING(King.class, new Score(0)),
    EMPTY(EmptyPiece.class, new Score(0));

    private static final int CRITERIA_OF_CALCULATE_PAWN_SCORE = 2;
    private static final double SCORE_OF_OVERLAP_PAWNS = 0.5;

    private final Class<? extends Piece> pieceType;
    private final Score score;

    PieceType(final Class<? extends Piece> pieceType, final Score score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    public static PieceType from(final Piece piece) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.pieceType.isAssignableFrom(piece.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }

    public static Score calculateScore(final List<Piece> pieces) {
        Score totalScore = totalScore(pieces);
        long pawnCount = getPawnCount(pieces);

        if (pawnCount >= CRITERIA_OF_CALCULATE_PAWN_SCORE) {
            return totalScore.subtract(SCORE_OF_OVERLAP_PAWNS * pawnCount);
        }
        return totalScore;
    }

    private static Score totalScore(final List<Piece> pieces) {
        return pieces.stream()
                .map(PieceType::from)
                .map(PieceType::getScore)
                .reduce(Score::add)
                .orElse(Score.ZERO);
    }

    private static long getPawnCount(final List<Piece> pieces) {
        return pieces.stream()
                .filter(piece -> from(piece).equals(PAWN))
                .count();
    }

    public Piece generatePiece(final Team team) {
        if (this.equals(PAWN)) {
            if (team.isAlly(BLACK)) {
                return new BlackPawn();
            }
            return new WhitePawn();
        }
        if (this.equals(KNIGHT)) {
            return new Knight(team);
        }
        if (this.equals(ROOK)) {
            return new Rook(team);
        }
        if (this.equals(BISHOP)) {
            return new Bishop(team);
        }
        if (this.equals(QUEEN)) {
            return new Queen(team);
        }
        if (this.equals(KING)) {
            return new King(team);
        }
        return new EmptyPiece();
    }

    public Score getScore() {
        return this.score;
    }
}
