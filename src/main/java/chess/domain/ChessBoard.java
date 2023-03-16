package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final int FIRST_INDEX = 1;
    private static final int RANK_SIZE = 8;
    private static final int POSITION_RANK_INDEX = 0;
    private static final int POSITION_FILE_INDEX = 1;
    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PAWN_PATH_ERROR_MESSAGE = "폰은 공격 할 때만 대각선으로 이동할 수 있습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";

    private final Map<Position, Piece> piecesByPosition = new HashMap<>();

    public ChessBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        insertPiecesByColor(TeamColor.WHITE);
        insertPiecesByColor(TeamColor.BLACK);
    }

    private void insertPiecesByColor(TeamColor color) {
        piecesByPosition.put(new Position(color.startingRank(), 1), new Rook(color));
        piecesByPosition.put(new Position(color.startingRank(), 2), new Knight(color));
        piecesByPosition.put(new Position(color.startingRank(), 3), new Bishop(color));
        piecesByPosition.put(new Position(color.startingRank(), 4), new King(color));
        piecesByPosition.put(new Position(color.startingRank(), 5), new Queen(color));
        piecesByPosition.put(new Position(color.startingRank(), 6), new Bishop(color));
        piecesByPosition.put(new Position(color.startingRank(), 7), new Knight(color));
        piecesByPosition.put(new Position(color.startingRank(), 8), new Rook(color));
        IntStream.range(FIRST_INDEX, RANK_SIZE + 1)
                .forEach(file -> piecesByPosition.put(new Position(color.startingPawnRank(), file),
                        new Pawn(color)));
    }

    public void move(List<Integer> sourcePosition, List<Integer> destPosition) {
        Position source = new Position(sourcePosition.get(POSITION_RANK_INDEX),
                sourcePosition.get(POSITION_FILE_INDEX));
        Position dest = new Position(destPosition.get(POSITION_RANK_INDEX), destPosition.get(POSITION_FILE_INDEX));

        validateSourcePosition(source);

        Piece piece = piecesByPosition.get(source);
        List<Path> movablePaths = piece.findMovablePaths(source);

        for (Path path : movablePaths) {
            if (moveWhenPossible(source, dest, piece, path)) {
                return;
            }
        }

        throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    private boolean moveWhenPossible(final Position source, final Position dest, final Piece piece, final Path path) {
        if (path.hasPosition(dest)) {
            checkObstacleInPath(dest, path);
            validatePawnAttack(piece, source, dest);

            // TODO: 2023/03/16 도착지에 자신의 팀의 말이 있다면 예외
            piecesByPosition.put(dest, piece);
            piecesByPosition.remove(source);
            return true;
        }
        return false;
    }

    private void validatePawnAttack(final Piece piece, final Position source, final Position dest) {
        if (!piece.isPawn()) {
            return;
        }
        Pawn pawn = (Pawn) piece;
        if (pawn.isAttack(source, dest) && !isOtherPieceInDestination(dest)) { // TODO: 2023/03/16 공격 대상이 상대팀 말이어야 한다
            throw new IllegalArgumentException(WRONG_PAWN_PATH_ERROR_MESSAGE);
        }
    }

    private boolean isOtherPieceInDestination(final Position dest) {
        return piecesByPosition.containsKey(dest);
    }

    private void checkObstacleInPath(final Position dest, final Path path) {
        List<Position> positions = path.positions();
        for (int index = 0; index < path.findPositionIndex(dest); index++) {
            checkObstacleAtIndex(positions, index);
        }
    }

    private void checkObstacleAtIndex(final List<Position> positions, final int index) {
        if (piecesByPosition.containsKey(positions.get(index))) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
    }

    private void validateSourcePosition(final Position source) {
        if (piecesByPosition.containsKey(source)) { // TODO: 2023/03/16 시작 위치의 말이, 상대팀의 말이라면 예외
            return;
        }
        throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
