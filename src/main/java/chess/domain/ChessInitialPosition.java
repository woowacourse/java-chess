package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Type;

import java.util.*;

public class ChessInitialPosition {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 8;
    private static final int BLACK_FIRST_Y = 8;
    private static final int WHITE_FIRST_Y = 1;
    private static final int BLACK_PAWN_Y = 7;
    private static final int WHITE_PAWN_Y = 2;
    private static final int FIRST_ROOK_X = 1;
    private static final int SECOND_ROOK_X = 8;
    private static final int FIRST_KNIGHT_X = 2;
    private static final int SECOND_KNIGHT_X = 7;
    private static final int FIRST_BISHOP_X = 3;
    private static final int SECOND_BISHOP_X = 6;
    private static final int QUEEN_X = 4;
    private static final int KING_X = 4;

    private static final Map<ChessPieceInfo, List<Position>> initialPositions = new HashMap<>();

    static {
        initializePawnPosition(Player.BLACK, BLACK_PAWN_Y);
        initializePawnPosition(Player.WHITE, WHITE_PAWN_Y);
        initializeFirstPosition(Player.BLACK, BLACK_FIRST_Y);
        initializeFirstPosition(Player.WHITE, WHITE_FIRST_Y);
    }

    private static void initializePawnPosition(Player player, int y) {
        List<Position> positions = new ArrayList<>();
        for (int i = MIN_BOUND; i <= MAX_BOUND; i++) {
            positions.add(Position.getPosition(i, y));
        }
        initialPositions.put(ChessPieceInfo.getChessPiece(player, Type.PAWN), positions);
    }

    private static void initializeFirstPosition(Player player, int y) {
        initialPositions.put(ChessPieceInfo.getChessPiece(player, Type.ROOK), Arrays.asList(
                Position.getPosition(FIRST_ROOK_X, y),
                Position.getPosition(SECOND_ROOK_X, y)));
        initialPositions.put(ChessPieceInfo.getChessPiece(player, Type.KNIGHT), Arrays.asList(
                Position.getPosition(FIRST_KNIGHT_X, y),
                Position.getPosition(SECOND_KNIGHT_X, y)));
        initialPositions.put(ChessPieceInfo.getChessPiece(player, Type.BISHOP), Arrays.asList(
                Position.getPosition(FIRST_BISHOP_X, y),
                Position.getPosition(SECOND_BISHOP_X, y)));
        initialPositions.put(ChessPieceInfo.getChessPiece(player, Type.QUEEN), Arrays.asList(
                Position.getPosition(QUEEN_X, y)));
        initialPositions.put(ChessPieceInfo.getChessPiece(player, Type.KING), Arrays.asList(
                Position.getPosition(KING_X, y)));
    }

    public static ChessBoard generateChessBoard() {
        List<Piece> pieces = new ArrayList<>();
        for (ChessPieceInfo chessPieceInfo : initialPositions.keySet()) {
            pieces.addAll(generatePieces(chessPieceInfo));
        }
        return new ChessBoard(pieces);
    }

    private static List<Piece> generatePieces(ChessPieceInfo chessPieceInfo) {
        List<Piece> pieces = new ArrayList<>();
        for (Position position : initialPositions.get(chessPieceInfo)) {
            pieces.add(chessPieceInfo.generate(position));
        }
        return pieces;
    }
}
