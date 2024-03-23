package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardCreator {
    private static final Map<Class<? extends Piece>, List<Position>> INITIAL_PIECE_ARRANGEMENT = new HashMap<>() {
        {
            List<Position> rookPositions = List.of(new Position(File.A, Rank.EIGHT), new Position(File.H, Rank.EIGHT));
            put(Rook.class, rookPositions);

            List<Position> knightPositions = List.of(new Position(File.B, Rank.EIGHT),
                    new Position(File.G, Rank.EIGHT));
            put(Knight.class, knightPositions);

            List<Position> bishopPositions = List.of(new Position(File.C, Rank.EIGHT),
                    new Position(File.F, Rank.EIGHT));
            put(Bishop.class, bishopPositions);

            List<Position> queenPositions = List.of(new Position(File.D, Rank.EIGHT));
            put(Queen.class, queenPositions);

            List<Position> kingPositions = List.of(new Position(File.E, Rank.EIGHT));
            put(King.class, kingPositions);

            List<Position> pawnPositions = Arrays.stream(File.values())
                    .map(file -> new Position(file, Rank.SEVEN))
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
            positionPiece.put(position.calculateVerticalReversedPosition(), createPieceInstance(pieceType, Team.WHITE));
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
