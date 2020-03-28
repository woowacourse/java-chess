package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class PieceFactory {

    public static Piece createPiece(Class<? extends Piece> type, Position position, Team team) {
        PieceType pieceType = PieceType.valueOf(type);
        try {
            Constructor<? extends Piece> constructor = type.getConstructor(String.class, Position.class, Team.class, List.class);
            return constructor.newInstance(pieceType.getName(), position, team, pieceType.getCanNotMoveStrategies());
        } catch (NoSuchMethodException
                | InvocationTargetException
                | IllegalAccessException
                | InstantiationException e) {
            throw new IllegalArgumentException(String.format("%s를 생성할 수 없습니다.", type));
        }

    }
}
