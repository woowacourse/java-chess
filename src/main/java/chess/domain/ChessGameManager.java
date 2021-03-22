package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.order.MoveResult;
import chess.domain.piece.Color;
import chess.domain.piece.ColoredPieces;
import chess.domain.position.Position;
import chess.domain.state.*;
import chess.domain.statistics.ChessGameStatistics;
import chess.domain.statistics.MatchResult;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessGameManager {
    private Board board;
    private List<ColoredPieces> coloredPieces;
    private Color currentTurnColor;
    private State state;

    public ChessGameManager() {
        updateAndReturnState(StateFactory.init());
    }

    public State start() {
        board = BoardFactory.createBoard();
        this.coloredPieces = Arrays.stream(Color.values())
                .map(ColoredPieces::createByColor)
                .collect(Collectors.toList());
        currentTurnColor = Color.WHITE;
        return updateAndReturnState(this.state.start());
    }

    private State updateAndReturnState(State state) {
        this.state = state;
        return this.state;
    }

    public State end() {
        return updateAndReturnState(this.state.end());
    }

    public State move(Position from, Position to) {
        if (this.state.isNotRunning()) {
            throw new IllegalArgumentException("이동 명령을 수행할 수 없습니다. - 진행중인 게임이 없습니다.");
        }
        validateTurn(from);
        MoveResult moveResult = board.move(from, to);
        if (moveResult.isCaptured()) {
            ColoredPieces opposite = findByColor(currentTurnColor.opposite());
            opposite.remove(moveResult.getCapturedPiece());
        }
        turnOver();
        return updateAndReturnState(this.state.move(isKingDeadEndCondition()));
    }

    private void validateTurn(Position from) {
        if (!board.findByPosition(from).getPiece().isSameColor(this.currentTurnColor)) {
            throw new IllegalArgumentException("현재 움직일 수 있는 진영의 기물이 아닙니다.");
        }
    }

    private void turnOver() {
        currentTurnColor = currentTurnColor.opposite();
    }

    private ColoredPieces findByColor(Color color) {
        return coloredPieces.stream()
                .filter(pieces -> pieces.isSameColor(color))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("시스템 에러 - 진영을 찾을 수 없습니다."));
    }

    public boolean isKingDeadEndCondition() {
        return coloredPieces.stream()
                .anyMatch(ColoredPieces::isKingDead);
    }

    public ChessGameStatistics getStatistics() {
        updateAndReturnState(this.state.status());
        Map<Color, Double> scoreMap = board.getScoreMap();
        if (isKingDeadEndCondition()) {
            return new ChessGameStatistics(scoreMap, MatchResult.generateMatchResultByColor(kingAliveColor()));
        }
        return new ChessGameStatistics(scoreMap, MatchResult.generateMatchResult(scoreMap.get(Color.WHITE), scoreMap.get(Color.BLACK)));
    }

    private Color kingAliveColor() {
        return coloredPieces.stream()
                .filter(ColoredPieces::isKingAlive)
                .map(ColoredPieces::getColor)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("시스템 에러 - King이 살아있는 진영을 찾을 수 없습니다."));
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean hasGame() {
        return !(this.state instanceof EndWithoutGame || this.state instanceof InitialState);
    }

    public boolean isEnd() {
        return this.state instanceof GameEnd || this.state instanceof EndWithoutGame;
    }
}
