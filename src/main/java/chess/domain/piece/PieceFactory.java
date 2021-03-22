package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PieceFactory {
    private static final List<Position> INITIAL_BISHOP_BLACK_POSITIONS =
            Arrays.asList(Position.of("c8"), Position.of("f8"));
    private static final List<Position> INITIAL_BISHOP_WHITE_POSITIONS =
            Arrays.asList(Position.of("c1"), Position.of("f1"));
    private static final Position INITIAL_KING_BLACK_POSITION = Position.of("e8");
    private static final Position INITIAL_KING_WHITE_POSITION = Position.of("e1");
    private static final List<Position> INITIAL_KNIGHT_BLACK_POSITIONS =
            Arrays.asList(Position.of("b8"), Position.of("g8"));
    private static final List<Position> INITIAL_KNIGHT_WHITE_POSITIONS =
            Arrays.asList(Position.of("b1"), Position.of("g1"));
    private static final List<Position> INITIAL_PAWN_BLACK_POSITIONS =
            Arrays.asList(Position.of("a7"), Position.of("b7"), Position.of("c7"), Position.of("d7"),
                    Position.of("e7"), Position.of("f7"), Position.of("g7"), Position.of("h7"));
    private static final List<Position> INITIAL_PAWN_WHITE_POSITIONS =
            Arrays.asList(Position.of("a2"), Position.of("b2"), Position.of("c2"), Position.of("d2"),
                    Position.of("e2"), Position.of("f2"), Position.of("g2"), Position.of("h2"));
    private static final Position INITIAL_QUEEN_BLACK_POSITION = Position.of("d8");
    private static final Position INITIAL_QUEEN_WHITE_POSITION = Position.of("d1");
    private static final List<Position> INITIAL_ROOK_BLACK_POSITIONS =
            Arrays.asList(Position.of("a8"), Position.of("h8"));
    private static final List<Position> INITIAL_ROOK_WHITE_POSITIONS =
            Arrays.asList(Position.of("a1"), Position.of("h1"));

    public PieceFactory() {
    }

    public static List<Piece> initialPieces() {
        List<Piece> initialPieces = new ArrayList<>();
        initialPieces.addAll(initialBishops());
        initialPieces.addAll(initialKings());
        initialPieces.addAll(initialKnights());
        initialPieces.addAll(initialPawns());
        initialPieces.addAll(initialQueens());
        initialPieces.addAll(initialRooks());
        return initialPieces;
    }

    private static List<Bishop> initialBishops() {
        List<Bishop> blackBishops = INITIAL_BISHOP_BLACK_POSITIONS.stream()
                .map(position -> new Bishop(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Bishop> whiteBishops = INITIAL_BISHOP_WHITE_POSITIONS.stream()
                .map(position -> new Bishop(position, Color.WHITE))
                .collect(Collectors.toList());
        blackBishops.addAll(whiteBishops);
        return blackBishops;
    }

    private static List<King> initialKings() {
        List<King> kings = new ArrayList();
        kings.add(new King(INITIAL_KING_BLACK_POSITION, Color.BLACK));
        kings.add(new King(INITIAL_KING_WHITE_POSITION, Color.WHITE));
        return kings;
    }

    public static List<Knight> initialKnights() {
        List<Knight> blackKnights = INITIAL_KNIGHT_BLACK_POSITIONS.stream()
                .map(position -> new Knight(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Knight> whiteKnights = INITIAL_KNIGHT_WHITE_POSITIONS.stream()
                .map(position -> new Knight(position, Color.WHITE))
                .collect(Collectors.toList());
        blackKnights.addAll(whiteKnights);
        return blackKnights;
    }

    public static List<Pawn> initialPawns() {
        List<Pawn> blackPawns = INITIAL_PAWN_BLACK_POSITIONS.stream()
                .map(position -> new Pawn(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Pawn> whitePawns = INITIAL_PAWN_WHITE_POSITIONS.stream()
                .map(position -> new Pawn(position, Color.WHITE))
                .collect(Collectors.toList());
        blackPawns.addAll(whitePawns);
        return blackPawns;
    }

    public static List<Queen> initialQueens() {
        List<Queen> queens = new ArrayList();
        queens.add(new Queen(INITIAL_QUEEN_BLACK_POSITION, Color.BLACK));
        queens.add(new Queen(INITIAL_QUEEN_WHITE_POSITION, Color.WHITE));
        return queens;
    }

    public static List<Rook> initialRooks() {
        List<Rook> blackRooks = INITIAL_ROOK_BLACK_POSITIONS.stream()
                .map(position -> new Rook(position, Color.BLACK))
                .collect(Collectors.toList());
        List<Rook> whiteRooks = INITIAL_ROOK_WHITE_POSITIONS.stream()
                .map(position -> new Rook(position, Color.WHITE))
                .collect(Collectors.toList());
        blackRooks.addAll(whiteRooks);
        return blackRooks;
    }
}
