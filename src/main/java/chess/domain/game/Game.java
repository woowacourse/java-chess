package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Game {

    private Color turn;
    private final Board board;

    public Game() {
        this.turn = Color.WHITE;
        this.board = BoardFactory.createBoard();
    }

    public void movePiece(String sourceRequest, String targetRequest) {
        Position source = new Position(sourceRequest);
        Position target = new Position(targetRequest);
        checkTurn(source);
        board.move(source, target);
        changeTurn();
    }

    private void checkTurn(Position position) {
        if (!board.checkTurn(position, turn)) {
            throw new IllegalArgumentException("자신의 기물만 움직일 수 있습니다");
        }
    }

    private void changeTurn() {
        turn = turn.change();
    }

    public boolean isEndOfGame() {
        return board.isEndOfGame();
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    public String getTurn() {
        return turn.name();
    }

    public GameResult getResult() {
        return board.getResult();
    }
}
