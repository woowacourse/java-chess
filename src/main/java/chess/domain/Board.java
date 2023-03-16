package chess.domain;

import chess.domain.move.Move;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board() {
        Map<Position, Piece> initialArrangement = Map.ofEntries(
                Map.entry(new Position(File.A, Rank.ONE), Rook.from(true)),
                Map.entry(new Position(File.B, Rank.ONE), Knight.from(true)),
                Map.entry(new Position(File.C, Rank.ONE), Bishop.from(true)),
                Map.entry(new Position(File.D, Rank.ONE), Queen.from(true)),
                Map.entry(new Position(File.E, Rank.ONE), King.from(true)),
                Map.entry(new Position(File.F, Rank.ONE), Bishop.from(true)),
                Map.entry(new Position(File.G, Rank.ONE), Knight.from(true)),
                Map.entry(new Position(File.H, Rank.ONE), Rook.from(true)),
                Map.entry(new Position(File.A, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.B, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.C, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.D, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.E, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.F, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.G, Rank.TWO), Pawn.from(true)),
                Map.entry(new Position(File.H, Rank.TWO), Pawn.from(true)),
                // Black
                Map.entry(new Position(File.A, Rank.EIGHT), Rook.from(false)),
                Map.entry(new Position(File.B, Rank.EIGHT), Knight.from(false)),
                Map.entry(new Position(File.C, Rank.EIGHT), Bishop.from(false)),
                Map.entry(new Position(File.D, Rank.EIGHT), Queen.from(false)),
                Map.entry(new Position(File.E, Rank.EIGHT), King.from(false)),
                Map.entry(new Position(File.F, Rank.EIGHT), Bishop.from(false)),
                Map.entry(new Position(File.G, Rank.EIGHT), Knight.from(false)),
                Map.entry(new Position(File.H, Rank.EIGHT), Rook.from(false)),
                Map.entry(new Position(File.A, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.B, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.C, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.D, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.E, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.F, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.G, Rank.SEVEN), Pawn.from(false)),
                Map.entry(new Position(File.H, Rank.SEVEN), Pawn.from(false))
        );
        this.pieces = new HashMap<>(initialArrangement);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = findSourcePiece(source);
        boolean isAttack = checkIsAttack(sourcePiece, target);
        Move move = Move.of(source, target);

        checkPieceReachable(sourcePiece, isAttack, move);
        checkNotCrossOtherPiece(source, target, move);

        if (isAttack) {
            pieces.remove(target);
        }
        pieces.remove(source);
        pieces.put(target, sourcePiece.touch());
    }

    private Piece findSourcePiece(Position source) {
        Piece sourcePiece = pieces.get(source);
        if (sourcePiece == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
        return sourcePiece;
    }

    private boolean checkIsAttack(Piece sourcePiece, Position target) {
        Piece targetPiece = pieces.get(target);
        if (isEmpty(target)) {
            return false;
        }
        if (sourcePiece.hasSameColor(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
        return true;
    }

    private void checkPieceReachable(Piece sourcePiece, boolean isAttack, Move move) {
        if (!canMove(sourcePiece, move, isAttack)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private boolean canMove(Piece piece, Move move, boolean isAttack) {
        if (isAttack) {
            return piece.hasAttackMove(move);
        }
        return piece.hasMove(move);
    }

    private void checkNotCrossOtherPiece(Position source, Position target, Move move) {
        Move unitMove = move.getUnitMove();
        Position currentPosition = unitMove.findDestination(source);
        while (!currentPosition.equals(target)) {
            checkEmpty(currentPosition);
            currentPosition = unitMove.findDestination(source);
        }
    }

    private void checkEmpty(Position position) {
        if (isNotEmpty(position)) {
            throw new IllegalArgumentException("다른 기물을 지나칠 수 없습니다");
        }
    }

    private boolean isEmpty(Position position) {
        return !isNotEmpty(position);
    }

    private boolean isNotEmpty(Position position) {
        return pieces.get(position) != null;
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
