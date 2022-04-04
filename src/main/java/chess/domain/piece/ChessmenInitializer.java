package chess.domain.piece;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessmenInitializer {

    private static final Rank BLACK_STRONGMEN_INIT_RANK = Rank.EIGHT;
    private static final Rank WHITE_STRONGMEN_INIT_RANK = Rank.ONE;

    public Pieces init() {
        List<Piece> chessmen = Stream.of(
                initStrongMen(Color.BLACK, BLACK_STRONGMEN_INIT_RANK),
                initPawns(Color.BLACK, Pawn.BLACK_INIT_RANK),
                initPawns(Color.WHITE, Pawn.WHITE_INIT_RANK),
                initStrongMen(Color.WHITE, WHITE_STRONGMEN_INIT_RANK))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        return new Pieces(chessmen);
    }


    private List<Piece> initStrongMen(Color color, Rank rank) {
        return List.of(
            new Rook(color, Position.from(File.A, rank)),
            new Knight(color, Position.from(File.B, rank)),
            new Bishop(color, Position.from(File.C, rank)),
            new Queen(color, Position.from(File.D, rank)),
            new King(color, Position.from(File.E, rank)),
            new Bishop(color, Position.from(File.F, rank)),
            new Knight(color, Position.from(File.G, rank)),
            new Rook(color, Position.from(File.H, rank)));
    }

    private List<Piece> initPawns(Color color, Rank rank) {
        return Arrays.stream(File.values())
            .map(file -> file.getRawFile() + rank.getRawRank())
            .map(positionKey -> new Pawn(color, Position.of(positionKey)))
            .collect(Collectors.toList());
    }

}
