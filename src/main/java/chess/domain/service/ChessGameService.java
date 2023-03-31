package chess.domain.service;

import chess.domain.dao.PieceDao;
import chess.domain.dao.TurnDao;
import chess.domain.factory.PlayersFactory;
import chess.domain.model.Score;
import chess.domain.model.piece.Piece;
import chess.domain.model.player.Color;
import chess.domain.model.player.Player;
import chess.domain.model.player.Players;
import chess.domain.model.position.Position;
import chess.dto.response.PiecesResponse;
import chess.ui.OutputView;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class ChessGameService {

    private final TurnDao turnDao;
    private final PieceDao pieceDao;
    private Players players;

    public ChessGameService(TurnDao turnDao, PieceDao pieceDao) {
        this.turnDao = turnDao;
        this.pieceDao = pieceDao;
    }

    public void initialize() {
        players = PlayersFactory.initialize(pieceDao, turnDao);
    }

    public PiecesResponse start() {
        players = PlayersFactory.initialize(pieceDao, turnDao);
        return new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
    }

    public boolean isEveryKingAlive() {
        return !players.isEveryKingAlive();
    }

    public void finishGame() {
        if (players.isEveryKingAlive()) {
            OutputView.printWinner(players.getWinnerColorName());
            pieceDao.deleteAll();
        }
    }

    public Score calculateScore() {
        return players.calculateScore();
    }

    public PiecesResponse move(Position source, Position target) {
        validatePositions(source, target);
        validateMovingRoute(source, target);
        movePiece(source, target);
        changeTurn();
        return new PiecesResponse(players.getPiecesByColor(Color.WHITE), players.getPiecesByColor(Color.BLACK));
    }

    private void movePiece(Position source, Position target) {
        Optional<Player> targetPieceOwner = players.findPlayerByPosition(target);
        Piece updatedPiece = players.movePiece(source, target, targetPieceOwner.isPresent());
        pieceDao.updatePosition(updatedPiece, source);

        targetPieceOwner.ifPresent(player -> {
            Piece deleted = player.removePiece(target);
            pieceDao.deletePieceByColor(deleted, player.getColor());
        });
    }

    private void changeTurn() {
        Color newTurn = players.changeTurn();
        turnDao.update(newTurn);
    }

    public void validatePositions(final Position findPosition, final Position targetPosition) {
        Player findPlayer = players.findPlayerByPosition(findPosition).orElseThrow();
        players.validateTurn(findPlayer);
        players.validateTargetIsNotSameColor(targetPosition, findPlayer);
    }

    public void validateMovingRoute(final Position fromPosition, final Position toPosition) {
        List<Integer> directionVector = calculateDirectionVector(fromPosition, toPosition);
        validateEachPositions(fromPosition, toPosition, directionVector);
    }

    private List<Integer> calculateDirectionVector(final Position fromPosition, final Position toPosition) {
        int diffFile = toPosition.calculateFileDistance(fromPosition);
        int diffRank = toPosition.calculateRankDistance(fromPosition);

        BigInteger rankAndFileGcd = BigInteger.valueOf(Math.abs(diffFile)).gcd(BigInteger.valueOf(Math.abs(diffRank)));
        int fileDirection = diffFile / rankAndFileGcd.intValue();
        int rankDirection = diffRank / rankAndFileGcd.intValue();
        return List.of(fileDirection, rankDirection);
    }

    private void validateEachPositions(final Position fromPosition, final Position toPosition,
                                       final List<Integer> directionVector) {
        Integer fileDirection = directionVector.get(0);
        Integer rankDirection = directionVector.get(1);
        Position tempPosition = fromPosition.move(fileDirection, rankDirection);

        while (!tempPosition.equals(toPosition)) {
            validateIsEmpty(tempPosition);
            tempPosition = tempPosition.move(fileDirection, rankDirection);
        }
    }

    private void validateIsEmpty(final Position tempPosition) {
        if (players.isAlreadyExistPiece(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }
}
