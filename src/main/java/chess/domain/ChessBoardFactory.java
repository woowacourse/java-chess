package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.NoPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardFactory {

    public static ChessBoard create() {
        List<Square> squares = new ArrayList<>();

        for (File file : File.values()) {
            squares.addAll(Arrays.stream(Rank.values())
                    .map(rank -> new Square(Position.of(rank, file), createPiece(rank, file)))
                    .collect(Collectors.toUnmodifiableList()));
        }

        return new ChessBoard(squares);
    }

    private static Piece createPiece(Rank rank, File file) {
        Team team = Team.initialOf(file);
        if (file == File.TWO || file == File.SEVEN) {
            return new Pawn(team);
        }
        if (file == File.ONE || file == File.EIGHT) {
            return createHeavyPiece(rank, team);
        }
        return NoPiece.getInstance();
    }

    private static Piece createHeavyPiece(final Rank rank, final Team team) {
        if (rank == Rank.A || rank == Rank.H) {
            return new Rook(team);
        }
        if (rank == Rank.B || rank == Rank.G) {
            return new Knight(team);
        }
        if (rank == Rank.C || rank == Rank.F) {
            return new Bishop(team);
        }
        if (rank == Rank.D) {
            return new Queen(team);
        }
        return new King(team);
    }
}
