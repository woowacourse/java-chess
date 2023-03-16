package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final int FIRST_INDEX = 1;
    private static final int RANK_SIZE = 8;
    private static final String WRONG_START_ERROR_MESSAGE = "시작 위치에 말이 없습니다.";
    private static final String OBSTACLE_IN_PATH_ERROR_MESSAGE = "경로에 다른 말이 있어서 이동할 수 없습니다.";
    private static final String WRONG_PAWN_PATH_ERROR_MESSAGE = "폰은 공격 할 때만 대각선으로 이동할 수 있습니다.";
    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private static final String WRONG_PIECE_COLOR_ERROR_MESSAGE = "자신 팀의 말만 이동시킬 수 있습니다.";
    private static final String WRONG_ATTACK_TARGET_ERROR_MESSAGE = "상대 팀의 말만 공격할 수 있습니다.";

    private final Map<Position, Piece> piecesByPosition = new HashMap<>();

    public ChessBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        insertPiecesByColor(Camp.WHITE);
        insertPiecesByColor(Camp.BLACK);
    }

    private void insertPiecesByColor(Camp camp) {
        piecesByPosition.put(Position.of(2, camp.startingRank()), new Knight(camp));
        piecesByPosition.put(Position.of(1, camp.startingRank()), new Rook(camp));
        piecesByPosition.put(Position.of(3, camp.startingRank()), new Bishop(camp));
        piecesByPosition.put(Position.of(4, camp.startingRank()), new King(camp));
        piecesByPosition.put(Position.of(5, camp.startingRank()), new Queen(camp));
        piecesByPosition.put(Position.of(6, camp.startingRank()), new Bishop(camp));
        piecesByPosition.put(Position.of(7, camp.startingRank()), new Knight(camp));
        piecesByPosition.put(Position.of(8, camp.startingRank()), new Rook(camp));
        IntStream.range(FIRST_INDEX, RANK_SIZE + 1)
                .forEach(file -> piecesByPosition.put(Position.of(file, camp.startingPawnRank()),
                        new Pawn(camp)));
    }

    public void move(List<Integer> sourceCoords, List<Integer> destinationCoords, final Camp camp) {
        Position source = Position.from(sourceCoords);
        Position destination = Position.from(destinationCoords);

        Piece piece = findPieceAtSourcePosition(source, camp);
        List<Path> allPaths = piece.findAllPaths(source);
        for (Path path : allPaths) {
            if (moveWhenPossible(path, source, destination, piece, camp)) {
                return;
            }
        }
        throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
    }

    private Piece findPieceAtSourcePosition(final Position source, final Camp camp) {
        validatePieceAtSourcePosition(source);
        Piece piece = piecesByPosition.get(source);
        validatePieceColor(piece, camp);
        return piece;
    }

    private void validatePieceAtSourcePosition(final Position start) {
        if (piecesByPosition.containsKey(start)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_START_ERROR_MESSAGE);
    }

    private void validatePieceColor(final Piece piece, final Camp camp) {
        if (piece.isSameColor(camp)) {
            return;
        }
        throw new IllegalArgumentException(WRONG_PIECE_COLOR_ERROR_MESSAGE);
    }

    private boolean moveWhenPossible(final Path path, final Position source, final Position destination,
                                     final Piece movingPiece, final Camp camp) {
        if (path.hasPosition(destination)) {
            validatePath(path, source, destination, movingPiece, camp);
            piecesByPosition.put(destination, movingPiece);
            piecesByPosition.remove(source);
            return true;
        }
        return false;
    }

    private void validatePath(final Path path, final Position source, final Position destination,
                              final Piece movingPeace, final Camp camp) {
        checkObstacleInPath(path, destination);
        checkPawnAttack(source, destination, movingPeace);
        checkObstacleInDestination(destination, camp);
    }

    private void checkObstacleInDestination(final Position destination, final Camp camp) {
        if (!piecesByPosition.containsKey(destination)) {
            return;
        }
        Piece pieceAtDestination = piecesByPosition.get(destination);
        if (pieceAtDestination.isSameColor(camp)) {
            throw new IllegalArgumentException(WRONG_ATTACK_TARGET_ERROR_MESSAGE);
        }
    }

    private void checkPawnAttack(final Position source, final Position destination, final Piece movingPiece) {
        if (!movingPiece.isPawn()) {
            return;
        }
        Pawn pawn = (Pawn) movingPiece;
        if (pawn.isAttack(source, destination) && isEmptyPosition(destination)) {
            throw new IllegalArgumentException(WRONG_PAWN_PATH_ERROR_MESSAGE);
        }
    }

    private boolean isEmptyPosition(final Position destination) {
        return !piecesByPosition.containsKey(destination);
    }

    private void checkObstacleInPath(final Path path, final Position destination) {
        List<Position> positions = path.positions();
        for (int index = 0; index < path.findPositionIndex(destination); index++) {
            checkObstacleAtIndex(positions, index);
        }
    }

    private void checkObstacleAtIndex(final List<Position> positions, final int index) {
        if (piecesByPosition.containsKey(positions.get(index))) {
            throw new IllegalArgumentException(OBSTACLE_IN_PATH_ERROR_MESSAGE);
        }
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
