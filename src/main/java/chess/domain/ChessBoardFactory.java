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
import chess.domain.position.Rank;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessBoardFactory {

    public static ChessBoard create() {
        List<Square> squares = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            squares.addAll(Arrays.stream(File.values())
                .map(file -> new Square(Position.of(file, rank), createPiece(file, rank)))
                .collect(Collectors.toUnmodifiableList()));
        }

        return new ChessBoard(squares);
    }

    private static Piece createPiece(File file, Rank rank) {
        Team team = Team.initialOf(rank);
        if (rank == Rank.TWO || rank == Rank.SEVEN) {
            return new Pawn(team);
        }
        if (rank == Rank.ONE || rank == Rank.EIGHT) {
            return createHeavyPiece(file, team);
        }
        return NoPiece.getInstance();
    }

    private static Piece createHeavyPiece(final File file, final Team team) {
        if (file == File.A || file == File.H) {
            return new Rook(team);
        }
        if (file == File.B || file == File.G) {
            return new Knight(team);
        }
        if (file == File.C || file == File.F) {
            return new Bishop(team);
        }
        if (file == File.D) {
            return new Queen(team);
        }
        return new King(team);
    }
}
