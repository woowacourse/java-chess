package chess.domain;

import chess.domain.piece.*;
import chess.exception.ImpossibleMoveException;
import chess.exception.PieceNotFoundException;

import java.util.Map;
import java.util.Optional;
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
        updateMovablePositions();
    }

    private void init() {
        initPieces(WHITE, 0);
        initPawns(WHITE, 1);

        initPawns(BLACK, 6);
        initPieces(BLACK, 7);
        updateMovablePositions();
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

    public void updateMovablePositions() {
        pieces.updateMovablePositions();
    }

    public void move(final Position currentPosition, final Position targetPosition) throws PieceNotFoundException, ImpossibleMoveException {
        Piece currentPiece = piece(currentPosition).orElseThrow(PieceNotFoundException::new);
        if (currentPiece.notSameColor(currentColor)) {
            throw new ImpossibleMoveException(currentColor + "팀 차례 입니다.");
        }

        Optional<Piece> targetPiece = piece(targetPosition);

        currentPiece.move(targetPosition);
        targetPiece.ifPresent(pieces::remove);
        updateMovablePositions();
        currentColor = currentColor.reverse();
    }

    public boolean isKingDead() {
        return pieces.isKingDead(currentColor);
    }

    public Score totalScoreByTeamColor(final TeamColor teamColor) {
        return pieces.totalScoreByTeamColor(teamColor);
    }

    public boolean checked() {
        updateMovablePositions();
        Set<Position> enemyAttackPositions = pieces.attackPositions(enemyColor());
        Piece king = pieces.kingByColor(currentColor);
        return enemyAttackPositions.contains(king.currentPosition());
    }

    public Map<Position, String> nameGroupingByPosition() {
        return pieces.nameGroupingByPosition();
    }

    public Optional<Piece> piece(final Position position) {
        return pieces.pieceByPosition(position);
    }

    public TeamColor enemyColor() {
        return currentColor.reverse();
    }

    public int boardSize() {
        return BOARD_SIZE;
    }

    public ChessResult result() {
        return new ChessResult(totalScoreByTeamColor(WHITE), totalScoreByTeamColor(BLACK));
    }
}
