package chess.domain;

public class ChessGame {

    private final Board board;
    private Team turn;

    public ChessGame(Board board, Team turn) {
        this.board = board;
        this.turn = turn;
    }

    public void movePiece(Position source, Position target) throws IllegalArgumentException {
        if (board.isTherePiece(source)) {
            validateTurn(source);
        }
        board.move(source, target);
        turn = turn.reverse();
    }

    private void validateTurn(Position source) {
        if (board.isNotTurn(source, turn)) {
            throw new IllegalArgumentException("[ERROR] 현재는 " + turn + "팀 차례입니다.");
        }
    }

    public Board getBoard() {
        return board;
    }
}
