package chess.domain;

import chess.domain.board.BoardFactory;
import chess.domain.board.ChessBoard;
import chess.domain.order.MoveResult;
import chess.domain.piece.Color;
import chess.domain.piece.ColoredPieces;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.*;
import chess.domain.statistics.ChessGameStatistics;
import chess.domain.statistics.MatchResult;
import chess.exception.DomainException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class ChessGameManager {
    private ChessBoard chessBoard;
    private List<ColoredPieces> coloredPieces;
    private Color currentTurnColor;
    private State state;

    public ChessGameManager() {
        updateState(StateFactory.init());
    }

    public void start() {
        chessBoard = BoardFactory.createBoard();
        this.coloredPieces = Arrays.stream(Color.values())
                .map(ColoredPieces::createByColor)
                .collect(toList());
        currentTurnColor = Color.WHITE;
        updateState(this.state.start());
    }

    public void load(ChessBoard chessBoard, Color currentTurnColor) {
        this.chessBoard = chessBoard;
        this.currentTurnColor = currentTurnColor;
        this.coloredPieces = chessBoard.board().values().stream()
                .filter(Piece::isNotBlank)
                .collect(groupingBy(Piece::getColor))
                .entrySet()
                .stream()
                .map(entry -> new ColoredPieces(entry.getValue(), entry.getKey()))
                .collect(toList());
        updateState(this.state.start());
    }

    private void updateState(State state) {
        this.state = state;
    }

    public void end() {
        updateState(this.state.end());
    }

    public void move(Position from, Position to) {
        validateProperPieceAtFromPosition(from);
        if (this.state.isNotRunning()) {
            throw new DomainException("이동 명령을 수행할 수 없습니다. - 진행중인 게임이 없습니다.");
        }

        MoveResult moveResult = chessBoard.move(chessBoard.createMoveRoute(from, to));
        if (moveResult.isCaptured()) {
            ColoredPieces opposite = findByColor(currentTurnColor.opposite());
            opposite.remove(moveResult.getCapturedPiece());
        }
        turnOver();
        updateState(this.state.move(isKingDeadEndCondition()));
    }

    private void validateProperPieceAtFromPosition(Position from) {
        validateHasPieceAtFromPosition(from);
        validateTurn(from);
    }

    private void validateHasPieceAtFromPosition(Position from) {
        if (!this.chessBoard.hasPiece(from)) {
            throw new DomainException("해당 위치에는 말이 없습니다.");
        }
    }

    private void validateTurn(Position from) {
        if (!chessBoard.getPieceByPosition(from).isSameColor(this.currentTurnColor)) {
            throw new DomainException("현재 움직일 수 있는 진영의 기물이 아닙니다.");
        }
    }

    private ColoredPieces findByColor(Color color) {
        return coloredPieces.stream()
                .filter(pieces -> pieces.isSameColor(color))
                .findAny()
                .orElseThrow(() -> new DomainException("시스템 에러 - 진영을 찾을 수 없습니다."));
    }

    private void turnOver() {
        currentTurnColor = currentTurnColor.opposite();
    }

    public boolean isKingDeadEndCondition() {
        return coloredPieces.stream()
                .anyMatch(ColoredPieces::isKingDead);
    }

    public ChessGameStatistics getStatistics() {
        updateState(this.state.status());
        Map<Color, Double> scoreMap = chessBoard.getScoreMap();
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

    public ChessBoard getBoard() {
        return this.chessBoard;
    }

    public boolean hasGame() {
        return !(this.state instanceof EndWithoutGame || this.state instanceof InitialState);
    }

    public boolean isEnd() {
        return this.state instanceof GameEnd || this.state instanceof EndWithoutGame;
    }

    public Color getCurrentTurnColor() {
        return this.currentTurnColor;
    }
}
