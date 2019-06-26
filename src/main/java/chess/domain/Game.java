package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.piecefigure.Bishop;
import chess.domain.piece.pieceinfo.TeamType;
import chess.exception.InvalidTurnException;

import java.util.Objects;
import java.util.Set;

public class Game {
    private TeamType currentTeam;
    private final Board board;

    public Game(final Board board) {
        this(board, TeamType.BLACK);
    }

    public Game(final Board board, final TeamType teamType) {
        this.board = board;
        this.currentTeam = teamType;
    }

    public Board getBoard() {
        return this.board;
    }

    public TeamType getCurrentTeam() {
        return this.currentTeam;
    }

    public Set<Position> selectSourcePiece(Position source) {
        if(!board.getCurrentPiece(source).isSameTeam(Bishop.of(currentTeam))) {
            throw new InvalidTurnException();
        }

        return board.getPossiblePositions(source);
    }

    public boolean move(Position source, Position destination) {
        boolean result = board.movePiece(source, destination);
        currentTeam = TeamType.switchTeamType(currentTeam);
        return result;
    }

    public double getScore(TeamType teamType) {
        return board.calculateFinalScore(teamType);
    }
}
