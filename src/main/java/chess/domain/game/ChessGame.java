package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.move.MoveStrategy;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private final GameSwitch gameSwitch;
    private final Turn turn;

    public ChessGame(final Board board, final Turn turn) {
        this.board = board;
        this.gameSwitch = new GameSwitch();
        this.turn = turn;
    }

    public ChessGame(final Board board) {
        this(board, new Turn());
    }

    public void move(final String rawSource, final String rawTarget) {
        final Piece targetPiece = movePiece(Position.valueOf(rawSource), Position.valueOf(rawTarget));
        turnOffWhenKingDie(targetPiece);
        turn.nextTurn();
    }

    private Piece movePiece(final Position source, final Position target) {
        Piece sourcePiece = board.getPiece(source);
        validateTurn(turn, sourcePiece);
        MoveStrategy moveStrategy = sourcePiece.getMoveStrategy();
        validateMove(source, target, moveStrategy);
        return board.movePiece(source, target);
    }

    private void validateTurn(final Turn turn, final Piece sourcePiece) {
        if (!turn.isRightTurn(sourcePiece.getTeam())) {
            throw new IllegalStateException("[ERROR] 당신의 차례가 아닙니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final MoveStrategy moveStrategy) {
        if (!moveStrategy.isMovable(board, source, target)) {
            throw new IllegalStateException("[ERROR] 이동할 수 없습니다.");
        }
    }

    private void turnOffWhenKingDie(final Piece targetPiece) {
        if (targetPiece.isKing()) {
            turnOff();
        }
    }

    public boolean isOn() {
        return gameSwitch.isOn();
    }

    public void turnOff() {
        gameSwitch.turnOff();
    }

    public String getPieceName(final String rawPosition) {
        return board.getPiece(Position.valueOf(rawPosition)).convertPieceToString();
    }

    public Board getBoard() {
        return board;
    }

    public Team getCurrentTurn() {
        return turn.getTeam();
    }

    public Map<Position, Piece> getCurrentBoard() {
        return board.getBoard();
    }
}
