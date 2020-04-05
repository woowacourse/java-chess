package dto;

import chess.location.Col;
import chess.location.Location;
import chess.location.Row;

public class LocationDTO {
    private final Location location;

    public LocationDTO(String row, String col) {
        Row rowValue = Row.of(Integer.parseInt(row));
        Col colValue = Col.of(col.charAt(0));

        location = new Location(rowValue, colValue);
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return location.toString();
    }
}
