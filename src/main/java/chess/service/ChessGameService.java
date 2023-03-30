package chess.service;

import chess.domain.Color;
import chess.domain.Player;
import chess.domain.Players;
import chess.domain.dao.PieceDao;
import chess.domain.dao.PieceDaoImpl;
import chess.domain.dao.TurnDao;
import chess.domain.dao.TurnDaoImpl;
import chess.domain.factory.InitPlayersFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.math.BigInteger;
import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/03/30
 */
public class ChessGameService {

    private final PieceDao pieceDao = PieceDaoImpl.getInstance();
    private final Players players;

    public ChessGameService() {
        this.players = InitPlayersFactory.initializePlayers();
    }

    public void movePiece(final String inputMovablePiece, final String inputTargetPosition) {
        Position findPosition = findPositionByInputPoint(inputMovablePiece);
        Position targetPosition = Position.from(inputTargetPosition);
        validatePositionByCurrentColor(findPosition, getCurrentTurn());
        move(findPosition, targetPosition);
        changeTurn();
    }

    private void validatePositionByCurrentColor(final Position findPosition, final Color currentTurn) {
        Player findPlayer = findPlayerByPosition(findPosition);
        if (!findPlayer.isSameColor(currentTurn)) {
            throw new IllegalArgumentException("해당 플레이어의 차례가 아닙니다.");
        }
    }

    private Position findPositionByInputPoint(final String point) {
        Position foundPosition = Position.from(point);
        return players.findPosition(foundPosition);
    }

    private List<Position> getAllPosition() {
        return players.getAllPosition();
    }

    private void validateMovingRoute(final Position fromPosition, final Position toPosition) {
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
        if (isAlreadyExistPiece(tempPosition)) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 있습니다.");
        }
    }

    private boolean isAlreadyExistPiece(final Position tempPosition) {
        return getAllPosition().stream()
                .anyMatch(position -> position.equals(tempPosition));
    }

    private Player findPlayerByPosition(final Position findPosition) {
        return players.findPlayerByPosition(findPosition);
    }

    private void changeTurn() {
        Color currentColor = getCurrentTurn();
        Color changeColor = Color.changeColor(currentColor);
        TurnDao turnDao = TurnDaoImpl.getInstance();
        turnDao.update(changeColor);
    }

    private void move(final Position sourcePosition, final Position targetPosition) {
        validateMovingRoute(sourcePosition, targetPosition);

        Player findPlayer = findPlayerByPosition(sourcePosition);
        Piece changedPiece = findPlayer.movePiece(getAllPosition(), sourcePosition, targetPosition);

        pieceDao.updatePosition(changedPiece, sourcePosition);
        deleteWhenIfExistOtherPlayer(targetPosition, findPlayer);
    }

    private void deleteWhenIfExistOtherPlayer(final Position targetPosition, final Player findPlayer) {
        Player anotherPlayer = getAnotherPlayer(findPlayer);
        anotherPlayer.removePiece(targetPosition)
                .ifPresent(piece -> pieceDao.deletePieceByColor(piece, anotherPlayer.getColor()));
    }

    private Player getAnotherPlayer(final Player findPlayer) {
        return players.getAnotherPlayer(findPlayer);
    }

    public List<Piece> getPiecesByColor(final Color color) {
        return getPlayerByColor(color).getPieces();
    }

    private Player getPlayerByColor(final Color color) {
        return players.getPlayerByColor(color);
    }

    public boolean notEveryKingAlive() {
        return players.notEveryKingAlive();
    }

    public String getWinnerColorName() {
        return players.getWinnerColorName();
    }

    public double calculateScore() {
        return players.calculateScore(getCurrentTurn());
    }

    public Color getCurrentTurn() {
        TurnDao turnDao = TurnDaoImpl.getInstance();
        return turnDao.getCurrentTurn();
    }
}
