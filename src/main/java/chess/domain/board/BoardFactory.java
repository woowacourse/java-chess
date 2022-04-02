package chess.domain.board;

import static chess.domain.board.position.File.A;
import static chess.domain.board.position.File.B;
import static chess.domain.board.position.File.C;
import static chess.domain.board.position.File.D;
import static chess.domain.board.position.File.E;
import static chess.domain.board.position.File.F;
import static chess.domain.board.position.File.G;
import static chess.domain.board.position.File.H;

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
import chess.domain.piece.TeamColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class BoardFactory {

    public List<Piece> generateInitialPieces() {
        final List<Piece> initialPieces = new ArrayList<>();
        initialPieces.addAll(createNotPawn(Rank.ONE));
        initialPieces.addAll(createNotPawn(Rank.EIGHT));
        initialPieces.addAll(createPawn(Rank.TWO));
        initialPieces.addAll(createPawn(Rank.SEVEN));
        return initialPieces;
    }

    private List<Piece> createNotPawn(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> createInitial(file, rank))
                .collect(Collectors.toList());
    }

    private List<Piece> createPawn(final Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .map(position -> new Pawn(TeamColor.findByRank(rank), position))
                .collect(Collectors.toList());
    }

    private Piece createInitial(final File file, final Rank rank) {
        final Map<File, BiFunction<TeamColor, Position, Piece>> initialPieceCreationStrategy =
                Map.of(A, Rook::new, B, Knight::new, C, Bishop::new, D, Queen::new,
                        E, King::new, F, Bishop::new, G, Knight::new, H, Rook::new);

        final TeamColor teamColor = TeamColor.findByRank(rank);
        return initialPieceCreationStrategy.get(file)
                .apply(teamColor, Position.of(file, rank));
    }
}
