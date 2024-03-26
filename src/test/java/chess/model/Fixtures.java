package chess.model;

import static java.util.stream.Collectors.toMap;

import chess.model.material.Color;
import chess.model.piece.None;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class Fixtures {

    public static final Position A1 = Position.from("a1");

    public static final Position A2 = Position.from("a2");
    public static final Position A3 = Position.from("a3");
    public static final Position A4 = Position.from("a4");
    public static final Position A5 = Position.from("a5");
    public static final Position A6 = Position.from("a6");
    public static final Position A7 = Position.from("a7");
    public static final Position A8 = Position.from("a8");
    public static final Position B1 = Position.from("b1");

    public static final Position B2 = Position.from("b2");
    public static final Position B3 = Position.from("b3");
    public static final Position B4 = Position.from("b4");
    public static final Position B5 = Position.from("b5");
    public static final Position B6 = Position.from("b6");
    public static final Position B7 = Position.from("b7");
    public static final Position B8 = Position.from("b8");
    public static final Position C1 = Position.from("c1");

    public static final Position C2 = Position.from("c2");
    public static final Position C3 = Position.from("c3");
    public static final Position C4 = Position.from("c4");
    public static final Position C5 = Position.from("c5");
    public static final Position C6 = Position.from("c6");
    public static final Position C7 = Position.from("c7");
    public static final Position C8 = Position.from("c8");
    public static final Position D1 = Position.from("d1");

    public static final Position D2 = Position.from("d2");
    public static final Position D3 = Position.from("d3");
    public static final Position D4 = Position.from("d4");
    public static final Position D5 = Position.from("d5");
    public static final Position D6 = Position.from("d6");
    public static final Position D7 = Position.from("d7");
    public static final Position D8 = Position.from("d8");
    public static final Position E1 = Position.from("e1");

    public static final Position E2 = Position.from("e2");
    public static final Position E3 = Position.from("e3");
    public static final Position E4 = Position.from("e4");
    public static final Position E5 = Position.from("e5");
    public static final Position E6 = Position.from("e6");
    public static final Position E7 = Position.from("e7");
    public static final Position E8 = Position.from("e8");
    public static final Position F1 = Position.from("f1");

    public static final Position F2 = Position.from("f2");
    public static final Position F3 = Position.from("f3");
    public static final Position F4 = Position.from("f4");
    public static final Position F5 = Position.from("f5");
    public static final Position F6 = Position.from("f6");
    public static final Position F7 = Position.from("f7");
    public static final Position F8 = Position.from("f8");
    public static final Position G1 = Position.from("g1");

    public static final Position G2 = Position.from("g2");
    public static final Position G3 = Position.from("g3");
    public static final Position G4 = Position.from("g4");
    public static final Position G5 = Position.from("g5");
    public static final Position G6 = Position.from("g6");
    public static final Position G7 = Position.from("g7");
    public static final Position G8 = Position.from("g8");
    public static final Position H1 = Position.from("h1");

    public static final Position H2 = Position.from("h2");
    public static final Position H3 = Position.from("h3");
    public static final Position H4 = Position.from("h4");
    public static final Position H5 = Position.from("h5");
    public static final Position H6 = Position.from("h6");
    public static final Position H7 = Position.from("h7");
    public static final Position H8 = Position.from("h8");
    public static Map<Position, Piece> EMPTY_PIECES = initiation();

    public static Map<Position, Piece> initiation() {
        List<Position> positions = List.of(
            A1, A2, A3, A4, A5, A6, A7, A8,
            B1, B2, B3, B4, B5, B6, B7, B8,
            C1, C2, C3, C4, C5, C6, C7, C8,
            D1, D2, D3, D4, D5, D6, D7, D8,
            E1, E2, E3, E4, E5, E6, E7, E8,
            F1, F2, F3, F4, F5, F6, F7, F8,
            G1, G2, G3, G4, G5, G6, G7, G8,
            H1, H2, H3, H4, H5, H6, H7, H8
        );
        return positions.stream()
            .collect(toMap(position -> position, position -> new None()));
    }

    private Fixtures() {
    }
}
