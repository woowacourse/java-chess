package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.scorecalculator.ChessScoreCalculator;
import chess.domain.scorecalculator.ScoreCalculator;
import chess.exception.InvalidTurnException;
import chess.exception.PieceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class ChessGameImpl implements ChessGame {

    private static final int BOARD_SIZE = 8;
    private static final int START_LINE = 0;
    private static final int END_LINE = 7;

    private final Pieces pieces;
    private final ScoreCalculator scoreCalculator;
    private TeamColor currentColor;

    private ChessGameImpl(Pieces pieces, TeamColor teamColor,
        ScoreCalculator scoreCalculator) {
        this.pieces = pieces;
        currentColor = teamColor;
        this.scoreCalculator = scoreCalculator;
    }

    public static ChessGameImpl initialGame() {
        return from(
            Pieces.from(PieceFactory.initialPieces(BOARD_SIZE, START_LINE, END_LINE)),
            TeamColor.WHITE
        );
    }

    public static ChessGameImpl emptyGame() {
        return from(Pieces.emptyPieces(), TeamColor.WHITE);
    }

    public static ChessGameImpl from(Pieces pieces, TeamColor teamColor) {
        ChessGameImpl chessGame = new ChessGameImpl(pieces, teamColor, new ChessScoreCalculator());
        pieces.updateMovablePositions();
        return chessGame;
    }

    @Override
    public void movePiece(Position currentPosition, Position targetPosition) {
        Piece currentPiece = pieces.pieceByPosition(currentPosition)
            .orElseThrow(PieceNotFoundException::new);
        ;
        if (currentPiece.isNotSameColor(currentColor)) {
            throw new InvalidTurnException(currentColor);
        }
        Optional<Piece> targetPiece = pieces.pieceByPosition(targetPosition);

        currentPiece.move(targetPosition);
        targetPiece.ifPresent(pieces::remove);
        pieces.updateMovablePositions();
        currentColor = currentColor.reverse();
    }

    @Override
    public GameResult gameResult() {
        return new GameResult(scoreByTeamColor(TeamColor.WHITE), scoreByTeamColor(TeamColor.BLACK));
    }

    private Score scoreByTeamColor(TeamColor teamColor) {
        List<Piece> piecesByTeamColor = pieces.piecesByTeamColor(teamColor);
        return scoreCalculator.calculate(piecesByTeamColor);
    }

    @Override
    public boolean isKingDead() {
        return pieces.isKingEmpty(currentColor);
    }

    @Override
    public boolean isChecked() {
        Set<Position> enemiesAttackPositions = pieces
            .attackPositionsByColor(currentColor.reverse());

        if (!isKingDead()) {
            return enemiesAttackPositions.contains(
                pieces.kingByColor(currentColor)
                    .currentPosition()
            );
        }

        return false;
    }

    @Override
    public boolean isCheckmate() {
        Set<Position> attackPositions = pieces.attackPositionsByColor(currentColor.reverse());
        Piece king = pieces.kingByColor(currentColor);
        List<Position> movablePositions = king.movablePositions();
        movablePositions.add(king.currentPosition());
        return attackPositions.containsAll(movablePositions);
    }

    @Override
    public Pieces pieces() {
        return pieces;
    }

    @Override
    public List<Piece> currentColorPieces() {
        return pieces.piecesByTeamColor(currentColor);
    }

    @Override
    public TeamColor currentColor() {
        return currentColor;
    }
}
