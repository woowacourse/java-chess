package chess.domain.chessGame;

import chess.domain.chessGame.generator.SpaceGenerator;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color deadKingColor = Color.EMPTY;

    public ChessGame(SpaceGenerator spaceGenerator) {
        this.chessBoard = ChessBoard.of(spaceGenerator);
    }

    public ChessGame of(SpaceGenerator spaceGenerator) {
        return new ChessGame(spaceGenerator);
    }

    public void move(Position from, Position to) {
        Color color = checkKingColorOn(to);
        chessBoard.move(from, to);
        deadKingColor = color;
    }

    private Color checkKingColorOn(Position to) {
        if (chessBoard.isBlackKingOn(to)) {
            return Color.BLACK;
        }
        if (chessBoard.isWhiteKingOn(to)) {
            return Color.WHITE;
        }
        return Color.EMPTY;
    }

    public void startGame() {
        chessBoard.startGame();
    }

    public void endGame() {
        chessBoard.endGame();
    }

    public boolean isActive() {
        return chessBoard.isActive();
    }

    public BoardScore calculateScore(Color color) {
        return chessBoard.calculateScore(color);
    }

    public List<String> showBoard() {
        return chessBoard.showBoard();
    }

    public boolean hasSameDeadKingColor(Color otherColor) {
        return deadKingColor == otherColor;
    }
}
