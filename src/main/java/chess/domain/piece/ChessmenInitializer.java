package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.PositionConverter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessmenInitializer {

    public Pieces init() {
        List<Piece> chessmen = Stream.of(
                initBlackStrongMen(),
                initBlackPawns(),
                initWhitePawns(),
                initWhiteStrongMen())
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        return new Pieces(chessmen);
    }

    private List<Piece> initBlackStrongMen() {
        return List.of(
            new Rook(Color.BLACK, Position.of("a8")),
            new Knight(Color.BLACK, Position.of("b8")),
            new Bishop(Color.BLACK, Position.of("c8")),
            new Queen(Color.BLACK, Position.of("d8")),
            new King(Color.BLACK, Position.of("e8")),
            new Bishop(Color.BLACK, Position.of("f8")),
            new Knight(Color.BLACK, Position.of("g8")),
            new Rook(Color.BLACK, Position.of("h8")));
    }

    private List<Piece> initBlackPawns() {
        return PositionConverter.VALID_FILES.chars()
            .mapToObj(rank -> (char) rank + "" + Pawn.BLACK_INIT_RANK)
            .map(positionKey -> new Pawn(Color.BLACK, Position.of(positionKey)))
            .collect(Collectors.toList());
    }

    private List<Piece> initWhitePawns() {
        return PositionConverter.VALID_FILES.chars()
            .mapToObj(rank -> (char) rank + "" + Pawn.WHITE_INIT_RANK)
            .map(positionKey -> new Pawn(Color.WHITE, Position.of(positionKey)))
            .collect(Collectors.toList());
    }

    private List<Piece> initWhiteStrongMen() {
        return List.of(
            new Rook(Color.WHITE, Position.of("a1")),
            new Knight(Color.WHITE, Position.of("b1")),
            new Bishop(Color.WHITE, Position.of("c1")),
            new Queen(Color.WHITE, Position.of("d1")),
            new King(Color.WHITE, Position.of("e1")),
            new Bishop(Color.WHITE, Position.of("f1")),
            new Knight(Color.WHITE, Position.of("g1")),
            new Rook(Color.WHITE, Position.of("h1")));
    }

}
