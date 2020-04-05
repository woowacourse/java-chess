package chess.domain.board;

import chess.dao.BoardDAO;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.exceptions.InvalidInputException;

import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class Board {
    private static final int PAWN_OVERLAP_CONSTANT = 1;

    private BoardDAO boardDAO;
    private Side turn;

    public Board(final BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
        this.turn = Side.WHITE;
    }

    public void initialize() throws SQLException {
        boardDAO.placeInitialPieces();
    }

    public Optional<Piece> findPieceOn(final Position position) throws SQLException {
        return boardDAO.findPieceOn(position);
    }

    public Path generatePath(final Position start, final Position end) throws SQLException {
        Map<Position, Piece> allPieces = boardDAO.findAllPieces();
        return new Path(allPieces.keySet()
                .stream()
                .filter(position -> position.inBetween(start, end) || position == start || position == end)
                .collect(toMap(Function.identity(), allPieces::get)), start, end);
    }

    public void move(final Position start, final Position end) throws SQLException {
        if (start == null || end == null) {
            throw new InvalidInputException();
        }

        Piece startPiece = getPiece(boardDAO.findPieceOn(start));
        if (!startPiece.is(turn)) {
            return;
        }
        Path path = generatePath(start, end);

        checkMovable(startPiece, path);
        boardDAO.placePieceOn(end, startPiece);
        boardDAO.removePieceOn(start);
        turn = turn.negate();
    }

    public List<Position> findMovablePositions(final Position start) throws SQLException {
        if (start == null) {
            throw new InvalidInputException();
        }

        Piece startPiece = getPiece(boardDAO.findPieceOn(start));
        if (!startPiece.is(turn)) {
            return new ArrayList<>();
        }

        List<Position> movablePositions = new ArrayList<>();
        for (Position position : Position.getAllPositions()) {
            if (startPiece.isMovable(generatePath(start, position))) {
                movablePositions.add(position);
            }
        }
        return movablePositions;
    }

    private Piece getPiece(final Optional<Piece> piece) {
        if (!piece.isPresent()) {
            throw new InvalidInputException();
        }
        return piece.get();
    }

    private void checkMovable(Piece piece, Path path) {
        if (!piece.isMovable(path)) {
            throw new InvalidInputException();
        }
    }

    public long count(final Type type, final Side side) throws SQLException {
        return boardDAO.findAllPieces().values()
                .stream()
                .filter(piece -> piece.is(type, side))
                .count();
    }

    public int countPawnsOnSameColumn(final Side side) throws SQLException {
        List<Position> pawnPositions = getPawnPositions(side);

        Set<Column> pawnColumns = pawnPositions.stream()
                .map(position -> position.getColumn())
                .collect(toSet());

        if (pawnColumns.size() == pawnPositions.size()) {
            return 0;
        }
        return pawnPositions.size() - pawnColumns.size() + PAWN_OVERLAP_CONSTANT;
    }

    private List<Position> getPawnPositions(final Side side) throws SQLException {
        return boardDAO.findAllPieces().entrySet()
                .stream()
                .filter(entry -> entry.getValue().is(Type.PAWN, side))
                .map(entry -> entry.getKey())
                .collect(toList());
    }
}
