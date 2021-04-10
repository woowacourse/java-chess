package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Room;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.dto.ChessGameDTO;
import chess.dto.RoomsDTO;
import chess.repository.ChessRepository;
import com.google.gson.Gson;

import java.util.List;

public class ChessGameService {
    private ChessRepository chessRepository;
    private Gson gson;

    public ChessGameService(final ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
        this.gson = new Gson();
    }

    public ChessGameDTO createChessGame() {
        ChessGame newChessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
        chessRepository.createChessGame(newChessGame);
        return ChessGameDTO.from(newChessGame);
    }

    public ChessGameDTO refreshChessGame(final String roomId) {
        ChessGame newChessGame = new ChessGame(new BlackTeam(), new WhiteTeam());
        chessRepository.saveChessGameFromRoom(roomId, newChessGame);
        return ChessGameDTO.from(newChessGame);
    }

    public ChessGameDTO loadChessGame(final String roomId) {
        ChessGame chessGame = chessRepository.loadChessGameFromRoom(roomId);
        return ChessGameDTO.from(chessGame);
    }

    public void selectPiece(final String roomId, final String selected) {
        Position selectedPosition = Position.of(selected);
        ChessGame chessGame = chessRepository.loadChessGameFromRoom(roomId);

        boolean havePiece = chessGame.havePieceInCurrentTurn(selectedPosition);

        if (!havePiece) {
            throw new IllegalArgumentException("잘못된 선택 입니다.");
        }
    }

    public ChessGameDTO moveChessGame(final String roomId, final String selected, final String target) {
        Position selectedPosition = Position.of(selected);
        Position targetPosition = Position.of(target);

        ChessGame chessGame = chessRepository.loadChessGameFromRoom(roomId);
        boolean isSuccess = chessGame.move(selectedPosition, targetPosition);
        if (isSuccess) {
            chessRepository.saveChessGameFromRoom(roomId, chessGame);
            return ChessGameDTO.from(chessGame);
        }

        throw new IllegalArgumentException("움직일 수 없습니다.");

    }

    public RoomsDTO createRoom(final String roomReqData) {
        chessRepository.createRoom(roomReqData);
        List<Room> rooms = chessRepository.getTotalRoom();
        return RoomsDTO.from(rooms);
    }

    public RoomsDTO getTotalRoom() {
        List<Room> rooms = chessRepository.getTotalRoom();
        return RoomsDTO.from(rooms);
    }
}
