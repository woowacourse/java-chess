package chess.domain;

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
            List<Position> rookPositions = List.of(Position.of(1, 1), Position.of(1, 8));
            put(Rook.class, rookPositions);

            List<Position> knightPositions = List.of(Position.of(1, 2), Position.of(1, 7));
            put(Knight.class, knightPositions);

            List<Position> bishopPositions = List.of(Position.of(1, 3), Position.of(1, 6));
            put(Bishop.class, bishopPositions);

            List<Position> queenPositions = List.of(Position.of(1, 4));
            put(Queen.class, queenPositions);

            List<Position> kingPositions = List.of(Position.of(1, 5));
            put(King.class, kingPositions);

            List<Position> pawnPositions = IntStream.rangeClosed(ColumnPosition.MIN_NUMBER, ColumnPosition.MAX_NUMBER)
                    .mapToObj(col -> Position.of(2, col))
                    .toList();
            put(Pawn.class, pawnPositions);
        }
    };

    //체스보드를 만든다. -> 화이트 팀을 배치한 맵을 만든다 -> 검은색 팀을 배치한 맵을 만든다. -> 두 맵을 합친다

    public ChessBoard create() {
        Map<Position, Piece> positionPiece = new HashMap<>();
        INITIAL_PIECE_ARRANGEMENT.entrySet().stream()
                .map(entry -> mapPositionToPiece(entry.getKey(), entry.getValue()))
                .forEach(positionPiece::putAll);
        return new ChessBoard(positionPiece);
    }


    //TODO: 의미 있는 메서드명 생각해보기
    // 특정 기물의 위치를 참조하여 위치 기물 맵을 작성한다.
    private Map<Position, Piece> mapPositionToPiece(Class<? extends Piece> pieceType, List<Position> positions) {
        Map<Position, Piece> positionPiece = new HashMap<>();
        positions.forEach(position -> {
            positionPiece.put(position, createPieceInstance(pieceType, Team.WHITE));
//            positionPiece.put(position, createPieceInstance(pieceType, Team.BLACK));
        });
        return positionPiece;
    }

    //특정 타입의 기물을 만든다
    private Piece createPieceInstance(Class<? extends Piece> clazz, Team team) {
        try {
            return clazz.getDeclaredConstructor(Team.class)
                    .newInstance(team);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
