package chess.domain.piece;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.File.C;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.File.G;
import static chess.domain.board.File.H;
import static chess.domain.board.Rank.isPawnRank;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.vo.TeamColor;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public abstract class Piece {

    private static final Map<File, BiFunction<TeamColor, Position, Piece>> initialPieceCreationStrategy =
            Map.of(A, Rook::new, B, Knight::new, C, Bishop::new, D, Queen::new,
                    E, King::new, F, Bishop::new, G, Knight::new, H, Rook::new);

    final TeamColor teamColor;
    final Position position;

    Piece(final TeamColor teamColor, final Position position) {
        this.teamColor = teamColor;
        this.position = position;
    }

    public static Piece create(final File file, final Rank rank) {
        TeamColor teamColor = TeamColor.findByRank(rank);
        if (isPawnRank(rank)) {
            return new Pawn(teamColor, Position.of(file, rank));
        }
        return initialPieceCreationStrategy.get(file)
                .apply(teamColor, Position.of(file, rank));
    }

    public final boolean hasPosition(final Position position) {
        return this.position == position;
    }

    public final boolean isBlackTeam() {
        return teamColor.isBlack();
    }

    public Piece move(final List<Piece> pieces, final Position targetPosition) {
        return null;
    }

    public final boolean isSameTeam(final Piece anotherPiece) {
        return this.teamColor == anotherPiece.teamColor;
    }
}
