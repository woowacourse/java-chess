package chess.domain;

import chess.domain.piece.Team;
import chess.domain.square.Square;
import chess.dto.GameStatusDto;
import chess.dto.SquareDto;

public class ChessGame {

    private final Board board = Board.create();
    private Team turn;

    public ChessGame() {
        turn = Team.WHITE;
    }

    public void move(final SquareDto currentDto, final SquareDto destinationDto) {
        Square current = currentDto.getSquare();
        Square destination = destinationDto.getSquare();
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
