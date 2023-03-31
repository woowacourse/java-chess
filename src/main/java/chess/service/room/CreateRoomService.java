package chess.service.room;

import chess.repository.chess.ChessGameRepository;

public class CreateRoomService {

    private final ChessGameRepository chessGameRepository;

    public CreateRoomService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public int createRoom(int userId) {
        return chessGameRepository.create(userId);
    }
}
