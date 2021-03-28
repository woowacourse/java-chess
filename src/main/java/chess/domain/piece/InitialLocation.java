package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Arrays;
import java.util.List;

public enum InitialLocation {

    KING("K", Arrays.asList(
        Position.of('e',1)
    )),
    QUEEN("Q", Arrays.asList(
        Position.of('d',1)
    )),
    KNIGHT("N", Arrays.asList(
        Position.of('b',1),
        Position.of('g',1)
    )),
    BISHOP("B", Arrays.asList(
        Position.of('c', 1),
        Position.of('f', 1)
        )
    ),
    ROOK("R", Arrays.asList(
        Position.of('a', 1),
        Position.of('h',1)
    )),
    PAWN("P", Arrays.asList(
        Position.of('a', 2),
        Position.of('b',2),
        Position.of('c',2),
        Position.of('d',2),
        Position.of('e',2),
        Position.of('f',2),
        Position.of('g',2),
        Position.of('h',2)
    )),
    VOID(".", Arrays.asList(
        Position.of('a', 3),
        Position.of('b',3),
        Position.of('c',3),
        Position.of('d',3),
        Position.of('e',3),
        Position.of('f',3),
        Position.of('g',3),
        Position.of('h',3),
        Position.of('a', 4),
        Position.of('b',4),
        Position.of('c',4),
        Position.of('d',4),
        Position.of('e',4),
        Position.of('f',4),
        Position.of('g',4),
        Position.of('h',4)
    ));

    public static final String INVALID_MATCH_PIECE = "맞는 말의 종류가 없습니다.";
    private final String symbol;
    private final List<Position> initialPositions;

    InitialLocation(String symbol,
        List<Position> initialPositions) {
        this.symbol = symbol;
        this.initialPositions = initialPositions;
    }

    public List<Position> bringPositions() {
        return this.initialPositions;
    }

    public boolean isSamePiece(PieceKind pieceKind) {
        return symbol.equals(pieceKind.getName(PieceColor.BLACK));
    }

    public static InitialLocation matchPiece(PieceKind pieceKind) {
        return Arrays.stream(values())
            .filter(element -> element.isSamePiece(pieceKind))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_MATCH_PIECE));
    }
}
