package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.DefaultBoardInitializer;
import chess.domain.order.MoveResult;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.ColoredPieces;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;
import chess.domain.statistics.MatchResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameManager {
    private Board board;
    private List<ColoredPieces> coloredPieces;
    private Color currentColor;
    private GameStatus gameStatus = GameStatus.NOT_STARTED;

    public void start() {
        board = DefaultBoardInitializer.getBoard();
        this.coloredPieces = Arrays.stream(Color.values())
                .map(ColoredPieces::createByColor)
                .collect(Collectors.toList());
        currentColor = Color.WHITE;
        gameStatus = GameStatus.RUNNING;
    }

    public void end() {
        gameStatus = GameStatus.END;
    }

    public void move(Position from, Position to) {
        throwExceptionWhenGameIsNotRunning();
        if (board.findByPosition(from).getPiece().getColor() != currentColor) {
            throw new IllegalArgumentException("현재 움직일 수 있는 진영의 기물이 아닙니다.");
        }
        MoveResult moveResult = board.move(from, to);
        if (moveResult.isCaptured()) {
            ColoredPieces opposite = findByColor(currentColor.opposite());
            opposite.remove(moveResult.getCapturedPiece());
        }
        updateEndCondition();
        turnOver();
    }

    private void throwExceptionWhenGameIsNotRunning() {
        if (gameStatus != GameStatus.RUNNING) {
            throw new IllegalArgumentException("게임이 진행중이지 않아 실행할 수 없습니다.");
        }
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

    public void updateEndCondition() {
        boolean isEnd = isKingDeadEndCondition();

        if (isEnd) {
            gameStatus = GameStatus.END;
        }
    }

    private boolean isKingDeadEndCondition() {
        return coloredPieces.stream()
                .anyMatch(ColoredPieces::isKingDead);
    }

    private Color kingAliveColor() {
        return coloredPieces.stream()
                .filter(ColoredPieces::isKingAlive)
                .map(ColoredPieces::getColor)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("왕이 다 죽어 승자가 없습니다."));
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean isSameStatus(GameStatus gameStatus) {
        return this.gameStatus == gameStatus;
    }

    public ChessGameStatistics getStatistics() {
        Map<Color, Double> scoreMap = board.getScoreMap();
        if (isKingDeadEndCondition()) {
            return new ChessGameStatistics(scoreMap, MatchResult.generateMatchResultByColor(kingAliveColor()));
        }
        return new ChessGameStatistics(scoreMap, MatchResult.generateMatchResult(scoreMap.get(Color.WHITE), scoreMap.get(Color.BLACK)));
    }

}
