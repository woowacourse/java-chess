package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoardFactory {
    
    private ChessBoardFactory() {
        throw new IllegalStateException("Factory 클래스를 인스턴스화 할 수 없습니다!");
    }

    public static ChessBoard create() {
        List<Square> squares = Arrays.stream(File.values())
                .flatMap(ChessBoardFactory::createSquaresFromOneFile)
                .collect(Collectors.toUnmodifiableList());
        return new ChessBoard(squares);
    }

    private static Stream<Square> createSquaresFromOneFile(final File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Square(Position.of(rank, file), createPiece(rank, file)));
    }

    private static Piece createPiece(Rank rank, File file) {
        Team team = Team.initialOf(file);
        if (file == File.TWO || file == File.SEVEN) {
            return new Pawn(team);
        }
        if (file == File.ONE || file == File.EIGHT) {
            return createHeavyPiece(rank, team);
        }
        return Blank.getInstance();
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
        if (rank == Rank.E) {
            return new King(team);
        }
        throw new IllegalStateException("올바른 Rank가 아닙니다.");
    }
}
