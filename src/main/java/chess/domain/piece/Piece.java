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
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

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

    public abstract Piece move(final List<Piece> otherPieces, final Position targetPosition);

    List<Position> convertToPositions(final List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> piece.position)
                .collect(Collectors.toList());
    }

    public final boolean isSameTeam(final Piece anotherPiece) {
        return this.teamColor == anotherPiece.teamColor;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return teamColor == piece.teamColor && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor, position);
    }

    public boolean isTypeOf(final Class<? extends Piece> pieceType) {
        return this.getClass().equals(pieceType);
    }

    public final boolean isTeamOf(final TeamColor teamColor) {
        return this.teamColor == teamColor;
    }
}
