package org.example.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    PING(1),
    PONG(2),

    ;

    private final int type;
}
