package chess.domain;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class ChessGame {
    private static final Team INITIAL_TURN = Team.WHITE;

    private final ChessBoard chessBoard;
    private Team turn;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        turn = INITIAL_TURN;
    }

    public void move(Position start, Position destiantion) {
        validatePieceExist(start);
        validateTurn(chessBoard.findPieceByPosition(start));
        validateLegalMovement(start, destiantion);

        chessBoard.move(start, destiantion);
        turn = turn.otherTeam();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    private void validatePieceExist(Position start) {
        if (chessBoard.positionIsEmpty(start)) {
            throw new IllegalArgumentException("움직임을 시작하는 위치에 기물이 존재하지 않습니다");
        }
    }

    private void validateTurn(Piece piece) {
        if (piece.isOtherTeam(turn)) {
            throw new IllegalArgumentException(turn + "의 차례입니다");
        }
    }

    private void validateLegalMovement(Position start, Position destination) {
        if (!chessBoard.canMove(start, destination)) {
            throw new IllegalArgumentException("기물의 행마법에 어긋나는 움직임입니다");
        }
    }
}
