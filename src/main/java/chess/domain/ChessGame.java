package chess.domain;

public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void move(Position source, Position target) {
        board.movePieceAndRenewBoard(source, target);
    }

    public Board getBoard() {
        return board;
    }

    public boolean checkTurn(Position source, Team team) {
        return board.getBoard().get(source).isSameTeam(team);
    }
}
