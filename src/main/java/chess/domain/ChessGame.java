package chess.domain;

import chess.domain.coordinate.ChessCoordinate;
import chess.domain.factory.AbstractStateInitiatorFactory;
import chess.domain.piece.ChessPiece;

import java.util.*;
import java.util.stream.Collectors;


public class ChessGame {
    private final Board board;
    private Turn turn;

    public ChessGame(AbstractStateInitiatorFactory stateInitiatorFactory, Turn turn) {
        this.board = new Board(stateInitiatorFactory);
        this.turn = turn;
    }

    public void move(ChessCoordinate from, ChessCoordinate to) {
        ChessPiece fromPiece = board.getPieceByCoord(from);

        validateTurn(fromPiece);

        Set<ChessCoordinate> movableCoordinates = fromPiece.getMovableCoordinates(board::getTeamAt, from);

        movableCoordinates.stream()
                .filter(coord -> coord.equals(to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다"));

        board.updateBoard(from, to);

        this.turn = turn.nextTurn();
    }

    private void validateTurn(ChessPiece fromPiece) {

        if (fromPiece.getType().getTeam() != turn.getCurrent()) {
            throw new IllegalArgumentException("해당 팀의 차례가 아닙니다.");
        }
    }

    public Set<String> getMovable(ChessCoordinate from) {
        return board.getPieceByCoord(from).getMovableCoordinates(board::getTeamAt, from).stream()
                .map(coord -> coord.toString()).collect(Collectors.toSet());
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurn() {
        return turn.getCurrent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) &&
                Objects.equals(turn, chessGame.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, turn);
    }
}
