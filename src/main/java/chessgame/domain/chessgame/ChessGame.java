package chessgame.domain.chessgame;

import chessgame.domain.coordinate.Coordinate;
import chessgame.domain.piece.Piece;
import chessgame.dto.GameRoomDto;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private Camp turn;
    private final Result result;
    private final long roomId;

    public ChessGame(final Map<Coordinate, Piece> board, final GameRoomDto gameRoomDto) {
        this.board = new Board(board);
        this.roomId = gameRoomDto.getRoomId();
        this.turn = Camp.valueOf(gameRoomDto.getTurn());
        this.result = new Result();
    }

    public boolean move(final Coordinate startCoordinate, final Coordinate endCoordinate) {
        checkTurn(startCoordinate);
        boolean isKing = board.move(startCoordinate, endCoordinate);
        changeTurn();
        return isKing;
    }

    private void checkTurn(final Coordinate coordinate) {
        if (board.checkCamp(coordinate, turn)) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 현재 차례가 아닌 팀의 기물입니다.");
    }

    private void changeTurn() {
        turn = turn.change();
    }

    public Map<Camp, Double> getStatus() {
        Map<Camp, Double> status = new HashMap<>();
        status.put(Camp.WHITE, result.calculateTeamAt(board.getBoard(), Camp.WHITE));
        status.put(Camp.BLACK, result.calculateTeamAt(board.getBoard(), Camp.BLACK));
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public Camp getTurn() {
        return turn;
    }

    public long getRoomId() {
        return roomId;
    }
}
