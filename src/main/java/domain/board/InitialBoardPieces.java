package domain.board;

import domain.board.piece.Piece;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.util.Arrays;
import java.util.Optional;

public enum InitialBoardPieces {
    //흰색 진영 귀족 라인
    A1(Location.of(Row.valueOf(0), Column.valueOf(0)), Piece.whiteRook()),
    B1(Location.of(Row.valueOf(0), Column.valueOf(1)), Piece.whiteKnight()),
    C1(Location.of(Row.valueOf(0), Column.valueOf(2)), Piece.whiteBishop()),
    D1(Location.of(Row.valueOf(0), Column.valueOf(3)), Piece.whiteKing()),
    E1(Location.of(Row.valueOf(0), Column.valueOf(4)), Piece.whiteQueen()),
    F1(Location.of(Row.valueOf(0), Column.valueOf(5)), Piece.whiteBishop()),
    G1(Location.of(Row.valueOf(0), Column.valueOf(6)), Piece.whiteKnight()),
    H1(Location.of(Row.valueOf(0), Column.valueOf(7)), Piece.whiteRook()),

    //흰색 진영 폰 라인
    A2(Location.of(Row.valueOf(1), Column.valueOf(0)), Piece.whitePawn()),
    B2(Location.of(Row.valueOf(1), Column.valueOf(1)), Piece.whitePawn()),
    C2(Location.of(Row.valueOf(1), Column.valueOf(2)), Piece.whitePawn()),
    D2(Location.of(Row.valueOf(1), Column.valueOf(3)), Piece.whitePawn()),
    E2(Location.of(Row.valueOf(1), Column.valueOf(4)), Piece.whitePawn()),
    F2(Location.of(Row.valueOf(1), Column.valueOf(5)), Piece.whitePawn()),
    G2(Location.of(Row.valueOf(1), Column.valueOf(6)), Piece.whitePawn()),
    H2(Location.of(Row.valueOf(1), Column.valueOf(7)), Piece.whitePawn()),

    //검은색 진영 폰 라인
    A7(Location.of(Row.valueOf(6), Column.valueOf(0)), Piece.blackPawn()),
    B7(Location.of(Row.valueOf(6), Column.valueOf(1)), Piece.blackPawn()),
    C7(Location.of(Row.valueOf(6), Column.valueOf(2)), Piece.blackPawn()),
    D7(Location.of(Row.valueOf(6), Column.valueOf(3)), Piece.blackPawn()),
    E7(Location.of(Row.valueOf(6), Column.valueOf(4)), Piece.blackPawn()),
    F7(Location.of(Row.valueOf(6), Column.valueOf(5)), Piece.blackPawn()),
    G7(Location.of(Row.valueOf(6), Column.valueOf(6)), Piece.blackPawn()),
    H7(Location.of(Row.valueOf(6), Column.valueOf(7)), Piece.blackPawn()),

    //검은색 진영 귀족 라인
    A8(Location.of(Row.valueOf(7), Column.valueOf(0)), Piece.blackRook()),
    B8(Location.of(Row.valueOf(7), Column.valueOf(1)), Piece.blackKnight()),
    C8(Location.of(Row.valueOf(7), Column.valueOf(2)), Piece.blackBishop()),
    D8(Location.of(Row.valueOf(7), Column.valueOf(3)), Piece.blackKing()),
    E8(Location.of(Row.valueOf(7), Column.valueOf(4)), Piece.blackQueen()),
    F8(Location.of(Row.valueOf(7), Column.valueOf(5)), Piece.blackBishop()),
    G8(Location.of(Row.valueOf(7), Column.valueOf(6)), Piece.blackKnight()),
    H8(Location.of(Row.valueOf(7), Column.valueOf(7)), Piece.blackRook());

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
