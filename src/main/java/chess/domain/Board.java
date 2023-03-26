package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.pawn.Pawn;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.PieceType.*;

public class Board {
    private final Map<Square, Piece> board;

    public Board() {
        this.board = BoardGenerator.init();
    }

    public void move(final Square src, final Square dst) {
        if (!canMove(src, dst)) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
        Piece srcPiece = board.getOrDefault(src, new Empty());
        if (srcPiece.getPieceType() == PAWN && srcPiece.isInitialPawn()) {
            srcPiece = new Pawn(srcPiece.getTeam());
        }
        board.put(dst, srcPiece);
        board.remove(src);
    }

    private boolean canMove(final Square src, final Square dst) {
        int fileInterval = File.calculate(src.getFile(), dst.getFile());
        int rankInterval = Rank.calculate(src.getRank(), dst.getRank());

        Piece srcPiece = board.getOrDefault(src, new Empty());
        Piece dstPiece = board.getOrDefault(dst, new Empty());
        srcPiece.validateMovement(fileInterval, rankInterval, dstPiece);

        if (srcPiece.getPieceType() == KNIGHT) {
            return true;
        }
        return canMoveNextSquare(src, fileInterval, rankInterval);
    }

    private boolean canMoveNextSquare(final Square src, final int fileInterval, final int rankInterval) {
        Square square = src;
        int fileMoveDirection = getMoveDirection(fileInterval);
        int rankMoveDirection = getMoveDirection(rankInterval);
        int interval = getMoveInterval(fileInterval, rankInterval);

        boolean notContainPiece = true;
        while (interval > 1 && notContainPiece) {
            square = square.next(fileMoveDirection, rankMoveDirection);
            notContainPiece = !board.containsKey(square);
            interval--;
        }
        return notContainPiece;
    }

    private int getMoveDirection(final int interval) {
        return Integer.compare(0, interval);
    }

    private int getMoveInterval(final int fileInterval, final int rankInterval) {
        return Math.max(Math.abs(fileInterval), Math.abs(rankInterval));
    }

    public boolean isSameTeam(final Square src, final Team team) {
        Piece piece = findPieceBy(src);
        return piece.getTeam() == team;
    }

    private Piece findPieceBy(final Square square) {
        if (!board.containsKey(square)) {
            throw new IllegalArgumentException("말이 있는 위치를 입력해주세요.");
        }
        return board.get(square);
    }

    public boolean isKingDead(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.getTeam() == team)
                .noneMatch(piece -> piece.getPieceType() == KING);
    }

    public double calculateTeamScore(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.getTeam() == team)
                .map(piece -> piece.getPieceType().getScore())
                .reduce(0.0-0.5*calculateSameLinePawn(team), Double::sum);
    }

    public int calculateSameLinePawn(final Team team) {
        return calculatePawnNumber(team).values()
                .stream()
                .filter(count -> count >= 2)
                .reduce(0, Integer::sum);
    }

    private Map<File, Integer> calculatePawnNumber(final Team team) {
        Map<File, Integer> numberOfPawn = new HashMap<>();
        board.keySet()
                .stream()
                .filter(square -> PAWN == board.get(square).getPieceType())
                .filter(square -> team == board.get(square).getTeam())
                .forEach(square -> numberOfPawn.put(square.getFile(), numberOfPawn.getOrDefault(square.getFile(), 0) + 1));
        return numberOfPawn;
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
