package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardCreator;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class ChessGame {
    private final ChessBoard board;
    private Team turn;

    private ChessGame(ChessBoard board, Team team) {
        this.board = board;
        this.turn = team;
    }

    public static ChessGame newGame() {
        ChessBoard board = ChessBoardCreator.normalGameCreator().create();
        return new ChessGame(board, Team.WHITE);
    }

    public void playTurn(Position start, Position destination) {
        checkPieceIsTurnTeamPiece(start);
        board.move(start, destination);
        switchTurn();
    }

    private void checkPieceIsTurnTeamPiece(Position start) {
        if (board.findPieceByPosition(start).isOtherTeam(turn)) {
            throw new IllegalArgumentException("현재 턴을 진행하는 팀의 기물이 아닙니다.");
        }
    }

    private void switchTurn() {
        if (turn == Team.BLACK) {
            turn = Team.WHITE;
            return;
        }
        turn = Team.BLACK;
    }

    public ChessBoard getBoard() {
        return board;
    }
}
