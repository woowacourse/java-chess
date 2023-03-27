package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.view.Command;

public class WhiteTurn implements State {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State progress(Command command, Board board) {
        if (command.isStart()) {
            throw new IllegalArgumentException("게임이 진행중 입니다.");
        }
        if (command.isMove()) {
            checkIsWhite(board, command.getCurrentPosition());
            board.movePiece(command.getCurrentPosition(), command.getTargetPosition());
            return new BlackTurn();
        }
        return new End();
    }

    private void checkIsWhite(Board board, Position position) {
        if (!board.getBoard().get(position).isWhiteTeam()) {
            throw new IllegalArgumentException("화이트의 차례입니다.");
        }
    }

}
