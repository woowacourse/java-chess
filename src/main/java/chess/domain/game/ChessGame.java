package chess.domain.game;

import chess.domain.Position;
import chess.domain.Score;
import chess.domain.TeamColor;
import chess.domain.piece.*;
import chess.controller.dto.PieceDto;

import java.util.List;
import java.util.Set;

import static chess.domain.TeamColor.BLACK;
import static chess.domain.TeamColor.WHITE;

public class ChessGame {

    private static final int BOARD_SIZE = 8;

    private final Pieces pieces;
    private TeamColor currentColor;

    public ChessGame() {
        pieces = new Pieces();
        currentColor = WHITE;
        init();
    }

    public ChessGame(Pieces pieces, TeamColor currentColor) {
        this.pieces = pieces;
        this.currentColor = currentColor;
        updatePiecesMovablePositions();
    }

    private void init() {
        initPieces(WHITE, 0);
        initPawns(WHITE, 1);

        initPawns(BLACK, 6);
        initPieces(BLACK, 7);
        updatePiecesMovablePositions();
    }

    private void initPieces(final TeamColor teamColor, final int y) {
        pieces.add(new Rook(teamColor, Position.of(0, y)));
        pieces.add(new Knight(teamColor, Position.of(1, y)));
        pieces.add(new Bishop(teamColor, Position.of(2, y)));
        pieces.add(new Queen(teamColor, Position.of(3, y)));
        pieces.add(new King(teamColor, Position.of(4, y)));
        pieces.add(new Bishop(teamColor, Position.of(5, y)));
        pieces.add(new Knight(teamColor, Position.of(6, y)));
        pieces.add(new Rook(teamColor, Position.of(7, y)));
    }

    private void initPawns(final TeamColor teamColor, final int y) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            pieces.add(new Pawn(teamColor, Position.of(x, y)));
        }
    }

    public void updatePiecesMovablePositions() {
        pieces.updatePiecesMovablePositions();
    }

    public void move(final Position currentPosition, final Position targetPosition) throws PieceNotFoundException, ImpossibleMoveException {
        pieces.move(currentPosition, targetPosition, currentColor);
        currentColor = currentColor.reverse();
    }

    public boolean isKingDead() {
        return pieces.isKingDead(currentColor);
    }

    public Score totalScoreByTeamColor(final TeamColor teamColor) {
        return pieces.totalScoreByTeamColor(teamColor);
    }

    public boolean checked() {
        updatePiecesMovablePositions();
        Set<Position> enemyAttackPositions = pieces.attackPositions(enemyColor());
        Piece king = pieces.kingByColor(currentColor);
        return enemyAttackPositions.contains(king.currentPosition());
    }

    public Piece piece(final Position position) {
        return pieces.pieceByPosition(position);
    }

    public TeamColor enemyColor() {
        return currentColor.reverse();
    }

    public ChessResult result() {
        return new ChessResult(totalScoreByTeamColor(WHITE), totalScoreByTeamColor(BLACK));
    }

    public List<PieceDto> getPieces() {
        return pieces.getPieces();
    }

    public String getCurrentColor() {
        return currentColor.toString();
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }
}
