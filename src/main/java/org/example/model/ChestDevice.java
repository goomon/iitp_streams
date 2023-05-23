package org.example.model;

import lombok.*;

import java.util.List;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChestDevice extends Device {
    private Value value;

    @ToString
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {
        private List<Integer> resp;
        private List<Integer> ecg;
        private List<Integer> eda;
        private List<Integer> emg;
        private List<Integer> accX;
        private List<Integer> accY;
        private List<Integer> accZ;
    }

    public static ChestDevice combine(ChestDevice accumulator, ChestDevice data) {
        Value value = data.getValue();
        for (Integer record : value.getResp()) {
            accumulator.getValue().getResp().add(record);
        }
        for (Integer record : value.getEcg()) {
            accumulator.getValue().getEcg().add(record);
        }
        for (Integer record : value.getEda()) {
            accumulator.getValue().getEda().add(record);
        }
        for (Integer record : value.getEmg()) {
            accumulator.getValue().getEmg().add(record);
        }
        for (Integer record : value.getAccX()) {
            accumulator.getValue().getAccX().add(record);
        }
        for (Integer record : value.getAccY()) {
            accumulator.getValue().getAccY().add(record);
        }
        for (Integer record : value.getAccZ()) {
            accumulator.getValue().getAccZ().add(record);
        }
        return accumulator;
    }
}
