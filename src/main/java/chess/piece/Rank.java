package chess.piece;

import chess.Turn;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class Rank {

    private final List<Piece> rank;

    public Rank(List<Piece> rank) {
        this.rank = rank;
    }

    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        Piece sourcePiece = rank.get(source.getRight());
        if (isExistPiece(target) && isPawn(sourcePiece, source, target)) {
            return true;
        }
        return sourcePiece.isMovable(source, target);
    }

    public boolean isExistPiece(Pair<Integer, Integer> position) {
        return !rank.get(position.getRight()).isSameType(Type.BLANK);
    }

    private boolean isPawn(Piece sourcePiece, Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (!sourcePiece.isSameType(Type.PAWN)) {
            return false;
        }
        Pawn pawn = (Pawn) sourcePiece;
        return pawn.isDiagonal(source, target);
    }

    public boolean isKing(Pair<Integer, Integer> position) {
        return rank.get(position.getRight()).isSameType(Type.KING);
    }

    public boolean isMovableColor(Turn turn, Pair<Integer, Integer> position) {
        return turn.isRightTurn(rank.get(position.getRight()).getColor());
    }

    public boolean isSameColor(int index, Color color) {
        return rank.get(index).isColor(color);
    }

    public boolean isSameType(int index, Type type) {
        return rank.get(index).isSameType(type);
    }

    public void removeLocationPiece(Pair<Integer, Integer> source) {
        rank.set(source.getRight(), new Blank());
    }

    public void assignLocationPiece(Pair<Integer, Integer> source, Pair<Integer, Integer> target, Rank sourceRank) {
        rank.set(target.getRight(), sourceRank.rank.get(source.getRight()));
    }

    public List<Pair<Integer, Integer>> findBetweenTwoPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return rank.get(source.getRight())
                .computeBetweenTwoPosition(source, target);
    }

    public double score(Color color) {
        return rank.stream()
                .filter(piece -> piece.isColor(color))
                .mapToDouble(piece -> piece.getType().getScore())
                .sum();
    }

    public Piece getRank(int index) {
        return rank.get(index);
    }
}
