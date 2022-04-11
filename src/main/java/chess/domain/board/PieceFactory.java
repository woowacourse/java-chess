package chess.domain.board;

import static chess.domain.board.position.File.A;
import static chess.domain.board.position.File.B;
import static chess.domain.board.position.File.C;
import static chess.domain.board.position.File.D;
import static chess.domain.board.position.File.F;
import static chess.domain.board.position.File.G;
import static chess.domain.board.position.File.H;
import static chess.domain.board.position.Rank.EIGHT;
import static chess.domain.board.position.Rank.ONE;
import static chess.domain.board.position.Rank.SEVEN;
import static chess.domain.board.position.Rank.TWO;

import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.piece.UnpromotablePiece;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceFactory {

    public Map<Position, Piece> generateInitialPieces() {
        final Map<Position, Piece> initialPieces = new HashMap<>(64);
        final List<Rank> initialPieceRanks = Arrays.asList(ONE, TWO, SEVEN, EIGHT);
        for (File file : File.values()) {
            create(initialPieces, initialPieceRanks, file);
        }
        return initialPieces;
    }

    private void create(final Map<Position, Piece> initialPieces, final List<Rank> initialPieceRanks,
                        final File file) {
        for (Rank rank : initialPieceRanks) {
            initialPieces.put(Position.of(file, rank), generate(file, rank));
        }
    }

    private Piece generate(final File file, final Rank rank) {
        final Team team = Team.findByRank(rank);
        if (rank.isPawnRank()) {
            return new Pawn(team);
        }
        return generateNotPawn(file, team);
    }

    private UnpromotablePiece generateNotPawn(final File file, final Team team) {
        if (file == A || file == H) {
            return new Rook(team);
        }
        if (file == B || file == G) {
            return new Knight(team);
        }
        if (file == C || file == F) {
            return new Bishop(team);
        }
        if (file == D) {
            return new Queen(team);
        }
        return new King(team);
    }
}
