package org.example.model;

import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Device {
    private String userId;
    private long timestamp;
}
