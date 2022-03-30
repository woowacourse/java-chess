package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import chess.domain.move.MoveStrategy;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private final CatchPieces catchPieces;
    private final GameSwitch gameSwitch;
    private final Turn turn;

    public ChessGame(final Board board) {
        this.board = board;
        this.catchPieces = new CatchPieces();
        this.gameSwitch = new GameSwitch();
        this.turn = new Turn();
    }

    public void move(final String rawSource, final String rawTarget) {
        Position source = Position.valueOf(rawSource);
        Position target = Position.valueOf(rawTarget);
        movePiece(source, target);
        turn.nextTurn();
    }

    private void movePiece(final Position source, final Position target) {
        Piece sourcePiece = board.getPiece(source);
        validateTurn(turn, sourcePiece);
        MoveStrategy moveStrategy = sourcePiece.getMoveStrategy();
        validateMove(source, target, moveStrategy);
        board.movePiece(source, target, catchPieces);
        turnOffWhenKingDie(sourcePiece.getColor());
    }

    private void validateTurn(final Turn turn, final Piece sourcePiece) {
        if (!turn.isRightTurn(sourcePiece.getColor())) {
            throw new IllegalStateException("[ERROR] 당신의 차례가 아닙니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final MoveStrategy moveStrategy) {
        if (!moveStrategy.isMovable(board, source, target)) {
            throw new IllegalStateException("[ERROR] 기물을 이동할 수 없습니다.");
        }
    }

    private void turnOffWhenKingDie(final Color color) {
        if (catchPieces.isKingCatch(color)) {
            gameSwitch.turnOff();
        }
    }

    public Score calculateScore() {
        return new Score(getCurrentBoard());
    }

    public boolean isOn() {
        return gameSwitch.isOn();
    }

    public void turnOff() {
        gameSwitch.turnOff();
    }

    public Map<Position, Piece> getCurrentBoard() {
        return board.getBoard();
    }
}
