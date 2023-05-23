package org.example.model;

import lombok.*;

import java.util.List;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChestDevice extends Device {
    private List<Integer> resp;
    private List<Integer> ecg;
    private List<Integer> eda;
    private List<Integer> emg;
    private List<Integer> accX;
    private List<Integer> accY;
    private List<Integer> accZ;

    public static ChestDevice combine(ChestDevice accumulator, ChestDevice data) {
        for (Integer record : data.getResp()) {
            accumulator.getResp().add(record);
        }
        for (Integer record : data.getEcg()) {
            accumulator.getEcg().add(record);
        }
        for (Integer record : data.getEda()) {
            accumulator.getEda().add(record);
        }
        for (Integer record : data.getEmg()) {
            accumulator.getEmg().add(record);
        }
        for (Integer record : data.getAccX()) {
            accumulator.getAccX().add(record);
        }
        for (Integer record : data.getAccY()) {
            accumulator.getAccY().add(record);
        }
        for (Integer record : data.getAccZ()) {
            accumulator.getAccZ().add(record);
        }
        return accumulator;
    }
}
