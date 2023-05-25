package org.example.model;

import lombok.*;

import java.util.List;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WristDevice extends Device {
    private List<Double> bvp;
    private List<Double> eda;
    private List<Double> temp;
    private List<Double> accX;
    private List<Double> accY;
    private List<Double> accZ;

    public static WristDevice combine(WristDevice accumulator, WristDevice data) {
        for (Double record : data.getBvp()) {
            accumulator.getBvp().add(record);
        }
        for (Double record : data.getEda()) {
            accumulator.getEda().add(record);
        }
        for (Double record : data.getTemp()) {
            accumulator.getTemp().add(record);
        }
        for (Double record : data.getAccX()) {
            accumulator.getAccX().add(record);
        }
        for (Double record : data.getAccY()) {
            accumulator.getAccY().add(record);
        }
        for (Double record : data.getAccZ()) {
            accumulator.getAccZ().add(record);
        }
        return accumulator;
    }
}
