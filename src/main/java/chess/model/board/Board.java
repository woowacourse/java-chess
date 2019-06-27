package chess.model.board;

import chess.model.*;
import chess.model.board.vector.Direction;
import chess.model.board.vector.Vector;
import chess.model.gameCreator.BoardCreatingStrategy;
import chess.model.piece.*;

import java.util.*;

import static chess.model.board.vector.Direction.*;

public class Board {
    public static final String WHITE_TEAM = "white";
    public static final String BLACK_TEAM = "black";
    public static final int ROW_SIZE = 8;
    public static final int INITIAL_ROW = 1;
    public static final int COLUMN_SIZE = 8;
    public static final int INITIAL_COLUMN = 1;
    public static final int BLACK_PAWN_ROW = 2;
    public static final int WHITE_PAWN_ROW = 7;
    private static final int HALF_SCORE_STANDARD = 1;
    private static final double HALF_SCORE_OF_PAWN = Pawn.SCORE / 2;
    private static final int TOTAL_COUNT_OF_KING = 2;

    private Map<String, Tile> tiles;

    public Board(BoardCreatingStrategy strategy) {
        if (Objects.isNull(strategy)) {
            throw new NullPointerException();
        }
        this.tiles = strategy.create();
    }

    public void movePiece(List<String> sourceAndTarget) {
        if (Objects.isNull(sourceAndTarget) || sourceAndTarget.isEmpty()) {
            throw new NullPointerException();
        }
        String sourcePosition = sourceAndTarget.get(0);
        String targetPosition = sourceAndTarget.get(1);

        checkMovablePiece(sourceAndTarget, sourcePosition, targetPosition);

        Piece clonedPiece = tiles.get(sourcePosition).clonePiece();

        tiles.put(sourcePosition, new Tile(sourcePosition, null));
        tiles.put(targetPosition, new Tile(targetPosition, clonedPiece));
    }

    private void checkMovablePiece(List<String> sourceAndTarget, String sourcePosition, String targetPosition) {
        if (!tiles.get(sourcePosition).isPiecePresent()) {
            throw new IllegalArgumentException("말을 클릭해야합니다.");
        }
        Coordinate sourceCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(0, 1)));
        Coordinate sourceCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(0).substring(1)));
        Coordinate targetCoordinateX = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(0, 1)));
        Coordinate targetCoordinateY = Coordinate.valueOf(Integer.parseInt(sourceAndTarget.get(1).substring(1)));
        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));

        if (checkPiecePresentInRoute(sourceAndTarget, vector)) {
            throw new IllegalArgumentException("경로에 piece가 있어서 움직일 수 없습니다.");
        }

        String teamOfSourceTilePiece = askTilePieceWhichTeam(sourcePosition);
        String teamOfTargetTilePiece = "";
        if (tiles.get(targetPosition).isPiecePresent()) {
            teamOfTargetTilePiece = askTilePieceWhichTeam(targetPosition);
        }

        if (checkPiecePresentInTarget(sourcePosition) && teamOfSourceTilePiece.equals(teamOfTargetTilePiece)) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로는 움직일 수 없습니다.");
        }

        checkWhenPawn(sourcePosition, targetPosition, vector);
    }


    private boolean checkPiecePresentInRoute(List<String> sourceAndTarget, Vector vector) {
        Tile tile = tiles.get(sourceAndTarget.get(0));
        Route route = tile.findRouteFromPiece(vector);

        return checkEveryRoute(route);
    }

    // TODO: 2019-06-21 파라미터 이름 변경

    private boolean checkEveryRoute(Route route) {
        for (String coordinates : tiles.keySet()) {
            if (route.contains(coordinates) && tiles.get(coordinates).isPiecePresent()) {
                return true;
            }
        }
        return false;
    }

    private String askTilePieceWhichTeam(String coordinate) {
        return tiles.get(coordinate).askPieceWhichTeam();
    }

    private boolean checkPiecePresentInTarget(String coordinate) {
        return tiles.get(coordinate).isPiecePresent();
    }

    private void checkWhenPawn(String sourcePosition, String targetPosition, Vector vector) {
        if (tiles.get(sourcePosition).askPieceIfPawn()) {
            checkWhenDiagonal(targetPosition, vector);
            checkWhenVertical(targetPosition, vector);
        }
    }

    private void checkWhenDiagonal(String targetPosition, Vector vector) {
        if (Direction.isDiagonal(vector.getDirection())) {
            checkPiecePresentWhenDiagonal(targetPosition);
        }
    }

    private void checkPiecePresentWhenDiagonal(String targetPosition) {
        if (!tiles.get(targetPosition).isPiecePresent()) {
            throw new IllegalArgumentException("폰은 상대팀이 있을 경우만 대각선방향으로 움직일 수 있습니다.");
        }
    }

    private void checkWhenVertical(String targetPosition, Vector vector) {
        if (vector.isEqualToDirection(NORTH) || vector.isEqualToDirection(SOUTH)) {
            checkPiecePresentWhenVertical(targetPosition);
        }
    }

    private void checkPiecePresentWhenVertical(String targetPosition) {
        if (tiles.get(targetPosition).isPiecePresent()) {
            throw new IllegalArgumentException("폰은 대각선방향이 아닌 방향으로 움직여 상대팀을 잡을 수 없습니다.");
        }
    }

    public ScoreResult makeScoreResult() {
        List<String> locationsOfWhitePawns = new ArrayList<>();
        List<String> locationsOfBlackPawns = new ArrayList<>();
        double scoreOfWhite = 0;
        double scoreOfBlack = 0;

        collectPawns(locationsOfWhitePawns, locationsOfBlackPawns);

        // 폰 점수계산
        for (int column = INITIAL_COLUMN; column <= COLUMN_SIZE; column++) {
            scoreOfWhite += calculatePawnScore(locationsOfWhitePawns, column);
            scoreOfBlack += calculatePawnScore(locationsOfBlackPawns, column);
        }

        // 폰 이외의 말 점수계산
        for (String location : tiles.keySet()) {
            if (tiles.get(location).isPiecePresent()) {
                Piece piece = tiles.get(location).getPiece();
                scoreOfWhite += calculateNormalPieceScore(piece, WHITE_TEAM);
                scoreOfBlack += calculateNormalPieceScore(piece, BLACK_TEAM);
            }
        }

        return new ScoreResult(scoreOfWhite, scoreOfBlack);
    }

    private void collectPawns(List<String> locationsOfWhitePawns, List<String> locationsOfBlackPawns) {
        for (String currentLocation : tiles.keySet()) {
            if (tiles.get(currentLocation).isPiecePresent()
                    && tiles.get(currentLocation).askPieceIfPawn()
                    && tiles.get(currentLocation).askPieceWhichTeam().equals(WHITE_TEAM)) {
                locationsOfWhitePawns.add(currentLocation);
            }
            if (tiles.get(currentLocation).isPiecePresent()
                    && tiles.get(currentLocation).askPieceIfPawn()
                    && tiles.get(currentLocation).askPieceWhichTeam().equals(BLACK_TEAM)) {
                locationsOfBlackPawns.add(currentLocation);
            }
        }
    }

    private double calculatePawnScore(List<String> locationsOfWhitePawns, int column) {
        int count = 0;
        for (String location : locationsOfWhitePawns) {
            if (location.substring(0, 1).equals(String.valueOf(column))) {
                count++;
            }
        }

        if (count > HALF_SCORE_STANDARD) {
            return (count * HALF_SCORE_OF_PAWN);
        }
        return (count * Pawn.SCORE);

    }

    private double calculateNormalPieceScore(Piece piece, String team) {
        if (!piece.isPawn() && team.equals(piece.askTeamColor())) {
            return piece.getScore();
        }
        return 0;
    }

    public Tile getTile(String coordinates) {
        return tiles.get(coordinates);
    }

    public boolean isRightTurn(String sourceCoordinate, int turn) {
        String turnColor = (turn % ChessGame.COUNT_OF_TEAM == 0) ? BLACK_TEAM : WHITE_TEAM;
        return tiles.get(sourceCoordinate).askPieceWhichTeam().equals(turnColor);

    }

    public List<String> convertToList() {
        List<String> pieces = new ArrayList<>();
        List<String> keys = new ArrayList<>(tiles.keySet());

        keys.sort((a1, a2) -> {
            int coordinateY1 = Integer.parseInt(a1.substring(1, 2));
            int coordinateY2 = Integer.parseInt(a2.substring(1, 2));

            if (coordinateY1 < coordinateY2) {
                return 1;
            } else if (coordinateY1 > coordinateY2) {
                return -1;
            } else if (coordinateY1 == coordinateY2) {
                int coordinateX1 = Integer.parseInt(a1.substring(0, 1));
                int coordinateX2 = Integer.parseInt(a2.substring(0, 1));
                return Integer.compare(coordinateX1, coordinateX2);
            }
            return -1;
        });

        int count = 0;
        String row = "";

        for (String key : keys) {
            count++;
            Tile tile = tiles.get(key);

            if (Objects.isNull(tile.getPiece())) {
                row = row.concat("#");

                if (count % 8 == 0) {
                    pieces.add(row);
                    row = "";
                }
                continue;
            }

            if (tile.getPiece().getScore() == King.SCORE) {
                if (WHITE_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("k");
                }

                if (BLACK_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("K");
                }
            }

            if (tile.getPiece().getScore() == Queen.SCORE) {
                if (WHITE_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("q");
                }

                if (BLACK_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("Q");
                }
            }

            if (tile.getPiece().getScore() == Rook.SCORE) {
                if (WHITE_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("r");
                }

                if (BLACK_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("R");
                }
            }

            if (tile.getPiece().getScore() == Knight.SCORE) {
                if (WHITE_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("n");
                }

                if (BLACK_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("N");
                }
            }

            if (tile.getPiece().getScore() == Bishop.SCORE) {
                if (WHITE_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("b");
                }

                if (BLACK_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("B");
                }
            }

            if (tile.getPiece().getScore() == Pawn.SCORE) {
                if (WHITE_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("p");
                }

                if (BLACK_TEAM.equals(tile.askPieceWhichTeam())) {
                    row = row.concat("P");
                }
            }

            if (count % 8 == 0) {
                pieces.add(row);
                row = "";
            }
        }

        return pieces;
    }

    public boolean checkKingAlive() {
        int kingCount = 0;
        for (String coordinate : tiles.keySet()) {
            if (tiles.get(coordinate).isPiecePresent() && tiles.get(coordinate).askIfKing()) {
                kingCount++;
            }

        }
        return kingCount == TOTAL_COUNT_OF_KING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tiles);
    }
}
