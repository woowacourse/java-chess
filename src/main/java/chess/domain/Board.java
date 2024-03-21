package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.strategy.BishopMoveStrategy;
import chess.domain.strategy.BlackPawnFirstMoveStrategy;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.KingMoveStrategy;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.QueenMoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> board;

    public Board() {
        Map<Position, Piece> board = new HashMap<>();
        initialize(board);

        this.board = board;
    }

    private void initialize(Map<Position, Piece> board) {
        placeEmptyPieces(board, createPieceLocationsByIndex(0, 64), Team.NONE);
    }

    public void placeInitialPieces() {
        placeBlackPieces();
        placeWhitePieces();
    }

    private void placeBlackPieces() {
        placeRookPieces(List.of("a8", "h8"), Team.BLACK);
        placeKnightPieces(List.of("b8", "g8"), Team.BLACK);
        placeBishopPieces(List.of("c8", "f8"), Team.BLACK);
        placeQueenAndKingPiece("d8", "e8", Team.BLACK);
        placePawnPieces(createPieceLocationsByIndex(48, 56), Team.BLACK, new BlackPawnFirstMoveStrategy());
    }

    private void placeWhitePieces() {
        placeRookPieces(List.of("a1", "h1"), Team.WHITE);
        placeKnightPieces(List.of("b1", "g1"), Team.WHITE);
        placeBishopPieces(List.of("c1", "f1"), Team.WHITE);
        placeQueenAndKingPiece("d1", "e1", Team.WHITE);
        placePawnPieces(createPieceLocationsByIndex(8, 16), Team.WHITE, new WhitePawnFirstMoveStrategy());
    }

    private void placeRookPieces(List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Rook(pieceInfo, new RookMoveStrategy()));
        }
    }

    private void placeKnightPieces(List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Knight(pieceInfo, new KnightMoveStrategy()));
        }
    }

    private void placeBishopPieces(List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Bishop(pieceInfo, new BishopMoveStrategy()));
        }
    }

    private void placeQueenAndKingPiece(String queenLocation, String kingLocation,
                                        Team team) {
        PieceInfo queenInfo = new PieceInfo(Position.of(queenLocation), team);
        board.put(queenInfo.getPosition(), new Queen(queenInfo, new QueenMoveStrategy()));

        PieceInfo kingInfo = new PieceInfo(Position.of(kingLocation), team);
        board.put(kingInfo.getPosition(), new King(kingInfo, new KingMoveStrategy()));
    }

    private void placePawnPieces(List<String> locations, Team team,
                                 MoveStrategy moveStrategy) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new Pawn(pieceInfo, moveStrategy));
        }
    }

    private void placeEmptyPieces(Map<Position, Piece> board, List<String> locations, Team team) {
        for (String location : locations) {
            PieceInfo pieceInfo = new PieceInfo(Position.of(location), team);
            board.put(pieceInfo.getPosition(), new EmptyPiece(pieceInfo, new EmptyMoveStrategy()));
        }
    }

    private List<String> createPieceLocationsByIndex(int startIndex, int endIndex) {
        List<String> positions = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            positions.add("" + (char) (i % 8 + 'a') + (char) (i / 8 + '1'));
        }

        return positions;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void placePiece(Position currentPosition, Piece piece) {
        board.put(currentPosition, piece);
    }

    public boolean checkObstacleInRange(Position currentPosition, Position newPosition) {
        List<Position> internalPositions = currentPosition.getInternalPositions(newPosition);

        return internalPositions.stream()
                .map(board::get)
                .anyMatch(piece -> piece.getType() != PieceType.EMPTY);
    }

    public boolean checkPieceExist(Position position) {
        Piece piece = board.get(position);

        return piece.getType() != PieceType.EMPTY;
    }

    public boolean checkSameTeamPieceExist(Team currentTeam, Position otherPosition) {
        Piece otherPiece = board.get(otherPosition);

        return otherPiece.isSameTeam(currentTeam);
    }

    public boolean isSameTeamFromPosition(Position position, Team team) {
        Piece piece = board.get(position);
        return piece.isSameTeam(team);
    }

    public void movePieceAndRenewBoard(Position source, Position target) {
        Piece piece = board.get(source);

        Piece movedPiece = movePiece(source, target, piece);

        PieceInfo pieceInfo = movedPiece.getPieceInfo();
        board.put(source, new EmptyPiece(new PieceInfo(source, Team.NONE), new EmptyMoveStrategy()));
        board.put(pieceInfo.getPosition(), movedPiece);
    }

    private Piece movePiece(Position source, Position target, Piece piece) {
        return piece.move(target,
                checkObstacleInRange(source, target),
                checkPieceExist(target),
                checkSameTeamPieceExist(piece.getTeam(), target));
    }
}
