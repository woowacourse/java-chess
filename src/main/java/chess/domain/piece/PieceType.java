package chess.domain.piece;

import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public enum  PieceType {
    ROOK(1, Rook::new, "r"),
    KNIGHT(2, Knight::new, "n"),
    BISHOP(3, Bishop::new, "b"),
    QUEEN(4, Queen::new, "q"),
    KING(5, King::new, "k");


    private static final int COLLUMN_SIZE = 8;
    private static final int DEFAULT_SYMMETRY_VALUE = 1;

    private final int initalX;
    private final InitializedPieceCreator initializedPieceCreator;
    private final String name;

    PieceType(int initalX, InitializedPieceCreator initializedPieceCreator, String name) {
        this.initalX = initalX;
        this.initializedPieceCreator = initializedPieceCreator;
        this.name = name;
    }

    //todo: refac
    public static PieceType valueOf(int x) {
        if (x == QUEEN.initalX) {
            return QUEEN;
        }

        if (x == KING.initalX) {
            return KING;
        }

        for (PieceType pieceType : values()) {
            if (match(x, pieceType)) {
                return pieceType;
            }
        }

        throw new IllegalArgumentException("해당하는 말이 없습니다.");
    }

    public Initialized createInitializedPiece(Position position, Team team) {
        return initializedPieceCreator.create(name, position, team);
    }

    private static boolean match(int x, PieceType pieceType) {
        return x == pieceType.getInitalX() || x == pieceType.getSymmetryOfInitialX();
    }

    private int getInitalX() {
        return initalX;
    }

    private int getSymmetryOfInitialX() {
        return COLLUMN_SIZE - initalX + DEFAULT_SYMMETRY_VALUE;
    }

    private interface InitializedPieceCreator {
        Initialized create(String name, Position position, Team team);
    }

}
