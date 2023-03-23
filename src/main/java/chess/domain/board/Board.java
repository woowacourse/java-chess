package chess.domain.board;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;

public class Board {

    private static final int MIN_DUPLICATE_COUNT = 2;
    private static final double SCORE_TO_SUBTRACT = 0.5;
    private static Map<Class<? extends Piece>, Score> scoreByPiece = Map.of(
            Queen.class, new Score(new BigDecimal("9.0")),
            Rook.class, new Score(new BigDecimal("5.0")),
            Bishop.class, new Score(new BigDecimal("3.0")),
            Knight.class, new Score(new BigDecimal("2.5")),
            Pawn.class, new Score(new BigDecimal("1.0")),
            King.class, new Score(new BigDecimal("0.0"))
    );

    private final Pieces pieces;
//    private final ScoreBySide scoreBySide;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPosition(final Position position) {
        return pieces.findPieceByPosition(position);
    }

    public void checkPieceMoveCondition(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        checkPieceMovable(targetPosition, sourcePiece);
        checkPath(targetPosition, sourcePiece);
        checkTargetPositionPieceSide(targetPosition, sourcePiece);
    }

    private void checkPieceMovable(final Position targetPosition, final Piece sourcePiece) {
        if (!sourcePiece.isMovable(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 해당 기물은 대상 위치로 이동할 수 없습니다.");
        }
    }

    private void checkPath(final Position targetPosition, final Piece sourcePiece) {
        final List<Position> paths = sourcePiece.getPaths(targetPosition);
        boolean isExistPieceOnPath = paths.stream().anyMatch(pieces::isPieceExistOnPosition);
        if (isExistPieceOnPath) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치까지의 경로에 말이 존재합니다.");
        }
    }

    private void checkTargetPositionPieceSide(final Position targetPosition, final Piece sourcePiece) {
        if (isExistPieceOnPosition(targetPosition)) {
            final Piece targetPiece = pieces.findPieceByPosition(targetPosition);
            checkSameSidePiece(sourcePiece, targetPiece);
        }
    }

    private boolean isExistPieceOnPosition(Position targetPosition) {
        return pieces.isPieceExistOnPosition(targetPosition);
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        final Piece sourcePiece = pieces.findPieceByPosition(sourcePosition);
        if (sourcePiece.isPawn()) {
            checkPieceDiagonalMove(sourcePosition, targetPosition);
            checkPieceLinearMove(sourcePosition, targetPosition);
        }
        takePieceOrOnlyMove(targetPosition, sourcePiece);
    }

    private void checkPieceDiagonalMove(Position sourcePosition, Position targetPosition) {
        Direction direction = sourcePosition.getDirectionTo(targetPosition);
        if (direction.isDiagonalMovable() && !isExistPieceOnPosition(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 대각선에 적팀 기물이 있을 때만 이동할 수 있습니다.");
        }
    }

    private void checkPieceLinearMove(Position sourcePosition, Position targetPosition) {
        Direction direction = sourcePosition.getDirectionTo(targetPosition);
        if (direction.isVerticalMovable() && isExistPieceOnPosition(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] Pawn은 직선 상의 도착 위치 기물이 적군이어도 이동할 수 없습니다.");
        }
    }

    private void takePieceOrOnlyMove(Position targetPosition, Piece sourcePiece) {
        if (isExistPieceOnPosition(targetPosition)) {
            takePieceMove(targetPosition, sourcePiece);
        }
        if (!isExistPieceOnPosition(targetPosition)) {
            move(targetPosition, sourcePiece);
        }
    }

    private void takePieceMove(Position targetPosition, Piece sourcePiece) {
        Piece oppositeTargetPiece = pieces.findPieceByPosition(targetPosition);
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.removePiece(oppositeTargetPiece);
        pieces.changePiece(sourcePiece, movedPiece);
    }

    private void move(Position targetPosition, Piece sourcePiece) {
        final Piece movedPiece = sourcePiece.move(targetPosition);
        pieces.changePiece(sourcePiece, movedPiece);
    }

    private void checkSameSidePiece(final Piece sourcePiece, final Piece targetPiece) {
        if (targetPiece.isSameSide(sourcePiece.getSide())) {
            throw new IllegalArgumentException("[ERROR] 타겟 위치에 아군 말이 존재합니다.");
        }
    }

    public Score calculateSideScore(Side side) {
        List<Piece> PiecesBySide = pieces.getPiecesBySide(side);
        Score piecesTotalScore = calculatePiecesTotalScore(PiecesBySide);
        Map<File, Integer> sameFileWhitePawnCount = pieces.getSameFilePawnCount(side);
        int duplicateWhitePawnCount = getDuplicateWhitePawnCount(sameFileWhitePawnCount);
        Score scoreToSubtract = new Score(new BigDecimal(duplicateWhitePawnCount * SCORE_TO_SUBTRACT));

        return piecesTotalScore.subtract(scoreToSubtract);
    }

    private int getDuplicateWhitePawnCount(Map<File, Integer> sameFileWhitePawnCount) {
        return sameFileWhitePawnCount.values().stream()
                .filter(count -> count >= MIN_DUPLICATE_COUNT)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Score calculatePiecesTotalScore(List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> scoreByPiece.get(piece.getClass()))
                .reduce(new Score(new BigDecimal("0.0")), Score::add);
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }
}
