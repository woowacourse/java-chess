package domain;

import domain.board.Board;
import domain.piece.Piece;
import domain.piece.Score;
import domain.piece.Team;
import domain.position.Position;
import gameinitializer.ChessGameInitializer;
import java.util.Map;

public final class ChessGame {
    private final Board board;
    private Team thisTurn;

    private ChessGame(final Board board, final Team thisTurn) {
        this.board = board;
        this.thisTurn = thisTurn;
    }

    public static ChessGame initGame(ChessGameInitializer initializer) {
        return new ChessGame(Board.create(initializer.initPiecePosition()), initializer.initTeam());
    }

    public Map<Position, Piece> getPieces() {
        return board.getPieces();
    }

    public boolean movePiece(final Position source, final Position destination) {
        boolean endedGame = board.move(source, destination, thisTurn);
        thisTurn = thisTurn.otherTeam();
        return endedGame;
    }

    public Score calculateTeamScore(Team team) {
        Map<Position, Piece> teamPieces = board.getPiecesByTeam(team);
        double teamScore = teamPieces.entrySet().stream().mapToDouble((entry) -> {
            Piece piece = entry.getValue();
            Score pieceScore = piece.getScore(entry.getKey(), teamPieces);
            return pieceScore.getScore();
        }).sum();
        return new Score(teamScore);
    }

    public Team getTeam() {
        return thisTurn;
    }
}
