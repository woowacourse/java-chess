package chess.domain;

import chess.domain.piece.Color;
import chess.domain.square.Square;
import chess.dto.GameStatusDto;

public class ChessGame {

    private final Board board = Board.create();
    private Color turn;

    public ChessGame() {
        turn = Color.WHITE;
    }

    public void move(Square current, Square destination) {
        if (board.isPieceTurn(current, turn)) {
            board.move(current, destination);
            turn = turn.getCounter();
            return;
        }
        throw new IllegalArgumentException("상대팀 말을 움직일 수 없습니다.");
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }
}
