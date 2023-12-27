package com.texo.apitest.enums;

public enum RecordPositions {

    YEAR(0), TITLE(1), STUDIOS(2), PRODUCERS(3), WINNER(4);

    public final int position;

    RecordPositions(int position) {
        this.position = position;
    }
}
