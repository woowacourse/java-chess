package chess.manager;

import chess.controller.dto.BoardResponseDto;
import chess.controller.dto.ShowPathResponseDto;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.command.MoveCommand;
import chess.domain.command.ShowCommand;
import chess.domain.piece.Owner;

public class ChessManager {
    private static final Owner FIRST_TURN = Owner.WHITE;

    private final Board board;
    private Owner turn = FIRST_TURN;
    private boolean isEnd = false;

    public ChessManager() {
        this.board = BoardInitializer.initiateBoard();
    }

    public void move(final MoveCommand command) {
        validateSourcePiece(command.source());
        validateTurn(command.source());
        board.move(command.source(), command.target());
    }

    private void validateSourcePiece(final Position source) {
        if (board.pickPiece(source).isEmptyPiece()) {
            throw new IllegalArgumentException("지정한 칸에는 체스말이 존재하지 않습니다.");
        }
    }

    private void validateTurn(final Position source) {
        if (!board.isPositionSameOwner(source, turn)) {
            throw new IllegalArgumentException("현재는 " + turn.name() + "플레이어의 턴입니다.");
        }
    }

    public void changeTurn() {
        turn = turn.reverse();
    }

    public void resetBoard() {
        turn = FIRST_TURN;
        board.resetBoard();
    }

    public void endGameByDiedKing() {
        isEnd = isDiedKing();
    }

    private boolean isDiedKing() {
        return !board.isKingAlive(this.turn.reverse());
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Status getStatus() {
        return Status.statusOfBoard(board);
    }

    public Path movablePath(final ShowCommand command) {
        validateSourcePiece(command.source());
        return board.movablePath(command.source());
    }

    public BoardResponseDto getBoardResponseDto() {
        return BoardResponseDto.toBoard(board);
    }

    public ShowPathResponseDto findPathByPosition(final ShowCommand showCommand) {
        return ShowPathResponseDto.toPath(movablePath(showCommand));
    }

    public Board getBoard() {
        return board;
    }
}
