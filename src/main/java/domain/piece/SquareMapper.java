package domain.piece;

import domain.chessboard.Empty;
import domain.chessboard.EmptyType;
import domain.chessboard.Square;
import domain.chessboard.SquareStatus;
import domain.chessboard.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SquareMapper {

    private static final Map<Type, Function<Color, SquareStatus>> squareStatusMapper;
    private static final List<Type> allTypes;

    static {
        squareStatusMapper = Map.of(
                EmptyType.EMPTY, color -> new Empty(),
                PieceType.KING, King::new,
                PieceType.PAWN, Pawn::new,
                PieceType.INIT_PAWN, InitPawn::new,
                PieceType.BISHOP, Bishop::new,
                PieceType.KNIGHT, Knight::new,
                PieceType.QUEEN, Queen::new,
                PieceType.ROOK, Rook::new
        );

        allTypes = new ArrayList<>(List.of(PieceType.values()));
        allTypes.addAll(List.of(EmptyType.values()));
    }

    public static Square toSquare(String pieceType, String pieceColor) {
        Color color = Color.fromName(pieceColor);
        Type resultType = allTypes.stream()
                .filter(type -> pieceType.equals(type.name()))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);

        return new Square(squareStatusMapper.get(resultType).apply(color));
    }

}
