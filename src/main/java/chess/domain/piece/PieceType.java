package chess.domain.piece;

import chess.domain.piece.bishop.Bishop;
import chess.domain.piece.king.King;
import chess.domain.piece.knight.Knight;
import chess.domain.piece.move.CanNotMoveStrategy;
import chess.domain.piece.queen.Queen;
import chess.domain.piece.rook.Rook;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.List;

//todo: refac
public enum  PieceType {
    ROOK(1, Rook::new, "r"),
    KNIGHT(2, Knight::new, "n"),
    BISHOP(3, Bishop::new, "b"),
    QUEEN(4, Queen::new, "q"),
    KING(5, King::new, "k");


    private static final int COLLUMN_SIZE = 8;
    private static final int DEFAULT_SYMMETRY_VALUE = 1;

    private final int initialX;
    private final InitializedPieceCreator initializedPieceCreator;
    private final String name;

    PieceType(int initialX, InitializedPieceCreator initializedPieceCreator, String name) {
        this.initialX = initialX;
        this.initializedPieceCreator = initializedPieceCreator;
        this.name = name;
    }

    //todo: refac
    public static PieceType valueOf(int x) {
        if (x == QUEEN.initialX) {
            return QUEEN;
        }

        if (x == KING.initialX) {
            return KING;
        }

        for (PieceType pieceType : values()) {
            if (match(x, pieceType)) {
                return pieceType;
            }
        }

        throw new IllegalArgumentException("해당하는 말이 없습니다.");
    }

    public Initialized createInitializedPiece(Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies) {
        return initializedPieceCreator.create(name, position, team, canNotMoveStrategies);
    }

    private static boolean match(int x, PieceType pieceType) {
        return x == pieceType.getInitialX() || x == pieceType.getSymmetryOfInitialX();
    }

    private int getInitialX() {
        return initialX;
    }

    private int getSymmetryOfInitialX() {
        return COLLUMN_SIZE - initialX + DEFAULT_SYMMETRY_VALUE;
    }

    private interface InitializedPieceCreator {
        Initialized create(String name, Position position, Team team, List<CanNotMoveStrategy> canNotMoveStrategies);
    }

}
