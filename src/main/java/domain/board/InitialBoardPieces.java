package domain.board;

import domain.board.piece.Camp;
import domain.board.piece.Piece;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.util.Arrays;
import java.util.Optional;

public enum InitialBoardPieces {
    //흰색 진영 귀족 라인
    A1(Location.of(Row.valueOf(0), Column.valueOf(0)), Piece.rookBelongs(Camp.WHITE)),
    B1(Location.of(Row.valueOf(0), Column.valueOf(1)), Piece.knightBelongs(Camp.WHITE)),
    C1(Location.of(Row.valueOf(0), Column.valueOf(2)), Piece.bishopBelongs(Camp.WHITE)),
    D1(Location.of(Row.valueOf(0), Column.valueOf(3)), Piece.kingBelongs(Camp.WHITE)),
    E1(Location.of(Row.valueOf(0), Column.valueOf(4)), Piece.queenBelongs(Camp.WHITE)),
    F1(Location.of(Row.valueOf(0), Column.valueOf(5)), Piece.bishopBelongs(Camp.WHITE)),
    G1(Location.of(Row.valueOf(0), Column.valueOf(6)), Piece.knightBelongs(Camp.WHITE)),
    H1(Location.of(Row.valueOf(0), Column.valueOf(7)), Piece.rookBelongs(Camp.WHITE)),

    //흰색 진영 폰 라인
    A2(Location.of(Row.valueOf(1), Column.valueOf(0)), Piece.pawnBelongs(Camp.WHITE)),
    B2(Location.of(Row.valueOf(1), Column.valueOf(1)), Piece.pawnBelongs(Camp.WHITE)),
    C2(Location.of(Row.valueOf(1), Column.valueOf(2)), Piece.pawnBelongs(Camp.WHITE)),
    D2(Location.of(Row.valueOf(1), Column.valueOf(3)), Piece.pawnBelongs(Camp.WHITE)),
    E2(Location.of(Row.valueOf(1), Column.valueOf(4)), Piece.pawnBelongs(Camp.WHITE)),
    F2(Location.of(Row.valueOf(1), Column.valueOf(5)), Piece.pawnBelongs(Camp.WHITE)),
    G2(Location.of(Row.valueOf(1), Column.valueOf(6)), Piece.pawnBelongs(Camp.WHITE)),
    H2(Location.of(Row.valueOf(1), Column.valueOf(7)), Piece.pawnBelongs(Camp.WHITE)),

    //검은색 진영 폰 라인
    A7(Location.of(Row.valueOf(6), Column.valueOf(0)), Piece.pawnBelongs(Camp.BLACK)),
    B7(Location.of(Row.valueOf(6), Column.valueOf(1)), Piece.pawnBelongs(Camp.BLACK)),
    C7(Location.of(Row.valueOf(6), Column.valueOf(2)), Piece.pawnBelongs(Camp.BLACK)),
    D7(Location.of(Row.valueOf(6), Column.valueOf(3)), Piece.pawnBelongs(Camp.BLACK)),
    E7(Location.of(Row.valueOf(6), Column.valueOf(4)), Piece.pawnBelongs(Camp.BLACK)),
    F7(Location.of(Row.valueOf(6), Column.valueOf(5)), Piece.pawnBelongs(Camp.BLACK)),
    G7(Location.of(Row.valueOf(6), Column.valueOf(6)), Piece.pawnBelongs(Camp.BLACK)),
    H7(Location.of(Row.valueOf(6), Column.valueOf(7)), Piece.pawnBelongs(Camp.BLACK)),

    //검은색 진영 귀족 라인
    A8(Location.of(Row.valueOf(7), Column.valueOf(0)), Piece.rookBelongs(Camp.BLACK)),
    B8(Location.of(Row.valueOf(7), Column.valueOf(1)), Piece.knightBelongs(Camp.BLACK)),
    C8(Location.of(Row.valueOf(7), Column.valueOf(2)), Piece.bishopBelongs(Camp.BLACK)),
    D8(Location.of(Row.valueOf(7), Column.valueOf(3)), Piece.kingBelongs(Camp.BLACK)),
    E8(Location.of(Row.valueOf(7), Column.valueOf(4)), Piece.queenBelongs(Camp.BLACK)),
    F8(Location.of(Row.valueOf(7), Column.valueOf(5)), Piece.bishopBelongs(Camp.BLACK)),
    G8(Location.of(Row.valueOf(7), Column.valueOf(6)), Piece.knightBelongs(Camp.BLACK)),
    H8(Location.of(Row.valueOf(7), Column.valueOf(7)), Piece.rookBelongs(Camp.BLACK));

    private final Location location;
    private final Piece piece;

    InitialBoardPieces(final Location location, final Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public static Optional<Piece> find(final Location findLocation) {
        return Arrays.stream(values())
            .filter((initialPiece) -> initialPiece.location.equals(findLocation))
            .map(findInitialPiece -> findInitialPiece.piece)
            .findFirst();
    }
}
