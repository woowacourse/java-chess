package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Rank;
import chess.domain.position.Position;
import chess.domain.position.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoardFactory {

    private ChessBoardFactory() {
        throw new IllegalStateException("Factory 클래스를 인스턴스화 할 수 없습니다!");
    }

    public static ChessBoard create() {
        List<Square> squares = Arrays.stream(Rank.values())
                .flatMap(ChessBoardFactory::createSquaresFromOneFile)
                .collect(Collectors.toUnmodifiableList());
        return new ChessBoard(squares);
    }

    private static Stream<Square> createSquaresFromOneFile(final Rank temp) {
        return Arrays.stream(File.values())
                .map(rank -> new Square(Position.of(rank, temp), createPiece(rank, temp)));
    }

    private static Piece createPiece(File file, Rank rank) {
        Team team = Team.initialOf(rank);
        if (rank == Rank.TWO || rank == Rank.SEVEN) {
            return new Pawn(team);
        }
        if (rank == Rank.ONE || rank == Rank.EIGHT) {
            return createHeavyPiece(file, team);
        }
        return Blank.getInstance();
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
        if (file == File.E) {
            return new King(team);
        }
        throw new IllegalStateException("올바른 Rank가 아닙니다.");
    }
}
