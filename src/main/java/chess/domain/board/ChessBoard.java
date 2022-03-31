package chess.domain.board;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Score;
import chess.domain.piece.Article;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class ChessBoard {

    private static final int BASE_KING_COUNT = 2;

    private final Map<Position, Article> pieces;

    public ChessBoard(Map<Position, Article> pieces) {
        this.pieces = pieces;
    }

    private static double scoreOfPiece(Entry<Position, Article> positionPiece) {
        return Score.valueOf(positionPiece.getValue()).getValue();
    }

    public void movePiece(Position from, Position to) {
        Article movePiece = findByPiece(from).move(from, to, this);

        pieces.remove(from);
        pieces.put(to, movePiece);
    }

    public Article findByPiece(Position position) {
        if (pieces.containsKey(position)) {
            return pieces.get(position);
        }

        throw new IllegalArgumentException("보드판에 존재하는 기물이 없습니다.");
    }

    public boolean isEmptyPosition(Position position) {
        return !pieces.containsKey(position);
    }

    public boolean isSameColor(Position position, Color color) {
        return findByPiece(position).getColor() == color;
    }

    public Map<Color, Double> getColorsTotalScore() {
        Map<Color, Double> totalScore = new EnumMap<>(Color.class);
        totalScore.put(Color.WHITE, getTotalScore(Color.WHITE));
        totalScore.put(Color.BLACK, getTotalScore(Color.BLACK));

        return totalScore;
    }

    private double getTotalScore(Color color) {
        return pieces.entrySet().stream()
                .filter(positionPiece -> isSameColor(positionPiece.getKey(), color))
                .mapToDouble(ChessBoard::scoreOfPiece)
                .sum();
    }

    public Color getWinner() {
        return pieces.values()
                .stream()
                .filter(Article::isKing)
                .map(Article::getColor)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("보드 판에 왕이 존재하지 않습니다."));
    }

    public boolean isKingAlive() {
        return getAliveKingCount() != BASE_KING_COUNT;
    }

    private long getAliveKingCount() {
        return pieces.values()
                .stream()
                .filter(Article::isKing)
                .count();
    }

    public Map<Position, Article> getPieces() {
        return Map.copyOf(pieces);
    }
}
