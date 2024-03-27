package chess.domain.chesspiece;

import chess.domain.chessboard.Lettering;
import chess.domain.chessboard.Numbering;
import chess.domain.chessboard.Square;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessPieceStartingPosition {

    private static final ChessPieceStartingPosition instance = new ChessPieceStartingPosition();

    private final Map<ChessPieceType, List<Square>> startingPosition = initializeStatingPosition();

    private ChessPieceStartingPosition() {
    }

    public static ChessPieceStartingPosition getInstance() {
        return instance;
    }

    public ChessPieceType determineChessPieceType(Square square) {
        return startingPosition.keySet().stream()
                .filter(chessPieceType -> {
                    List<Square> squares = startingPosition.get(chessPieceType);
                    return squares.contains(square);
                })
                .findFirst()
                .orElse(ChessPieceType.NONE);
    }

    private Map<ChessPieceType, List<Square>> initializeStatingPosition() {
        Map<ChessPieceType, List<Square>> startingPosition = new HashMap<>();
        addKingStartingPosition(startingPosition);
        addQueenStartingPosition(startingPosition);
        addBishopStartingPosition(startingPosition);
        addKnightStartingPosition(startingPosition);
        addRookStartingPosition(startingPosition);
        addPawnStartingPosition(startingPosition);
        
        return startingPosition;
    }

    private void addKingStartingPosition(Map<ChessPieceType, List<Square>> startingPosition) {
        List<Lettering> kingLettering = List.of(Lettering.E);
        List<Square> startingSquare = createStartingSquare(kingLettering, firstLineNumbering());
        startingPosition.put(ChessPieceType.KING, startingSquare);
    }

    private void addQueenStartingPosition(Map<ChessPieceType, List<Square>> startingPosition) {
        List<Lettering> queenLettering = List.of(Lettering.D);
        List<Square> startingSquare = createStartingSquare(queenLettering, firstLineNumbering());
        startingPosition.put(ChessPieceType.QUEEN, startingSquare);
    }

    private void addBishopStartingPosition(Map<ChessPieceType, List<Square>> startingPosition) {
        List<Lettering> bishopLettering = List.of(Lettering.C, Lettering.F);
        List<Square> startingSquare = createStartingSquare(bishopLettering, firstLineNumbering());
        startingPosition.put(ChessPieceType.BISHOP, startingSquare);
    }

    private void addKnightStartingPosition(Map<ChessPieceType, List<Square>> startingPosition) {
        List<Lettering> knightLettering = List.of(Lettering.B, Lettering.G);
        List<Square> startingSquare = createStartingSquare(knightLettering, firstLineNumbering());
        startingPosition.put(ChessPieceType.KNIGHT, startingSquare);
    }

    private void addRookStartingPosition(Map<ChessPieceType, List<Square>> startingPosition) {
        List<Lettering> rookLettering = List.of(Lettering.A, Lettering.H);
        List<Square> startingSquare = createStartingSquare(rookLettering, firstLineNumbering());
        startingPosition.put(ChessPieceType.ROOK, startingSquare);
    }

    private void addPawnStartingPosition(Map<ChessPieceType, List<Square>> startingPosition) {
        List<Lettering> pawnLettering = List.of(
                Lettering.A,
                Lettering.B,
                Lettering.C,
                Lettering.D,
                Lettering.E,
                Lettering.F,
                Lettering.G,
                Lettering.H
        );
        List<Square> startingSquare = createStartingSquare(pawnLettering, secondLineNumbering());
        startingPosition.put(ChessPieceType.PAWN, startingSquare);
    }

    private List<Numbering> firstLineNumbering() {
        return List.of(Numbering.ONE, Numbering.EIGHT);
    }

    private List<Numbering> secondLineNumbering() {
        return List.of(Numbering.TWO, Numbering.SEVEN);
    }

    private List<Square> createStartingSquare(List<Lettering> typeLettering, List<Numbering> numberings) {
        return typeLettering.stream()
                .flatMap(lettering -> numberings.stream()
                        .map(numbering -> new Square(lettering, numbering)))
                .toList();
    }
}
