package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.exception.TeamNotMatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Game {
    private final Board board;
    private Team turn;

    public Game() {
        board = new Board();
        turn = Team.WHITE;
    }

    public void move(Square source, Square target) {
        if (isNotCurrentTurn(source) || board.isEmptyPiece(source)) {
            throw new TeamNotMatchException(turn);
        }
        board.move(source, target);
        turn = turn.nextTurn(turn);
    }

    private boolean isNotCurrentTurn(Square square) {
        return board.isSquarePieceNotCurrentTurn(square, turn);
    }

    public boolean isGameEnd() {
        return board.haveOneKing();
    }

    public Team calculateWinner(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return Team.WHITE;
        }
        if (blackScore > whiteScore) {
            return Team.BLACK;
        }
        return Team.EMPTY;
    }

    public double calculateWhiteScore() {
        Map<Piece, Square> whitePieces = board.getWhitePieces();
        return calculateScore(whitePieces);
    }

    public double calculateBlackScore() {
        Map<Piece, Square> blackPieces = board.getBlackPieces();
        return calculateScore(blackPieces);
    }

    private double calculateScore(Map<Piece, Square> pieces) {
        double score = 0;
        Map<Piece, Integer> pawnPiece = getPawnPieces(pieces);
        for (Entry<Piece, Square> entry : pieces.entrySet()) {
            PieceType pieceType = entry.getKey().getPieceType();
            score += pieceType.getScore();
            score -= pawnDuplicateMinusScore(pawnPiece, entry.getValue(), pieceType);
        }
        return score;
    }

    private double pawnDuplicateMinusScore(Map<Piece, Integer> pawnPiece, Square square, PieceType pieceType) {
        if (pieceType == PieceType.PAWN) {
            int fileToInt = square.getFileToInt();
            long pawnCount = pawnPiece.values().stream()
                    .filter(file -> file.equals(fileToInt)).count();
            return pawnMinusScore(pawnCount);
        }
        return 0;
    }

    private double pawnMinusScore(long pawnCount) {
        if (pawnCount > 1) {
            return 0.5;
        }
        return 0;
    }

    private static Map<Piece, Integer> getPawnPieces(Map<Piece, Square> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getKey().isSamePieceType(PieceType.PAWN))
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getValue().getFileToInt()
                ));
    }

    public List<Piece> getPieces() {
        return board.getPieces();
    }
}
