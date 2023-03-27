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

    public Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Piece move(final Square source, final Square destination) {
        if (!canMove(source, destination)) {
            throw new IllegalArgumentException("이동 경로에 말이 존재합니다.");
        }
        Piece sourcePiece = board.getOrDefault(source, new Empty());
        if (sourcePiece.getPieceType() == PAWN && sourcePiece.isInitialPawn()) {
            sourcePiece = new Pawn(sourcePiece.getTeam());
        }
        board.put(destination, sourcePiece);
        board.remove(source);

        return sourcePiece;
    }

    private boolean canMove(final Square source, final Square destination) {
        int fileInterval = File.calculate(source.getFile(), destination.getFile());
        int rankInterval = Rank.calculate(source.getRank(), destination.getRank());

        Piece sourcePiece = board.getOrDefault(source, new Empty());
        Piece destinationPiece = board.getOrDefault(destination, new Empty());
        sourcePiece.validateMovement(fileInterval, rankInterval, destinationPiece);

        if (sourcePiece.getPieceType() == KNIGHT) {
            return true;
        }
        return canMoveNextSquare(source, fileInterval, rankInterval);
    }

    private boolean canMoveNextSquare(final Square source, final int fileInterval, final int rankInterval) {
        Square square = source;
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

    public boolean isSameTeam(final Square source, final Team team) {
        Piece piece = findPieceBy(source);
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
                .reduce(0.0 - 0.5 * calculateSameLinePawn(team), Double::sum);
    }

    private int calculateSameLinePawn(final Team team) {
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
