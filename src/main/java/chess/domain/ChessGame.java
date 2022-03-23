package chess.domain;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.Pawn.BLACK_PAWN_INIT_RANK;
import static chess.domain.piece.Pawn.WHITE_PAWN_INIT_RANK;
import static chess.domain.piece.position.PositionUtil.VALID_FILES;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGame {

    private final List<Piece> chessmen = new ArrayList<>(32);

    public ChessGame() {
        chessmen.addAll(initBlackStrongMen());
        chessmen.addAll(initBlackPawns());
        chessmen.addAll(initWhitePawns());
        chessmen.addAll(initWhiteStrongMen());
    }

    private List<Piece> initBlackStrongMen() {
        return List.of(
                new Rook(BLACK, Position.of("a8")),
                new Knight(BLACK, Position.of("b8")),
                new Bishop(BLACK, Position.of("c8")),
                new Queen(BLACK, Position.of("d8")),
                new King(BLACK, Position.of("e8")),
                new Bishop(BLACK, Position.of("f8")),
                new Knight(BLACK, Position.of("g8")),
                new Rook(BLACK, Position.of("h8")));
    }

    private List<Piece> initBlackPawns() {
        return VALID_FILES.chars()
                .mapToObj(rank -> (char) rank + BLACK_PAWN_INIT_RANK)
                .map(positionKey -> new Pawn(BLACK, Position.of(positionKey)))
                .collect(Collectors.toList());
    }

    private List<Piece> initWhitePawns() {
        return VALID_FILES.chars()
                .mapToObj(rank -> (char) rank + WHITE_PAWN_INIT_RANK)
                .map(positionKey -> new Pawn(WHITE, Position.of(positionKey)))
                .collect(Collectors.toList());
    }

    private List<Piece> initWhiteStrongMen() {
        return List.of(
                new Rook(WHITE, Position.of("a1")),
                new Knight(WHITE, Position.of("b1")),
                new Bishop(WHITE, Position.of("c1")),
                new Queen(WHITE, Position.of("d1")),
                new King(WHITE, Position.of("e1")),
                new Bishop(WHITE, Position.of("f1")),
                new Knight(WHITE, Position.of("g1")),
                new Rook(WHITE, Position.of("h1")));
    }

    public List<Piece> getChessmen() {
        return chessmen;
    }

    @Override
    public String toString() {
        return "ChessGame{" + "chessmen=" + chessmen + '}';
    }
}
