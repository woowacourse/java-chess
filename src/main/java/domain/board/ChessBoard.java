package domain.board;

import database.BoardDao;
import database.DatabaseConnector;
import domain.board.piece.Camp;
import domain.board.piece.Piece;
import domain.board.piece.PieceType;
import domain.path.Path;
import domain.path.PieceMove;
import domain.path.direction.Direction;
import domain.path.location.Column;
import domain.path.location.Location;
import domain.path.location.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ChessBoard {

    private static final int MAX_RANGE = 8;
    private static final double PAWN_IN_SAME_COLUMN_PENALTY = 0.5;
    private static final int ONE_KING_EXIST = 1;
    
    private final Map<Location, Piece> board = new HashMap<>();
    private Turn turn = Turn.white();

    public void makeBoard() {
        BoardDao boardDao = new BoardDao(new DatabaseConnector());
        if (boardDao.isHistoryExist()) {
            boardDao.loadBoard(board);
            turn = boardDao.loadTurn();
            return;
        }
        initializeBoard();
    }

    public void initializeBoard() {
        for (int row = 0; row < MAX_RANGE; row++) {
            initializeRow(row);
        }
        turn = Turn.white();
        BoardDao boardDao = new BoardDao(new DatabaseConnector());
        boardDao.insertBoard(board);
    }

    private void initializeRow(final int rowValue) {
        for (int columnValue = 0; columnValue < MAX_RANGE; columnValue++) {
            final Location currentLocation = Location.of(
                Row.valueOf(rowValue), Column.valueOf(columnValue)
            );
            board.put(currentLocation, findInitialPiece(currentLocation));
        }
    }

    private Piece findInitialPiece(final Location location) {
        final Optional<Piece> initialPiece = InitialBoardPieces.find(location);
        return initialPiece.orElseGet(Piece::empty);
    }

    public void move(final Location start, final Location end) {
        final Piece startPiece = board.get(start);
        checkTurn(startPiece);
        final PieceMove pieceMove = new PieceMove(start, end);
        Path path = new Path(pieceMove, collectPiecesInPath(pieceMove));
        startPiece.validatePath(path);
        board.put(end, board.get(start));
        board.put(start, Piece.empty());
        turn = turn.next();
    }

    private void checkTurn(Piece piece) {
        if (turn.isTurn(piece.getCamp())) {
            return;
        }
        if (piece.getCamp() == Camp.NONE) {
            throw new IllegalArgumentException("빈 곳 입니다.");
        }
        throw new IllegalArgumentException("현재 다른 진영 턴입니다.");
    }

    private List<Piece> collectPiecesInPath(final PieceMove pieceMove) {
        final List<Piece> piecesInPath = new ArrayList<>();
        final Location end = pieceMove.getEnd();
        final Direction direction = Direction.find(pieceMove.getColumnDiff(), pieceMove.getRowDiff());
        Location current = pieceMove.getStart();
        while (!current.equals(end)) {
            piecesInPath.add(board.get(current));
            current = current.addDirectionOnce(direction);
        }
        piecesInPath.add(board.get(end));
        return piecesInPath;
    }

    public double getBlackScore() {
        final double totalScore = board.values().stream()
            .filter(piece -> piece.getCamp() == Camp.BLACK)
            .mapToDouble(Piece::getScore)
            .sum();
        return totalScore - (countBlackPawnsInSameColumn() * PAWN_IN_SAME_COLUMN_PENALTY);
    }

    public double getWhiteScore() {
        final double totalScore = board.values().stream()
            .filter(piece -> piece.getCamp() == Camp.WHITE)
            .mapToDouble(Piece::getScore)
            .sum();
        return totalScore - (countWhitePawnsInSameColumn() * PAWN_IN_SAME_COLUMN_PENALTY);
    }

    private int countBlackPawnsInSameColumn() {
        List<Location> locations = board.entrySet().stream()
            .filter(entry -> isBlackPawn(entry.getValue()))
            .map(Entry::getKey)
            .collect(Collectors.toList());
        return checkPawnsInColumn(locations);
    }

    private boolean isBlackPawn(Piece piece) {
        return piece.getType() == PieceType.PAWN && piece.getCamp() == Camp.BLACK;
    }

    private int checkPawnsInColumn(List<Location> locations) {
        Map<Integer, Integer> pawnsInColumn = new HashMap<>();
        for (Location location : locations) {
            pawnsInColumn.merge(location.getCol(), 1, Integer::sum);
        }
        return pawnsInColumn.values().stream()
            .filter(count -> count > 1)
            .mapToInt(Integer::intValue)
            .sum();
    }

    private int countWhitePawnsInSameColumn() {
        List<Location> locations = board.entrySet().stream()
            .filter(entry -> isWhitePawn(entry.getValue()))
            .map(Entry::getKey)
            .collect(Collectors.toList());
        return checkPawnsInColumn(locations);
    }

    private boolean isWhitePawn(Piece piece) {
        return piece.getType() == PieceType.PAWN && piece.getCamp() == Camp.WHITE;
    }

    public boolean isOneKingExist() {
        return board.values().stream()
            .filter(piece -> piece.getType() == PieceType.KING)
            .count() == ONE_KING_EXIST;
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Turn getTurn() {
        return turn;
    }
}
