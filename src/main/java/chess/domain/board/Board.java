package chess.domain.board;

import chess.domain.GameResult;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static chess.domain.piece.Pawn.PAWN_HALF_SCORE;
import static chess.domain.piece.Pawn.PAWN_SCORE;
import static chess.util.NullValidator.validateNull;

public class Board {
    public static final int ONLY_ONE_PAWN_IN_XPOINT = 1;

    private final Map<Position, Piece> board = BoardFactory.getBoard();

    public Piece findPiece(Position sourcePosition, PieceColor team) {
        validateNull(sourcePosition);

        Piece sourcePiece = board.get(sourcePosition);
        if (sourcePiece.isNone()) {
            throw new IllegalArgumentException("움직일 수 있는 체스말이 없습니다.");
        }
        if (sourcePiece.isDifferentColor(team)) {
            throw new IllegalArgumentException("다른 색의 말을 움직일 수 없습니다.");
        }
        return board.get(sourcePosition);
    }

    public void checkPath(Piece piece, Position targetPosition) {
        if (piece instanceof Pawn) {
            checkPawnPath(piece, targetPosition);
            return;
        }
        List<Position> path = piece.getPathTo(targetPosition);
        if (havePieceBeforeTargetPosition(path)) {
            throw new IllegalArgumentException("이동 경로 중에 다른 체스말이 있기 때문에 지정한 위치로 이동할 수 없습니다.");
        }
        if (cannotMoveToTargetPosition(piece, targetPosition)) {
            throw new IllegalArgumentException("지정한 위치에 같은 색의 체스말이 있기 때문에 이동할 수 없습니다.");
        }
    }

    private boolean havePieceBeforeTargetPosition(List<Position> path) {
        path.remove(path.size() - 1);
        return !path.stream()
                .map(board::get)
                .allMatch(Piece::isNone);
    }

    private boolean cannotMoveToTargetPosition(Piece piece, Position targetPosition) {
        Piece targetPiece = board.get(targetPosition);
        if (!targetPiece.isNone()) {
            return piece.isSameColor(targetPiece);
        }
        return false;
    }

    private void checkPawnPath(Piece piece, Position targetPosition) {
        Pawn pawn = (Pawn) piece;
        List<Position> path = pawn.getPathTo(targetPosition);
        if ((pawn.getDirection(targetPosition).isSouth() || pawn.getDirection(targetPosition).isNorth()) && havePieceIn(path)) {
            throw new IllegalArgumentException("이동 경로 중에 다른 체스말이 있기 때문에 지정한 위치로 이동할 수 없습니다.");
        } else if (!pawn.getDirection(targetPosition).isSouth() && !pawn.getDirection(targetPosition).isNorth() && cannotMovePawnToTargetPosition(pawn, targetPosition)) {
            throw new IllegalArgumentException("지정한 위치에 다른 색의 체스말이 없기 때문에 이동할 수 없습니다.");
        }
    }

    private boolean havePieceIn(List<Position> path) {
        return !path.stream()
                .map(board::get)
                .allMatch(Piece::isNone);
    }

    private boolean cannotMovePawnToTargetPosition(Pawn pawn, Position targetPosition) {
        Piece targetPiece = board.get(targetPosition);
        if (!targetPiece.isNone()) {
            return pawn.isSameColor(targetPiece);
        }
        return true;
    }

    public void move(Piece piece, Position targetPosition) {
        board.put(piece.getPosition(), new EmptyPiece(PieceColor.NONE, piece.getPosition()));
        board.put(targetPosition, piece);
        piece.changeTo(targetPosition);
    }

    public boolean isBlackKingKilled() {
        return board.values().stream()
                .map(Piece::getName)
                .noneMatch("K"::equals);
    }

    public boolean isWhiteKingKilled() {
        return board.values().stream()
                .map(Piece::getName)
                .noneMatch("k"::equals);
    }

    private double getAliveBlackPieceScoreSum() {
        double scoreSum = board.values().stream()
                .filter(piece -> !(piece instanceof Pawn) && piece.isBlack())
                .mapToDouble(Piece::getScore)
                .sum();

        return scoreSum + getAliveBlackPawnScoreSum();
    }

    private double getAliveBlackPawnScoreSum() {
        double scoreSum = 0;

        for (char x = 'a'; x <= 'h'; x++) {
            int pawnInXPointCount = 0;
            for (char y = '1'; y <= '8'; y++) {
                Piece piece = board.get(Positions.of(x, y));
                if (piece instanceof Pawn && piece.isBlack()) {
                    pawnInXPointCount++;
                }
            }
            scoreSum += getPawnScoreSumInXPoint(pawnInXPointCount);
        }

        return scoreSum;
    }

    private double getAliveWhitePieceScoreSum() {
        double scoreSum = board.values().stream()
                .filter(piece -> !(piece instanceof Pawn) && piece.isWhite())
                .mapToDouble(Piece::getScore)
                .sum();

        return scoreSum + getAliveWhitePawnScoreSum();
    }

    private double getAliveWhitePawnScoreSum() {
        double scoreSum = 0;

        for (char x = 'a'; x <= 'h'; x++) {
            int pawnInXPointCount = 0;
            for (char y = '1'; y <= '8'; y++) {
                Piece piece = board.get(Positions.of(x, y));
                if (piece instanceof Pawn && piece.isWhite()) {
                    pawnInXPointCount++;
                }
            }
            scoreSum += getPawnScoreSumInXPoint(pawnInXPointCount);
        }

        return scoreSum;
    }

    private double getPawnScoreSumInXPoint(int pawnInXPointCount) {
        double scoreSum = 0;

        if (ONLY_ONE_PAWN_IN_XPOINT < pawnInXPointCount) {
            scoreSum += pawnInXPointCount * PAWN_HALF_SCORE;
        } else {
            scoreSum += pawnInXPointCount * PAWN_SCORE;
        }

        return scoreSum;
    }

    public GameResult createGameResult() {
        return new GameResult(isBlackKingKilled(), isWhiteKingKilled(), getAliveBlackPieceScoreSum(),
                              getAliveWhitePieceScoreSum());
    }

    public Map<Position, Piece> get() {
        return Collections.unmodifiableMap(board);
    }
}
