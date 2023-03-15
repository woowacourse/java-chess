package chess.domain.board;

import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    //todo : start 위치가 들어왔을 때 무슨 말이 찾아주는 거
    public Piece findPiece(final String start) {
        return chessBoard.get(Position.from(start));
    }

    //todo : 무슨 말인지에서 도착 지점까지의 길 중에 누가 있는지 파악

    //todo : 위치 바꾸어 주는 거
    public void switchPosition(final String start, final String end) {
        validateMove(start, end);
        chessBoard.replace(Position.from(end), findPiece(start));
        chessBoard.replace(Position.from(start), new Place());
    }

    private void validateMove(final String start, final String end) {
        Piece piece = findPiece(start);
        piece.canMove(start, end);
        validateMoveSamePosition(start, end);
        validateMoveMyTeam(start, end);
    }

    private void validateMoveMyTeam(final String start, final String end) {
        if ((findPiece(start).isNameLowerCase() == findPiece(end).isNameLowerCase()) && !findPiece(end).getName().equals(".")) {
            throw new IllegalArgumentException("우리팀 말에게 이동할 수 없습니다.");
        }
    }

    private void validateMoveSamePosition(final String start, final String end) {
        if(start.equals(end)) {
            throw new IllegalArgumentException("같은 위치로 움직일 수 없습니다.");
        }
    }
}
