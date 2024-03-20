package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.ColumnPosition;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoardCreator {
    private static final Map<Class<? extends Piece>, List<Position>> INITIAL_PIECE_ARRANGEMENT = new HashMap<>() {
        {
            List<Position> rookPositions = List.of(Position.of(0, 0), Position.of(0, 7));
            put(Rook.class, rookPositions);

            List<Position> knightPositions = List.of(Position.of(0, 1), Position.of(0, 6));
            put(Knight.class, knightPositions);

            List<Position> bishopPositions = List.of(Position.of(0, 2), Position.of(0, 5));
            put(Bishop.class, bishopPositions);

            List<Position> queenPositions = List.of(Position.of(0, 3));
            put(Queen.class, queenPositions);

            List<Position> kingPositions = List.of(Position.of(0, 4));
            put(King.class, kingPositions);

            List<Position> pawnPositions = IntStream.rangeClosed(ColumnPosition.MIN_NUMBER, ColumnPosition.MAX_NUMBER)
                    .mapToObj(col -> Position.of(1, col))
                    .toList();
            put(Pawn.class, pawnPositions);
        }
    };

    public ChessBoard create() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        INITIAL_PIECE_ARRANGEMENT.entrySet().stream()
                .map(entry -> mapPositionToPiece(entry.getKey(), entry.getValue()))
                .forEach(positionPiece::putAll);
        return new ChessBoard(positionPiece);
    }


    //TODO: 의미 있는 메서드명 생각해보기
    private Map<Position, Piece> mapPositionToPiece(Class<? extends Piece> pieceType, List<Position> positions) {
        Map<Position, Piece> positionPiece = new HashMap<>();
        positions.forEach(position -> {
            positionPiece.put(position, createPieceInstance(pieceType, Team.BLACK));
            positionPiece.put(position.verticalReversePosition(), createPieceInstance(pieceType, Team.WHITE));
        });
        return positionPiece;
    }

    private Piece createPieceInstance(Class<? extends Piece> clazz, Team team) {
        try {
            return clazz.getDeclaredConstructor(Team.class)
                    .newInstance(team);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("[INTERNAL ERROR] 기물을 생성하여 배치하는 리플렉션에서 오류가 발생했습니다 ");
        }
    }
}
