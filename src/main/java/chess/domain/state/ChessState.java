package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.score.Score;
import chess.domain.score.ScoreManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class ChessState {
    private static final int KING_TOTAL = 2;

    protected final Map<Position, Piece> board;

    protected ChessState(Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public abstract void move(Color turnColor, Positions positions);

    public final ChessState changeStrategy(Position from) {
        Piece selectedPiece = board.get(from);
        return selectedPiece.state(board);
    }

    protected final void checkTurnOf(Piece currentPiece, Color turnColor) {
        if (!currentPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("상대 말은 이동할 수 없습니다.");
        }
    }

    protected final boolean isNotAllBlankPath(Set<Position> path) {
        return !path.stream()
                .map(board::get)
                .allMatch(Piece::isBlank);
    }

    public final boolean isKingCaptured() {
        return board.values().stream()
                .filter(Piece::isKing)
                .count() != KING_TOTAL;
    }

    public final Color announceCapturedKingColor() {
        return board.values().stream()
                .filter(Piece::isKing)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("왕이 모두 잡혔습니다."))
                .color()
                .findOppositeColor();
    }

    public final Score calculateScore(Color color) {
        ScoreManager scoreManager = new ScoreManager();
        return IntStream.rangeClosed(1, 8)
                .mapToObj(i -> findFilePieces(color, i))
                .map(scoreManager::calculateFileScore)
                .reduce(new Score(0), Score::add);
    }

    private List<Piece> findFilePieces(Color color, int file) {
        return IntStream.rangeClosed(1, 8)
                .mapToObj(i -> board.get(new Position(file, i)))
                .filter(piece -> piece.isSameColor(color))
                .toList();
    }

    public final Map<Position, PieceType> collectBoard() {
        return board.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().pieceType()
                ));
    }
}
