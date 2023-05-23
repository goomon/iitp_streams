package org.example.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChestDeviceFeature {
    private String userId;
    private long timestamp;
    private double respMean;
    private double respStd;
    private double respMax;
    private double respMin;
    private double ecgMean;
    private double ecgStd;
    private double ecgMax;
    private double ecgMin;
    private double edaMean;
    private double edaStd;
    private double edaMax;
    private double edaMin;
    private double emgMean;
    private double emgStd;
    private double emgMax;
    private double emgMin;
    private double accXMean;
    private double accXStd;
    private double accXMax;
    private double accXMin;
    private double accYMean;
    private double accYStd;
    private double accYMax;
    private double accYMin;
    private double accZMean;
    private double accZStd;
    private double accZMax;
    private double accZMin;

    public ChestDeviceFeature(ChestDevice chestDeviceWindowed) {
        this.userId = chestDeviceWindowed.getUserId();
        this.timestamp = chestDeviceWindowed.getTimestamp();
        ChestDevice.Value value = chestDeviceWindowed.getValue();
        this.respMean = calcMean(value.getResp());
        this.respStd = calcStd(value.getResp(), respMean);
        this.respMax = Collections.max(value.getResp());
        this.respMin = Collections.min(value.getResp());
        this.ecgMean = calcMean(value.getEcg());
        this.ecgStd = calcStd(value.getEcg(), ecgMean);
        this.ecgMax = Collections.max(value.getEcg());
        this.ecgMin = Collections.min(value.getEcg());
        this.edaMean = calcMean(value.getEda());
        this.edaStd = calcStd(value.getEda(), edaMean);
        this.edaMax = Collections.max(value.getEda());
        this.edaMin = Collections.min(value.getEda());
        this.emgMean = calcMean(value.getEmg());
        this.emgStd = calcStd(value.getEmg(), emgMean);
        this.emgMax = Collections.max(value.getEmg());
        this.emgMin = Collections.min(value.getEmg());
        this.accXMean = calcMean(value.getAccX());
        this.accXStd = calcStd(value.getAccX(), accXMean);
        this.accXMax = Collections.max(value.getAccX());
        this.accXMin = Collections.min(value.getAccX());
        this.accYMean = calcMean(value.getAccY());
        this.accYStd = calcStd(value.getAccY(), accYMean);
        this.accYMax = Collections.max(value.getAccY());
        this.accYMin = Collections.min(value.getAccY());
        this.accZMean = calcMean(value.getAccZ());
        this.accZStd = calcStd(value.getAccZ(), accZMean);
        this.accZMax = Collections.max(value.getAccZ());
        this.accZMin = Collections.min(value.getAccZ());
    }

    private double calcMean(List<Integer> data) {
        return data
                .stream()
                .map(Double::valueOf)
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

    private double calcStd(List<Integer> data, double mean) {
        double variance = data
                .stream()
                .map(x -> Double.valueOf(x) - mean)
                .mapToDouble(x -> x * x)
                .average()
                .orElse(0);
        return Math.sqrt(variance);
    }

    @Override
    public String toString() {
        return "ChestDeviceFeature{" +
                "userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
