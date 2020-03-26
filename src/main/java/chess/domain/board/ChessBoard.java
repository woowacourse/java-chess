package chess.domain.board;

import chess.domain.Piece.Piece;
import chess.domain.Piece.PieceType;
import chess.domain.Piece.blank.Blank;
import chess.domain.Piece.pawn.InitializedPawn;
import chess.domain.Piece.state.Initialized;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard implements Board {
    private static final int SIZE = 64;
    private static final int COLLUMN_START = 1;
    private static final int COLLUMN_END = 8;
    public static final int BLACK_PAWN_ROW = 7;
    public static final int WHITE_PAWN_ROW = 2;

    private final Map<Position, Piece> pieces;

    private ChessBoard(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public static ChessBoard initiaize() {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);

        initializeBlanks(pieces);
        initializeWhiteTeam(pieces);
        return new ChessBoard(pieces);
    }


    @Override
    public Board movePiece(Position from, Position to) {
        return null;
    }

    @Override
    public Piece getPiece(Position position) {
        return pieces.get(position);
    }

    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, COLLUMN_END);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, COLLUMN_START);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = COLLUMN_START; x <= COLLUMN_END; x++) {
            PieceType pieceType = PieceType.valueOf(x);
            Position position = Position.of(x, edgeRow);
            Initialized piece = pieceType.createInitializedPiece(position, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int y = row; y <= row; y++) {
            for (int x = 1; x <= 8; x++) {
                Position position = Position.of(x, y);
                InitializedPawn initializedPawn = new InitializedPawn("p", position, team);
                pieces.put(position, initializedPawn);

            }
        }
    }

    private static void initializeBlanks(Map<Position, Piece> pieces) {
        for (int y = 3; y <= 6; y++) {
            for (int x = 1; x <= 8; x++) {
                Position position = Position.of(x, y);
                Blank blank = new Blank(".", position, Team.NOT_ASSIGNED);
                pieces.put(position, blank);
            }
        }
    }
}
