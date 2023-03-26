package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.FileCoordinate;
import chess.domain.board.Position;
import chess.domain.board.RankCoordinate;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.Map;

public class ChessGame {

    private final Board board;
    private Team nowPlayingTeam;

    public ChessGame(Board board) {
        this.board = board;
        this.nowPlayingTeam = Team.WHITE;
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        board.movePiece(sourcePosition, targetPosition, nowPlayingTeam);
        this.nowPlayingTeam = nowPlayingTeam.getReverseTeam();
    }

    public Piece findPiece(Position position) {
        return board.findPiece(position);
    }

    public long pawnCountByColumnAndTeam(Position position, Team team) {
        FileCoordinate fileCoordinate = position.getFileCoordinate();
        return RankCoordinate.getSortedRankCoordinates()
                .stream()
                .map(rankCoordinate -> findPiece(new Position(fileCoordinate, rankCoordinate)))
                .filter(x -> x.getTeam() == team && x.getClass().equals(Pawn.class))
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoards();
    }
}
