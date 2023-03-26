package chess.domain.board;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;
import chess.domain.piece.*;

import java.util.*;
import java.util.stream.Collectors;

public class BlackWhiteChessBoard implements ChessBoard {
    private final Map<Coordinate, Piece> pieces;
    
    private BlackWhiteChessBoard(Map<Coordinate, Piece> pieces) {
        this.pieces = pieces;
    }
    
    public static BlackWhiteChessBoard create() {
        return new BlackWhiteChessBoard(initChessBoard());
    }
    
    private static Map<Coordinate, Piece> initChessBoard() {
        return initCoordinates().stream()
                .collect(Collectors.toMap(
                        coordinate -> coordinate,
                        Piece::from,
                        (firstPiece, secondPiece) -> firstPiece)
                );
    }
    
    private static List<Coordinate> initCoordinates() {
        return Arrays.stream(Row.values())
                .filter(Row::isPieceLine)
                .map(BlackWhiteChessBoard::initLineCoordinates)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private static List<Coordinate> initLineCoordinates(Row row) {
        return Arrays.stream(Column.values())
                .map(column -> new Coordinate(column, row))
                .collect(Collectors.toUnmodifiableList());
    }
    
    @Override
    public void move(String sourceCoordinate, String destinationCoordinate, Team currentTeam) {
        Coordinate fromCoordinate = parseCoordinate(sourceCoordinate);
        Coordinate toCoordinate = parseCoordinate(destinationCoordinate);
        Piece fromPiece = pieceOrEmpty(fromCoordinate);
        
        validateMovable(fromCoordinate, toCoordinate, fromPiece, currentTeam);
        
        pieces.remove(fromCoordinate);
        pieces.put(toCoordinate, fromPiece.movedPiece());
    }
    
    private void validateMovable(Coordinate fromCoordinate, Coordinate toCoordinate, Piece fromPiece, Team currentTeam) {
        validateSource(fromCoordinate, fromPiece, currentTeam);
        
        PieceMovingType pieceMovingType = fromPiece.movingType();
        Set<Coordinate> possibleCoordinates = pieceMovingType.movableRouteSearch(this, fromPiece, fromCoordinate);
        validateDestination(toCoordinate, possibleCoordinates);
    }
    
    private void validateSource(Coordinate fromCoordinate, Piece fromPiece, Team currentTeam) {
        if (isEmpty(fromCoordinate)) {
            throw new IllegalArgumentException("해당 출발 좌표는 비어있는 좌표입니다.");
        }
        
        if (!fromPiece.isTeam(currentTeam)) {
            throw new IllegalArgumentException("해당 기물 팀의 순서가 아닙니다.");
        }
    }
    
    private void validateDestination(Coordinate toCoordinate, Set<Coordinate> possibleCoordinates) {
        if (isImmovableToCoordinate(toCoordinate, possibleCoordinates)) {
            throw new IllegalArgumentException("해당 도착 좌표는 이동할 수 없는 좌표입니다.");
        }
    }
    
    private boolean isImmovableToCoordinate(Coordinate toCoordinate, Set<Coordinate> possibleCoordinates) {
        return possibleCoordinates.stream()
                .noneMatch(coordinate -> coordinate.equals(toCoordinate));
    }
    
    private Coordinate parseCoordinate(String coordinate) {
        String[] splitedCoordinate = coordinate.split("");
        return new Coordinate(parseColumn(splitedCoordinate), parseRow(splitedCoordinate));
    }
    
    private Column parseColumn(String[] splitedCoordinate) {
        return Column.from(parseColumnData(splitedCoordinate));
    }
    
    private char parseColumnData(String[] splitedCoordinate) {
        return splitedCoordinate[0].charAt(0);
    }
    
    private Row parseRow(String[] splitedCoordinate) {
        return Row.from(parseRowData(splitedCoordinate));
    }
    
    private int parseRowData(String[] splitedCoordinate) {
        return Integer.parseInt(splitedCoordinate[1]);
    }
    
    @Override
    public boolean isAllyAtCoordinate(Piece piece, Coordinate coordinate) {
        return piece.isSameTeam(pieceOrEmpty(coordinate));
    }
    
    @Override
    public boolean isEnemyAtCoordinate(Piece piece, Coordinate coordinate) {
        return piece.isOppositeTeam(pieceOrEmpty(coordinate));
    }
    
    @Override
    public boolean isEmpty(Coordinate coordinate) {
        return pieceOrEmpty(coordinate).isSameTeam(Empty.getInstance());
    }
    
    @Override
    public double calculateScore(Team team) {
        return pieces.keySet().stream()
                .map(this::pieceOrEmpty)
                .filter(piece -> piece.isSameTeam(team))
                .map(Piece::pieceType)
                .mapToDouble(PieceType::score)
                .sum();
    }
    
    @Override
    public boolean isKingDied() {
        return numberOfSurvivingKings() == 1;
    }
    
    private long numberOfSurvivingKings() {
        return pieces.keySet().stream()
                .map(this::pieceOrEmpty)
                .map(Piece::pieceType)
                .filter(pieceType -> pieceType == PieceType.KING)
                .count();
    }
    
    @Override
    public Team teamWithKing() {
        return pieces.keySet().stream()
                .map(this::pieceOrEmpty)
                .filter(piece -> piece.pieceType() == PieceType.KING)
                .map(Piece::team)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("킹이 존재하지 않습니다."));
    }
    
    @Override
    public Team winnerTeam() {
        double whiteScore = calculateScore(Team.WHITE);
        double blackScore = calculateScore(Team.BLACK);
        if (isKingDied()) {
            return teamWithKing();
        }
        
        if (whiteScore > blackScore) {
            return Team.WHITE;
        }
    
        if (whiteScore < blackScore) {
            return Team.BLACK;
        }
        
        return Team.EMPTY;
    }
    
    private Piece pieceOrEmpty(Coordinate nextCoordinate) {
        return pieces.getOrDefault(nextCoordinate, Empty.getInstance());
    }
    
    @Override
    public Map<Coordinate, Piece> pieces() {
        return Collections.unmodifiableMap(pieces);
    }
    
    @Override
    public String toString() {
        return "BlackWhiteChessBoard{" +
                "pieces=" + pieces +
                '}';
    }
}
