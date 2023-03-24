package chess.domain.board;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Path;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Board {

    private static final String FROM_IS_EMPTY_MESSAGE = "출발점에 말이 없습니다.";

    private final Turn turn = new Turn();
    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void move(final Position from, final Position to) {
        validateIsFromEmpty(from);
        turn.validateStartPieceColor(board.get(from));
        final Piece movingPiece = board.get(from);
        final Path path = movingPiece
                .searchPathTo(from, to, board.getOrDefault(to, Empty.getInstance()));
        path.validateObstacle(board.keySet());
        board.remove(from);
        board.put(to, movingPiece);
        turn.changeTurn();
    }

    private void validateIsFromEmpty(final Position from) {
        if (!board.containsKey(from)) {
            throw new IllegalArgumentException(FROM_IS_EMPTY_MESSAGE);
        }
    }

    public Map<Color, Score> calculateScore() {
        final Score blackScore = calculateScoreByColor(Color.BLACK);
        final Score whiteScore = calculateScoreByColor(Color.WHITE);

        return Map.of(Color.BLACK, blackScore, Color.WHITE, whiteScore);
    }

    private Score calculateScoreByColor(final Color color) {
        final Map<File, List<Piece>> pieceGroupByFile = board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(groupingBy(entry -> entry.getKey().file(), mapping(Entry::getValue, toList())));
        return pieceGroupByFile.values()
                .stream()
                .map(this::calculateScoreByFile)
                .reduce(Score.ZERO, Score::sum);
    }

    private Score calculateScoreByFile(final List<Piece> pieces) {
        final Map<PieceType, Long> pieceCount = pieces.stream()
                .collect(groupingBy(Piece::getPieceType, counting()));
        return pieceCount.entrySet()
                .stream()
                .map(this::scoreMap)
                .reduce(Score.ZERO, Score::sum);
    }

    private Score scoreMap(final Entry<PieceType, Long> entryMap) {
        final PieceType pieceType = entryMap.getKey();
        final Long count = entryMap.getValue();
        final Score calculatedScore = Score.mapPieceScore(pieceType).multiply(count);
        if (pieceType == PieceType.PAWN && count >= Score.DUPLICATE_SAME_FILE_PAWN_LIMIT) {
            return calculatedScore.multiply(Score.PAWN_WEIGHT);
        }
        return calculatedScore;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
