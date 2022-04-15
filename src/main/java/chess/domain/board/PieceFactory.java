package chess.domain.board;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum PieceFactory {

    KING("king", King::new),
    QUEEN("queen", Queen::new),
    BISHOP("bishop", Bishop::new),
    ROOK("rook", Rook::new),
    KNIGHT("knight", Knight::new),
    PAWN("pawn", Pawn::new),
    ;

    private final String type;
    private final Function<Color, Piece> pieceGenerator;


    PieceFactory(String type, Function<Color, Piece> pieceGenerator) {
        this.type = type;
        this.pieceGenerator = pieceGenerator;
    }

    public static Piece generatePieceWith(String type, Color color) {
        return Arrays.stream(PieceFactory.values())
                .filter(pieceFactory -> pieceFactory.type.equals(type))
                .findAny()
                .map(pieceFactory -> pieceFactory.pieceGenerator.apply(color))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피스 정보입니다"));
    }

    public static Map<Position, Piece> createInitializedChessBoard() {
        Map<Position, Piece> result = new HashMap<>();
        result.put(Position.of("a8"), new Rook(Color.BLACK));
        result.put(Position.of("b8"), new Knight(Color.BLACK));
        result.put(Position.of("c8"), new Bishop(Color.BLACK));
        result.put(Position.of("d8"), new Queen(Color.BLACK));
        result.put(Position.of("e8"), new King(Color.BLACK));
        result.put(Position.of("f8"), new Bishop(Color.BLACK));
        result.put(Position.of("g8"), new Knight(Color.BLACK));
        result.put(Position.of("h8"), new Rook(Color.BLACK));

        result.put(Position.of("a7"), new Pawn(Color.BLACK));
        result.put(Position.of("b7"), new Pawn(Color.BLACK));
        result.put(Position.of("c7"), new Pawn(Color.BLACK));
        result.put(Position.of("d7"), new Pawn(Color.BLACK));
        result.put(Position.of("e7"), new Pawn(Color.BLACK));
        result.put(Position.of("f7"), new Pawn(Color.BLACK));
        result.put(Position.of("g7"), new Pawn(Color.BLACK));
        result.put(Position.of("h7"), new Pawn(Color.BLACK));

        result.put(Position.of("a1"), new Rook(Color.WHITE));
        result.put(Position.of("b1"), new Knight(Color.WHITE));
        result.put(Position.of("c1"), new Bishop(Color.WHITE));
        result.put(Position.of("d1"), new Queen(Color.WHITE));
        result.put(Position.of("e1"), new King(Color.WHITE));
        result.put(Position.of("f1"), new Bishop(Color.WHITE));
        result.put(Position.of("g1"), new Knight(Color.WHITE));
        result.put(Position.of("h1"), new Rook(Color.WHITE));

        result.put(Position.of("a2"), new Pawn(Color.WHITE));
        result.put(Position.of("b2"), new Pawn(Color.WHITE));
        result.put(Position.of("c2"), new Pawn(Color.WHITE));
        result.put(Position.of("d2"), new Pawn(Color.WHITE));
        result.put(Position.of("e2"), new Pawn(Color.WHITE));
        result.put(Position.of("f2"), new Pawn(Color.WHITE));
        result.put(Position.of("g2"), new Pawn(Color.WHITE));
        result.put(Position.of("h2"), new Pawn(Color.WHITE));
        return result;
    }

}
