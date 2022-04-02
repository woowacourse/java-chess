package chess.domain.board;

import static chess.domain.board.position.File.A;
import static chess.domain.board.position.File.B;
import static chess.domain.board.position.File.C;
import static chess.domain.board.position.File.D;

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
        final TeamColor teamColor = TeamColor.findByRank(rank);
        final Position position = Position.of(file, rank);

        if (file == A) {
            return new Rook(teamColor, position);
        }
        if (file == B) {
            return new Knight(teamColor, position);
        }
        if (file == C) {
            return new Bishop(teamColor, position);
        }
        if (file == D) {
            return new Queen(teamColor, position);
        }
        return new King(teamColor, position);
    }
}
