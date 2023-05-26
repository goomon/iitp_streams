package org.example.model;

import lombok.*;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CombinedDevice {
    private ChestDeviceFeature chestDevice;
    private WristDeviceFeature wristDevice;
}
