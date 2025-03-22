package chess.board;

import static chess.board.Column.A;
import static chess.board.Column.B;
import static chess.board.Column.C;
import static chess.board.Column.D;
import static chess.board.Column.E;
import static chess.board.Column.F;
import static chess.board.Column.G;
import static chess.board.Column.H;
import static chess.board.Row.EIGHT;
import static chess.board.Row.FIVE;
import static chess.board.Row.FOUR;
import static chess.board.Row.ONE;
import static chess.board.Row.SEVEN;
import static chess.board.Row.SIX;
import static chess.board.Row.THREE;
import static chess.board.Row.TWO;

public enum InitPositions {
    A1(new Position(A, ONE)),
    A2(new Position(A, TWO)),
    A3(new Position(A, THREE)),
    A4(new Position(A, FOUR)),
    A5(new Position(A, FIVE)),
    A6(new Position(A, SIX)),
    A7(new Position(A, SEVEN)),
    A8(new Position(A, EIGHT)),
    B1(new Position(B, ONE)),
    B2(new Position(B, TWO)),
    B3(new Position(B, THREE)),
    B4(new Position(B, FOUR)),
    B5(new Position(B, FIVE)),
    B6(new Position(B, SIX)),
    B7(new Position(B, SEVEN)),
    B8(new Position(B, EIGHT)),
    C1(new Position(C, ONE)),
    C2(new Position(C, TWO)),
    C3(new Position(C, THREE)),
    C4(new Position(C, FOUR)),
    C5(new Position(C, FIVE)),
    C6(new Position(C, SIX)),
    C7(new Position(C, SEVEN)),
    C8(new Position(C, EIGHT)),
    D1(new Position(D, ONE)),
    D2(new Position(D, TWO)),
    D3(new Position(D, THREE)),
    D4(new Position(D, FOUR)),
    D5(new Position(D, FIVE)),
    D6(new Position(D, SIX)),
    D7(new Position(D, SEVEN)),
    D8(new Position(D, EIGHT)),
    E1(new Position(E, ONE)),
    E2(new Position(E, TWO)),
    E3(new Position(E, THREE)),
    E4(new Position(E, FOUR)),
    E5(new Position(E, FIVE)),
    E6(new Position(E, SIX)),
    E7(new Position(E, SEVEN)),
    E8(new Position(E, EIGHT)),
    F1(new Position(F, ONE)),
    F2(new Position(F, TWO)),
    F3(new Position(F, THREE)),
    F4(new Position(F, FOUR)),
    F5(new Position(F, FIVE)),
    F6(new Position(F, SIX)),
    F7(new Position(F, SEVEN)),
    F8(new Position(F, EIGHT)),
    G1(new Position(G, ONE)),
    G2(new Position(G, TWO)),
    G3(new Position(G, THREE)),
    G4(new Position(G, FOUR)),
    G5(new Position(G, FIVE)),
    G6(new Position(G, SIX)),
    G7(new Position(G, SEVEN)),
    G8(new Position(G, EIGHT)),
    H1(new Position(H, ONE)),
    H2(new Position(H, TWO)),
    H3(new Position(H, THREE)),
    H4(new Position(H, FOUR)),
    H5(new Position(H, FIVE)),
    H6(new Position(H, SIX)),
    H7(new Position(H, SEVEN)),
    H8(new Position(H, EIGHT));

    public final Position position;

    InitPositions(Position position) {
        this.position = position;
    }
}
