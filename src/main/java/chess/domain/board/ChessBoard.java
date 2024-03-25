package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.vo.Score;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {
    private static final int SINGLE_KING = 1;
    private static final int DOUBLE_PAWN = 2;
    private static final Score HALF_PAWN_SCORE = new Score(0.5);

    private final Map<Position, Piece> chessBoard = new LinkedHashMap<>();
    private Color currentTurnTeamColor;

    public ChessBoard() {
        initializeBlackPieces();
        initializeWhitePieces();
        currentTurnTeamColor = Color.WHITE;
    }

    public void move(Position source, Position target) {
        if (!chessBoard.containsKey(source)) {
            throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
        }
        Piece sourcePiece = chessBoard.get(source);
        validateTurn(sourcePiece);

        if (!canMove(sourcePiece, source, target)) {
            throw new IllegalArgumentException("올바르지 않은 이동입니다.");
        }
        updateChessBoard(source, target, sourcePiece);
    }

    public boolean isKingCaptured() {
        int kingCount = Math.toIntExact(chessBoard.values().stream()
                .filter(Piece::isKing)
                .count());
        return kingCount == SINGLE_KING;
    }

    public Color findWinnerByKing() {
        return chessBoard.values().stream().filter(Piece::isKing)
                .findFirst()
                .map(Piece::getColor)
                .orElseThrow(() -> new UnsupportedOperationException("King이 존재하지 않습니다."));
    }

    public Score calculateScore(Color color) {
        double scoreValue = chessBoard.values().stream().filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore).sum();
        Score score = new Score(scoreValue);
        for (File file : File.values()) {
            double verticalPawnCount = getVerticalPawnCount(file, color);
            score = applyHalfPawnScore(score, verticalPawnCount);
        }
        return score;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    private void validateTurn(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(currentTurnTeamColor)) {
            throw new IllegalArgumentException("해당 팀의 턴이 아닙니다.");
        }
    }

    private boolean canMove(Piece sourcePiece, Position source, Position target) {
        Piece targetPiece = chessBoard.get(target);
        Color targetPieceColor = Optional.ofNullable(targetPiece)
                .map(Piece::getColor).orElse(Color.NONE);

        if (sourcePiece.canMove(source, target, targetPieceColor)) {
            return sourcePiece.searchPath(source, target).stream().noneMatch(chessBoard::containsKey);
        }
        return false;
    }

    private void updateChessBoard(Position source, Position target, Piece sourcePiece) {
        chessBoard.put(target, sourcePiece);
        chessBoard.remove(source);
        currentTurnTeamColor = currentTurnTeamColor.convertTurn();
    }

    private double getVerticalPawnCount(File file, Color color) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(file, rank))
                .filter(position -> {
                    Piece piece = chessBoard.get(position);
                    return piece != null && piece.isPawn() && piece.isSameColor(color);
                })
                .count();
    }

    private Score applyHalfPawnScore(Score score, double pawnCount) {
        if (pawnCount >= DOUBLE_PAWN) {
            score = score.diminishScore(HALF_PAWN_SCORE, pawnCount);
        }
        return score;
    }

    private void initializeBlackPieces() {
        initializeEdgeRank(Rank.EIGHT, Color.BLACK);
        initializePawnRank(Rank.SEVEN, Color.BLACK);
    }

    private void initializeWhitePieces() {
        initializePawnRank(Rank.TWO, Color.WHITE);
        initializeEdgeRank(Rank.ONE, Color.WHITE);
    }

    private void initializePawnRank(Rank rank, Color color) {
        for (File file : File.values()) {
            chessBoard.put(Position.of(file, rank), new Pawn(color));
        }
    }

    private void initializeEdgeRank(Rank rank, Color color) {
        chessBoard.put(Position.of(File.A, rank), new Rook(color));
        chessBoard.put(Position.of(File.B, rank), new Knight(color));
        chessBoard.put(Position.of(File.C, rank), new Bishop(color));
        chessBoard.put(Position.of(File.D, rank), new Queen(color));
        chessBoard.put(Position.of(File.E, rank), new King(color));
        chessBoard.put(Position.of(File.F, rank), new Bishop(color));
        chessBoard.put(Position.of(File.G, rank), new Knight(color));
        chessBoard.put(Position.of(File.H, rank), new Rook(color));
    }
}
