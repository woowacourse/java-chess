package domain;

import domain.piece.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private static final Map<File, Piece> BLACK_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Team.BLACK), File.B, new Knight(Team.BLACK),
            File.C, new Bishop(Team.BLACK), File.D, new Queen(Team.BLACK),
            File.E, new King(Team.BLACK), File.F, new Bishop(Team.BLACK),
            File.G, new Knight(Team.BLACK), File.H, new Rook(Team.BLACK)
    );
    private static final Map<File, Piece> WHITE_PIECE_TYPE_ORDERS = Map.of(
            File.A, new Rook(Team.WHITE), File.B, new Knight(Team.WHITE),
            File.C, new Bishop(Team.WHITE), File.D, new Queen(Team.WHITE),
            File.E, new King(Team.WHITE), File.F, new Bishop(Team.WHITE),
            File.G, new Knight(Team.WHITE), File.H, new Rook(Team.WHITE)
    );

    private final Map<Square, Piece> pieces;
    private Team team;

    public ChessBoard() {
        this.pieces = new HashMap<>();
    }

    private ChessBoard(final Map<Square, Piece> pieces) {
        this.pieces = pieces;
        this.team = Team.WHITE;
    }

    public static ChessBoard create() {
        final Map<Square, Piece> chessTable = new HashMap<>();

        for (final File file : File.values()) {
            chessTable.put(new Square(file, Rank.SEVEN), new Pawn(Team.BLACK));
            chessTable.put(new Square(file, Rank.TWO), new Pawn(Team.WHITE));
            chessTable.put(new Square(file, Rank.EIGHT), BLACK_PIECE_TYPE_ORDERS.get(file));
            chessTable.put(new Square(file, Rank.ONE), WHITE_PIECE_TYPE_ORDERS.get(file));
        }

        return new ChessBoard(chessTable);
    }

    public void move(final Square source, final Square target) {
        validateEmptySource(source);

        final Piece sourcePiece = pieces.get(source);

        validateTeam(sourcePiece);

        if (pieces.containsKey(target)) {
            validateAttack(source, target, sourcePiece);
        } else {
            validateMove(source, target, sourcePiece);
        }

        pieces.put(target, sourcePiece);
        pieces.remove(source);
        team = team.turn();
    }

    private void validateEmptySource(final Square source) {
        if (!pieces.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateTeam(final Piece sourcePiece) {
        if (sourcePiece.isOppositeTeam(team)) {
            throw new IllegalArgumentException("자기 말이 아닙니다.");
        }
    }

    private void validateAttack(final Square source, final Square target, final Piece sourcePiece) {
        final Piece targetPiece = pieces.get(target);
        if (targetPiece.isSameTeam(sourcePiece)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다.");
        }
        if (sourcePiece.canNotAttack(source, target, pieces)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다.");
        }
    }

    private void validateMove(final Square source, final Square target, final Piece sourcePiece) {
        if (sourcePiece.canNotMove(source, target, pieces)) {
            throw new IllegalArgumentException("갈 수 없는 경로입니다.");
        }
    }

    public Map<Square, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
