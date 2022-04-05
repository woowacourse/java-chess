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

    public ChessGame(final Board board, final GameSwitch gameSwitch, final Turn turn) {
        this.board = board;
        this.gameSwitch = gameSwitch;
        this.turn = turn;
    }

    public void move(final String rawSource, final String rawTarget) {
        validateGameSwitch();
        movePiece(Position.valueOf(rawSource), Position.valueOf(rawTarget));
        turnOffWhenKingDie();
        turn.passTurn();
    }

    private void validateGameSwitch() {
        if (!isOn()) {
            throw new  IllegalStateException("[ERROR] 게임이 종료되어 기물을 이동시킬 수 없습니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        Piece sourcePiece = board.getPiece(source);
        validateTurn(turn, sourcePiece);
        validateMove(source, target, sourcePiece.getMoveStrategy());
        board.movePiece(source, target);
    }

    private void validateTurn(final Turn turn, final Piece sourcePiece) {
        if (!turn.isRightTurn(sourcePiece.getTeam())) {
            throw new IllegalStateException("[ERROR] 당신의 차례가 아닙니다.");
        }
    }

    private void validateMove(final Position source, final Position target, final MoveStrategy moveStrategy) {
        if (!moveStrategy.isMovable(board, source, target)) {
            throw new IllegalStateException("[ERROR] 기물을 이동할 수 없습니다.");
        }
    }

    private void turnOffWhenKingDie() {
        if (board.searchTeamOfDeadKing() != Team.NONE) {
            turnOff();
        }
    }

    public void turnOff() {
        gameSwitch.turnOff();
    }

    public boolean isOn() {
        return gameSwitch.isOn();
    }

    public Result generateResult() {
        return new Result(getCurrentBoard(), board.searchTeamOfDeadKing());
    }

    public Map<Position, Piece> getCurrentBoard() {
        return board.getBoard();
    }

    public Map<String, Piece> getCurrentBoardForSpark() {
        return board.getBoardForSpark();
    }

    public Turn getTurn() {
        return turn;
    }
}
