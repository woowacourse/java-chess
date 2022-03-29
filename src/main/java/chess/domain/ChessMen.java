package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessMen implements Iterable<ChessPiece> {
    private static final String NO_CHESS_PIECE_EXCEPTION = "[ERROR] 움직이려는 위치에 체스피스가 없습니다.";
    private final List<ChessPiece> chessPieces;

    public ChessMen(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public static ChessMen create(Team team) {
        List<ChessPiece> chessPieces = new ArrayList<>();
        createPawn(chessPieces, team);
        createKnight(chessPieces, team);
        createBishop(chessPieces, team);
        createRook(chessPieces, team);
        createQueen(chessPieces, team);
        createKing(chessPieces, team);
        return new ChessMen(chessPieces);
    }

    private static void createPawn(List<ChessPiece> chessPieces, Team team) {
        chessPieces.addAll(Pawn.create(team));
    }

    private static void createKnight(List<ChessPiece> chessPieces, Team team) {
        chessPieces.addAll(Knight.create(team));
    }

    private static void createBishop(List<ChessPiece> chessPieces, Team team) {
        chessPieces.addAll(Bishop.create(team));
    }

    private static void createRook(List<ChessPiece> chessPieces, Team team) {
        chessPieces.addAll(Rook.create(team));
    }

    private static void createQueen(List<ChessPiece> chessPieces, Team team) {
        chessPieces.addAll(Queen.create(team));
    }

    private static void createKing(List<ChessPiece> chessPieces, Team team) {
        chessPieces.addAll(King.create(team));
    }

    public ChessPiece getChessPieceAt(ChessBoardPosition position) {
        return chessPieces.stream()
                .filter(it -> it.isSamePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_CHESS_PIECE_EXCEPTION));
    }

    public boolean existChessPieceAt(ChessBoardPosition position) {
        return chessPieces.stream()
                .anyMatch(it -> it.isSamePosition(position));
    }

    public void removeChessPieceAt(ChessBoardPosition position) {
        chessPieces.removeIf(chessPiece -> chessPiece.isSamePosition(position));
    }

    public boolean isKingDead() {
        return chessPieces.stream()
                .noneMatch(King.class::isInstance);
    }

    public double calculateScore() {
        double sum = chessPieces.stream()
                .mapToDouble(ChessPiece::getScore)
                .sum();
        return sum - countSameRowPawn() * 0.5;
    }

    private int countSameRowPawn() {
        List<Character> pawnColumns = collectPawnColumns();
        return IntStream.rangeClosed('a', 'h')
                .boxed()
                .map(it -> Collections.frequency(pawnColumns, (char)it.intValue()))
                .collect(Collectors.toList())
                .stream()
                .filter(it -> it > 1)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Character> collectPawnColumns() {
        return chessPieces.stream()
                .filter(Pawn.class::isInstance)
                .map(it -> it.getPosition().getColumn())
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<ChessPiece> iterator() {
        return chessPieces.iterator();
    }
}
