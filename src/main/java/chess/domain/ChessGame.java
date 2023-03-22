package chess.domain;

import chess.domain.dto.GameStatusDto;
import chess.domain.piece.Team;
import chess.domain.square.Square;

public class ChessGame {

    private final Board board = Board.create();
    private Team turn;

    public ChessGame() {
        turn = Team.WHITE;
    }

    public void move(final Square current, final Square destination) {
        checkTurn(current);
        board.move(current, destination);
        turn = turn.getEnemy();
    }

    private void checkTurn(final Square square) {
        if (board.isPieceTurn(square, turn)) {
            return;
        }
        throw new IllegalArgumentException("상대팀 말을 움직일 수 없습니다.");
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }
}
