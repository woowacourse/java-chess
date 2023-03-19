package chess.game.state;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;

public class PieceMoveState extends ChessGameState {
    private final Coordinate sourceCoordinate;
    private final Coordinate destinationCoordinate;
    
    public PieceMoveState(ChessBoard chessBoard, Team currentOrderTeam, String sourceCoordinate, String destinationCoordinate) {
        this(chessBoard, currentOrderTeam, new Coordinate(split(sourceCoordinate)), new Coordinate(split(destinationCoordinate)));
    }
    
    public PieceMoveState(ChessBoard chessBoard, Team currentOrderTeam, Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        super(chessBoard, currentOrderTeam);
        this.sourceCoordinate = sourceCoordinate;
        this.destinationCoordinate = destinationCoordinate;
    }
    
    private static String[] split(String coordinate) {
        return coordinate.split("");
    }
    
    @Override
    public ChessGameState next() {
        chessBoard.move(sourceCoordinate, destinationCoordinate, currentOrderTeam);
        return new OutputChessBoardState(chessBoard, currentOrderTeam.nextTeam());
    }
}
