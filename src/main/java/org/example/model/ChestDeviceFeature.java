package org.example.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChestDeviceFeature {
    private String userId;
    private long recordId;
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
        this.recordId = chestDeviceWindowed.getRecordId();
        this.timestamp = chestDeviceWindowed.getTimestamp();
        this.respMean = calcMean(chestDeviceWindowed.getResp());
        this.respStd = calcStd(chestDeviceWindowed.getResp(), respMean);
        this.respMax = Collections.max(chestDeviceWindowed.getResp());
        this.respMin = Collections.min(chestDeviceWindowed.getResp());
        this.ecgMean = calcMean(chestDeviceWindowed.getEcg());
        this.ecgStd = calcStd(chestDeviceWindowed.getEcg(), ecgMean);
        this.ecgMax = Collections.max(chestDeviceWindowed.getEcg());
        this.ecgMin = Collections.min(chestDeviceWindowed.getEcg());
        this.edaMean = calcMean(chestDeviceWindowed.getEda());
        this.edaStd = calcStd(chestDeviceWindowed.getEda(), edaMean);
        this.edaMax = Collections.max(chestDeviceWindowed.getEda());
        this.edaMin = Collections.min(chestDeviceWindowed.getEda());
        this.emgMean = calcMean(chestDeviceWindowed.getEmg());
        this.emgStd = calcStd(chestDeviceWindowed.getEmg(), emgMean);
        this.emgMax = Collections.max(chestDeviceWindowed.getEmg());
        this.emgMin = Collections.min(chestDeviceWindowed.getEmg());
        this.accXMean = calcMean(chestDeviceWindowed.getAccX());
        this.accXStd = calcStd(chestDeviceWindowed.getAccX(), accXMean);
        this.accXMax = Collections.max(chestDeviceWindowed.getAccX());
        this.accXMin = Collections.min(chestDeviceWindowed.getAccX());
        this.accYMean = calcMean(chestDeviceWindowed.getAccY());
        this.accYStd = calcStd(chestDeviceWindowed.getAccY(), accYMean);
        this.accYMax = Collections.max(chestDeviceWindowed.getAccY());
        this.accYMin = Collections.min(chestDeviceWindowed.getAccY());
        this.accZMean = calcMean(chestDeviceWindowed.getAccZ());
        this.accZStd = calcStd(chestDeviceWindowed.getAccZ(), accZMean);
        this.accZMax = Collections.max(chestDeviceWindowed.getAccZ());
        this.accZMin = Collections.min(chestDeviceWindowed.getAccZ());
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
                ", recordId=" + recordId +
                ", timestamp=" + timestamp +
                '}';
    }
}
