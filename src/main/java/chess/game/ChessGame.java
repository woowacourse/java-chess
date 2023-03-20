package chess.game;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.dto.SquareResponse;

import java.util.List;

public class ChessGame {

    private final Board board;
    private Turn turn;

    public ChessGame() {
        this.board = new Board(BoardFactory.create());
        this.turn = new Turn(Team.WHITE);
    }

    public void move(Position source, Position target) {
        validateBoard();
        validateTurn(source);
        board.move(source, target);
        changeTurn();
    }

    private void changeTurn() {
        turn = turn.next();
    }

    private void validateTurn(Position source) {
        Piece sourcePiece = board.findByPosition(source);
        if (isInvalidTurn(sourcePiece)) {
            throw new IllegalArgumentException("[ERROR] " + turn.getTeam().name() + "팀의 말만 이동할 수 있습니다.");
        }
    }

    private boolean isInvalidTurn(Piece sourcePiece) {
        return !turn.isCollectWith(sourcePiece);
    }

    public List<SquareResponse> getBoard() {
        validateBoard();
        return board.getBoard().entrySet().stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList());
    }

    private void validateBoard() {
        if (board == null) {
            throw new IllegalStateException("[ERROR] 보드가 세팅되지 않았습니다.");
        }
    }
}
