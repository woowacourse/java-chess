package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.order.MoveResult;
import chess.domain.piece.Color;
import chess.domain.piece.ColoredPieces;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGameManager {
    private Board board;
    private List<ColoredPieces> coloredPieces;
    private Color currentColor;
    private GameStatus gameStatus = GameStatus.NOT_STARTED;

    public void start() {
        board = BoardFactory.createBoard();
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

    public void status() {
        throwExceptionWhenGameIsNotRunning();
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
        boolean isEnd = coloredPieces.stream()
                .anyMatch(ColoredPieces::isKingDead);
        
        if (isEnd) {
            gameStatus = GameStatus.END;
        }
    }

    public Board getBoard() {
        return this.board;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}
