package chess.domain;

import chess.dto.RoomNamesDto;

import java.util.List;
import java.util.stream.Collectors;

public class Rooms {

    List<Room> rooms;

    public Rooms(List<RoomNamesDto> roomNames) {
        this.rooms = setRooms(roomNames);
    }

    private List<Room> setRooms(List<RoomNamesDto> roomNames) {
        return roomNames.stream()
            .map(name -> new Room(name.getName(), Game.continueGame(name.getName())))
            .collect(Collectors.toList());
    }

    public void addRoom(String name) {
        Room newRoom = new Room(name, Game.newGame());
        rooms.add(newRoom);
    }

    public Game findGame(String name) {
        return rooms.stream()
            .filter(room -> room.isSameName(name))
            .findAny()
            .get()
            .game();
    }
}
