package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.order.MoveResult;
import chess.domain.piece.ColoredPieces;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;
import chess.domain.statistics.MatchResult;

import java.util.List;
import java.util.Map;

public class RunningGameManager implements ChessGameManager {
    private final Board board;
    private final List<ColoredPieces> coloredPieces;
    private Color currentColor;

    public RunningGameManager(Board board, List<ColoredPieces> coloredPieces, Color currentColor) {
        this.board = board;
        this.coloredPieces = coloredPieces;
        this.currentColor = currentColor;
    }

    @Override
    public ChessGameManager start() {
        return ChessGameManagerFactory.createRunningGame();
    }

    @Override
    public ChessGameManager end() {
        return ChessGameManagerFactory.createEndGame(getStatistics());
    }

    @Override
    public void move(Position from, Position to) {
        if (board.findColorByPosition(from) != currentColor) {
            throw new IllegalArgumentException("현재 움직일 수 있는 진영의 기물이 아닙니다.");
        }
        MoveResult moveResult = board.move(from, to);
        if (moveResult.isCaptured()) {
            ColoredPieces opposite = findByColor(currentColor.opposite());
            opposite.remove(moveResult.getCapturedPiece());
        }
        turnOver();
    }

    private void turnOver() {
        currentColor = currentColor.opposite();
    }

    private ColoredPieces findByColor(Color color) {
        return coloredPieces.stream()
                .filter(pieces -> pieces.isSameColor(color))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 진영의 기물들입니다."));
    }

    @Override
    public boolean isKingDead() {
        return coloredPieces.stream()
                .anyMatch(ColoredPieces::isKingDead);
    }

    private Color kingAliveColor() {
        return coloredPieces.stream()
                .filter(ColoredPieces::isKingAlive)
                .map(ColoredPieces::getColor)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("왕이 다 죽어 승자가 없습니다."));
    }

    @Override
    public ChessGameStatistics getStatistics() {
        Map<Color, Double> scoreMap = board.getScoreMap();
        if (isKingDead()) {
            return new ChessGameStatistics(scoreMap, MatchResult.generateMatchResultByColor(kingAliveColor()));
        }
        return new ChessGameStatistics(scoreMap, MatchResult.generateMatchResult(scoreMap.get(Color.WHITE), scoreMap.get(Color.BLACK)));
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public boolean isStart() {
        return true;
    }
}
