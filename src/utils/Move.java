package utils;

import static utils.SquareUtils.*;

public record Move(String origin, String destination) {
    public Move {
        if (!isValid(origin) || !isValid(destination)) {
            throw new IllegalArgumentException("Invalid square name");
        }
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", origin, destination);
    }
}
