package chess.domain;

import chess.domain.piece.Color;
import chess.domain.square.Square;
import chess.dto.GameStatusDto;
import chess.dto.SquareDto;

public class ChessGame {

    private final Board board = Board.create();
    private Color turn;

    public ChessGame() {
        turn = Color.WHITE;
    }

    public void move(SquareDto currentDto, SquareDto destinationDto) {
        Square current = currentDto.getSquare();
        Square destination = destinationDto.getSquare();
        checkTurn(current);
        board.move(current, destination);
        turn = turn.getCounter();
    }

    private void checkTurn(Square square) {
        if (board.isPieceTurn(square, turn)) {
            return;
        }
        throw new IllegalArgumentException("상대팀 말을 움직일 수 없습니다.");
    }

    public GameStatusDto getGameStatus() {
        return GameStatusDto.from(board);
    }
}
