package chess.domain.game;

import chess.dao.*;
import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.position.Column;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.RoomDto;
import chess.dto.StatusDto;

import java.util.*;
import java.util.stream.Collectors;

public final class GameService {

    private static final int KING_TOTAL_COUNT = 2;
    private static final double PAWN_PENALTY_SCORE = 0.5;
    private static final int SINGLE_COUNT = 1;

    private final BoardDao<Board> boardDao;
    private final PositionDao<Position> chessPositionDao;
    private final PieceDao<Piece> chessPieceDao;

    public GameService() {
        this.boardDao = new ChessBoardDao(new ChessConnectionManager());
        this.chessPositionDao = new ChessPositionDao(new ChessConnectionManager());
        this.chessPieceDao = new ChessPieceDao(new ChessConnectionManager());
    }

    public Board saveBoard(final Board board, final Initializer initializer) {
        return boardDao.init(board, initializer.initialize());
    }

    public void move(final int roomId, final Position sourceRawPosition, final Position targetRawPosition) {
        Position sourcePosition = chessPositionDao.getByColumnAndRowAndBoardId(sourceRawPosition.getColumn(), sourceRawPosition.getRow(), roomId);
        Position targetPosition = chessPositionDao.getByColumnAndRowAndBoardId(targetRawPosition.getColumn(), targetRawPosition.getRow(), roomId);
        validateTurn(roomId, sourcePosition);
        validateNotEquals(sourcePosition, targetPosition);
        final Piece sourcePiece = getPieceByPosition(sourcePosition);
        movePiece(roomId, sourcePosition, targetPosition, sourcePiece);
    }

    private void validateTurn(final int roomId, final Position sourcePosition) {
        final Optional<Piece> wrappedPiece = chessPieceDao.findByPositionId(chessPositionDao.getIdByColumnAndRowAndBoardId(sourcePosition.getColumn(), sourcePosition.getRow(), roomId));
        wrappedPiece.ifPresent(piece -> validateCorrectTurn(roomId, piece));
    }

    private void validateNotEquals(final Position sourcePosition, final Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("출발지와 목적지가 동일할 수 없습니다.");
        }
    }

    private Piece getPieceByPosition(Position sourcePosition) {
        final Optional<Piece> optionalPiece = chessPieceDao.findByPositionId(sourcePosition.getId());
        if (optionalPiece.isEmpty()) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        return optionalPiece.get();
    }

    private void movePiece(final int roomId, final Position sourcePosition, final Position targetPosition, final Piece sourcePiece) {
        validateMovement(sourcePosition, targetPosition, sourcePiece);
        final Optional<Piece> targetPiece = chessPieceDao.findByPositionId(targetPosition.getId());
        targetPiece.ifPresent(piece -> validateTargetNotSameColor(sourcePiece, piece));
        validatePawnMovement(sourcePosition, targetPosition, sourcePiece, targetPiece);
        validatePathEmpty(roomId, sourcePosition, targetPosition);
        updateMovingPiecePosition(sourcePosition, targetPosition, targetPiece);
        changeTurn(roomId);
    }

    private void validateMovement(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        if (!sourcePiece.isMovable(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("기물의 움직임이 아닙니다.");
        }
    }

    private void validateCorrectTurn(int roomId, Piece piece) {
        final Color turn = boardDao.getById(roomId).getTurn();
        if (!piece.isSameColor(turn)) {
            throw new IllegalArgumentException("지금은 " + turn.value() + "의 턴입니다.");
        }
    }

    private void changeTurn(int roomId) {
        boardDao.updateTurn(Color.opposite(boardDao.getById(roomId).getTurn()), roomId);
    }

    public BoardDto getBoard(int roomId) {
        final Board board = boardDao.getById(roomId);
        final Map<Position, Piece> allPositionsAndPieces = chessPositionDao.findAllPositionsAndPieces(roomId);
        Map<String, Piece> pieces = mapPositionToString(allPositionsAndPieces);
        return BoardDto.of(pieces, board.getRoomTitle(), board.getMembers().get(0), board.getMembers().get(1));
    }

    private Map<String, Piece> mapPositionToString(Map<Position, Piece> allPositionsAndPieces) {
        return allPositionsAndPieces.keySet().stream()
                .collect(Collectors.toMap(
                        position -> position.getRow().value() + position.getColumn().name(),
                        allPositionsAndPieces::get));
    }

    private void updateMovingPiecePosition(Position sourcePosition, Position targetPosition, Optional<Piece> targetPiece) {
        if (targetPiece.isPresent()) {
            chessPieceDao.deleteByPositionId(targetPosition.getId());
        }
        chessPieceDao.updatePositionId(sourcePosition.getId(), targetPosition.getId());
    }

    private void validateTargetNotSameColor(final Piece source, final Piece target) {
        if (source.isSameColorPiece(target)) {
            throw new IllegalArgumentException("목적지에 같은 색의 기물이 있으면 움직일 수 없습니다.");
        }
    }

    private void validatePathEmpty(final int roomId, final Position source, final Position target) {
        final Direction direction = Direction.calculate(source, target);
        if (!direction.isIgnore()) {
            List<Position> positions = source.calculatePath(target, direction);
            List<Position> realPositions = chessPositionDao.getPaths(positions, roomId);
            validatePiecesNotExistOnPath(realPositions);
        }
    }

    private void validatePiecesNotExistOnPath(final List<Position> positions) {
        for (Position position : positions) {
            validatePieceNotExist(position);
        }
    }

    private void validatePieceNotExist(final Position position) {
        if (chessPieceDao.findByPositionId(position.getId()).isPresent()) {
            throw new IllegalArgumentException("이동경로에 다른 기물이 있으면 움직일 수 없습니다.");
        }
    }

    private void validatePawnMovement(final Position sourcePosition, final Position targetPosition, final Piece sourcePiece, final Optional<Piece> targetPiece) {
        if (sourcePiece.isPawn() && Direction.calculate(sourcePosition, targetPosition).isDiagonal()) {
            checkPawnTargetExist(targetPiece);
        }
        if (sourcePiece.isPawn() && Direction.calculate(sourcePosition, targetPosition).isVertical()) {
            checkPawnTargetNotExist(targetPiece);
        }
    }

    private void checkPawnTargetExist(final Optional<Piece> targetPiece) {
        if (targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 상대기물이 목적지에 존재해야 대각선으로 움직일 수 있습니다.");
        }
    }

    private void checkPawnTargetNotExist(final Optional<Piece> targetPiece) {
        if (targetPiece.isPresent()) {
            throw new IllegalArgumentException("목적지에 다른 기물이 존재하면 움직일 수 없습니다.");
        }
    }

    public boolean isEnd(int roomId) {
        final boolean kingDead = chessPieceDao.getAllByBoardId(roomId)
                .stream()
                .filter(Piece::isKing)
                .count() != KING_TOTAL_COUNT;
        if (kingDead) {
            boardDao.deleteById(roomId);
        }
        return kingDead;
    }

    public List<Piece> existPieces(int roomId) {
        return chessPieceDao.getAllByBoardId(roomId);
    }

    public double calculateScore(int roomId, final Color color) {
        return calculateDefaultScore(roomId, color) - PAWN_PENALTY_SCORE * countPawnsOnSameColumns(roomId, color);
    }

    private double calculateDefaultScore(int roomId, Color color) {
        return existPieces(roomId)
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private int countPawnsOnSameColumns(int roomId, final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> chessPieceDao.countPawnsOnSameColumn(roomId, column, color))
                .filter(count -> count > SINGLE_COUNT)
                .sum();
    }

    public StatusDto status(int roomId) {
        return new StatusDto(Arrays.stream(Color.values())
                .collect(Collectors.toMap(Enum::name, color -> calculateScore(roomId, color))));
    }

    public void end(int roomId) {
        boardDao.deleteById(roomId);
    }

    public BoardsDto getBoards() {
        List<RoomDto> boardsDto = new ArrayList<>();
        List<Board> boards = boardDao.findAll();
        for (Board board : boards) {
            boardsDto.add(new RoomDto(board.getId(), board.getRoomTitle(), board.getMembers().get(0), board.getMembers().get(1)));
        }
        return new BoardsDto(boardsDto);
    }
}
