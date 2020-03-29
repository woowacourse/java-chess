package chess.domain.board;

import chess.domain.GameResult;
import chess.domain.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.piece.Pawn.PAWN_HALF_SCORE;
import static chess.domain.piece.Pawn.PAWN_SCORE;
import static chess.util.NullValidator.validateNull;

public class Board {
    public static final int ONLY_ONE_PAWN_IN_XPOINT = 1;
    private final Map<Position, Piece> board = createBoard();

    private Map<Position, Piece> createBoard() {
        Map<Position, Piece> cells = createEmptyBoard();
        setBlackPieces(cells);
        setWhitePieces(cells);
        return cells;
    }

    private Map<Position, Piece> createEmptyBoard() {
        Map<Position, Piece> board = new HashMap<>();
        for (Xpoint xPoint : Xpoint.values()) {
            for (Ypoint yPoint : Ypoint.values()) {
                EmptyPiece emptyPiece = new EmptyPiece(PieceColor.NONE,
                                                       Positions.of(xPoint.getName() + yPoint.getName()));
                board.put(Positions.of(xPoint.getValue(), yPoint.getValue()), emptyPiece);
            }
        }
        return board;
    }

    private void setBlackPieces(Map<Position, Piece> cells) {
        cells.put(Positions.of("a8"), new Rook(PieceColor.BLACK, Positions.of("a8")));
        cells.put(Positions.of("b8"), new Knight(PieceColor.BLACK, Positions.of("b8")));
        cells.put(Positions.of("c8"), new Bishop(PieceColor.BLACK, Positions.of("c8")));
        cells.put(Positions.of("d8"), new Queen(PieceColor.BLACK, Positions.of("d8")));
        cells.put(Positions.of("e8"), new King(PieceColor.BLACK, Positions.of("e8")));
        cells.put(Positions.of("f8"), new Bishop(PieceColor.BLACK, Positions.of("f8")));
        cells.put(Positions.of("g8"), new Knight(PieceColor.BLACK, Positions.of("g8")));
        cells.put(Positions.of("h8"), new Rook(PieceColor.BLACK, Positions.of("h8")));

        for (char xPoint = 'a'; xPoint <= 'h'; xPoint++) {
            cells.put(Positions.of(xPoint, '7'), new Pawn(PieceColor.BLACK, Positions.of(xPoint, '7')));
        }
    }

    private void setWhitePieces(Map<Position, Piece> cells) {
        for (char xPoint = 'a'; xPoint <= 'h'; xPoint++) {
            cells.put(Positions.of(xPoint, '2'), new Pawn(PieceColor.WHITE, Positions.of(xPoint, '2')));
        }
        cells.put(Positions.of("a1"), new Rook(PieceColor.WHITE, Positions.of("a1")));
        cells.put(Positions.of("b1"), new Knight(PieceColor.WHITE, Positions.of("b1")));
        cells.put(Positions.of("c1"), new Bishop(PieceColor.WHITE, Positions.of("c1")));
        cells.put(Positions.of("d1"), new Queen(PieceColor.WHITE, Positions.of("d1")));
        cells.put(Positions.of("e1"), new King(PieceColor.WHITE, Positions.of("e1")));
        cells.put(Positions.of("f1"), new Bishop(PieceColor.WHITE, Positions.of("f1")));
        cells.put(Positions.of("g1"), new Knight(PieceColor.WHITE, Positions.of("g1")));
        cells.put(Positions.of("h1"), new Rook(PieceColor.WHITE, Positions.of("h1")));
    }

    public Piece findPiece(Position sourcePosition) {
        validateNull(sourcePosition);
        if (board.get(sourcePosition).isNone()) {
            throw new IllegalArgumentException("움직일 수 있는 체스말이 없습니다.");
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
