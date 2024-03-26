package domain.game;

import dao.DBConnector;
import dao.GameDao;
import dao.PieceDao;
import domain.position.File;
import domain.position.Position;
import dto.PieceDto;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    public static final double DUPLICATED_PAWN_PENALTY_RATE = 0.5;

    private final Map<Position, Piece> chessBoard;

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public MoveResponse movePiece(final TeamColor teamColor, final Position source, final Position destination) {
        validateMoveRequest(teamColor, source, destination);

        Piece piece = chessBoard.get(source);
        chessBoard.remove(source);
        Optional<Piece> caughtPiece = getCaughtPiece(destination);
        chessBoard.put(destination, piece);

        if (caughtPiece.isPresent()) {
            PieceType caughtPieceType = caughtPiece.get().getPieceType();
            return new CaughtMoveResponse(caughtPieceType);
        }
        return new NormalMoveResponse();
    }

    private void validateMoveRequest(TeamColor teamColor, Position source, Position destination) {
        validateEmpty(source);
        validateColor(teamColor, source);
        Piece piece = chessBoard.get(source);
        validateReachability(source, destination, piece);
        validateDestinationPiece(teamColor, destination);
    }

    private void validateEmpty(final Position source) {
        if (!chessBoard.containsKey(source)) {
            throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
        }
    }

    private void validateColor(final TeamColor teamColor, final Position source) {
        if (!chessBoard.get(source).hasColor(teamColor)) {
            throw new IllegalArgumentException("차례가 맞지 않습니다.");
        }
    }

    private void validateReachability(Position source, Position destination, Piece piece) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 도착지가 동일합니다.");
        }
        if (!piece.isMovable(source, destination, generatePiecePositions(source))) {
            throw new IllegalArgumentException("이동 위치까지 이동할 수 없습니다.");
        }
    }

    private Set<Position> generatePiecePositions(final Position excluded) {
        return chessBoard.keySet().stream()
                .filter(key -> !key.equals(excluded))
                .collect(Collectors.toSet());
    }

    private void validateDestinationPiece(TeamColor teamColor, Position destination) {
        if (isAllyPieceExistOnDestination(teamColor, destination)) {
            throw new IllegalArgumentException("이동 위치에 아군 기물이 존재합니다.");
        }
    }

    private boolean isAllyPieceExistOnDestination(TeamColor teamColor, Position destination) {
        return isPieceExist(destination) && (chessBoard.get(destination).hasColor(teamColor));
    }

    private Optional<Piece> getCaughtPiece(Position destination) {
        if (!chessBoard.containsKey(destination)) {
            return Optional.empty();
        }
        return Optional.of(chessBoard.get(destination));
    }

    private boolean isPieceExist(Position position) {
        return chessBoard.containsKey(position);
    }

    public double calculateScoreOf(TeamColor teamColor) {
        Map<Position, Piece> teamPieces = chessBoard.entrySet().stream()
                .filter(entry -> entry.getValue().hasColor(teamColor))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

        double totalPieceValue = teamPieces.values().stream()
                .mapToDouble(Piece::value)
                .sum();

        return totalPieceValue - calcDuplicatedPawnPenalty(teamPieces);
    }

    private double calcDuplicatedPawnPenalty(Map<Position, Piece> teamPieces) {
        Map<File, Long> pawnCounts = teamPieces.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .collect(Collectors.groupingBy(entry -> entry.getKey().file(), Collectors.counting()));

        int duplicatedPawnCount = pawnCounts.values().stream()
                .filter(count -> count > 1)
                .mapToInt(Long::intValue)
                .sum();

        return duplicatedPawnCount * DUPLICATED_PAWN_PENALTY_RATE;
    }

    public Map<Position, Piece> getPositionsOfPieces() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public int save(TeamColor currentPlayingTeam) {
        PieceDao pieceDao = new PieceDao(new DBConnector());
        pieceDao.removeAllPieces();
        List<PieceDto> pieceDtos = chessBoard.entrySet().stream()
                .map(entry -> PieceDto.of(entry.getKey(), entry.getValue()))
                .toList();

        pieceDao.addAll(pieceDtos);

        GameDao gameDao = new GameDao(new DBConnector());
        gameDao.removeAllGames();
        int gameId = gameDao.addGame();
        gameDao.updateTurn(gameId, currentPlayingTeam);

        return gameId;
    }
}
