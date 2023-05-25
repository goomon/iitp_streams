package org.example.model;

import lombok.*;

import java.util.Collections;
import java.util.List;

//@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WristDeviceFeature {
    private String userId;
    private long timestamp;
    private double bvpMean;
    private double bvpStd;
    private double bvpMax;
    private double bvpMin;
    private double edaMean;
    private double edaStd;
    private double edaMax;
    private double edaMin;
    private double tempMean;
    private double tempStd;
    private double tempMax;
    private double tempMin;
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

    public WristDeviceFeature(WristDevice wristDeviceWindowed) {
        this.userId = wristDeviceWindowed.getUserId();
        this.timestamp = wristDeviceWindowed.getTimestamp();
        this.bvpMean = calcMean(wristDeviceWindowed.getBvp());
        this.bvpStd = calcStd(wristDeviceWindowed.getBvp(), bvpMean);
        this.bvpMax = Collections.max(wristDeviceWindowed.getBvp());
        this.bvpMin = Collections.min(wristDeviceWindowed.getBvp());
        this.edaMean = calcMean(wristDeviceWindowed.getEda());
        this.edaStd = calcStd(wristDeviceWindowed.getEda(), edaMean);
        this.edaMax = Collections.max(wristDeviceWindowed.getEda());
        this.edaMin = Collections.min(wristDeviceWindowed.getEda());
        this.tempMean = calcMean(wristDeviceWindowed.getTemp());
        this.tempStd = calcStd(wristDeviceWindowed.getTemp(), tempMean);
        this.tempMax = Collections.max(wristDeviceWindowed.getTemp());
        this.tempMin = Collections.min(wristDeviceWindowed.getTemp());
        this.accXMean = calcMean(wristDeviceWindowed.getAccX());
        this.accXStd = calcStd(wristDeviceWindowed.getAccX(), accXMean);
        this.accXMax = Collections.max(wristDeviceWindowed.getAccX());
        this.accXMin = Collections.min(wristDeviceWindowed.getAccX());
        this.accYMean = calcMean(wristDeviceWindowed.getAccY());
        this.accYStd = calcStd(wristDeviceWindowed.getAccY(), accYMean);
        this.accYMax = Collections.max(wristDeviceWindowed.getAccY());
        this.accYMin = Collections.min(wristDeviceWindowed.getAccY());
        this.accZMean = calcMean(wristDeviceWindowed.getAccZ());
        this.accZStd = calcStd(wristDeviceWindowed.getAccZ(), accZMean);
        this.accZMax = Collections.max(wristDeviceWindowed.getAccZ());
        this.accZMin = Collections.min(wristDeviceWindowed.getAccZ());
    }

    private double calcMean(List<Double> data) {
        return data
                .stream()
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

    private double calcStd(List<Double> data, double mean) {
        double variance = data
                .stream()
                .mapToDouble(x -> (x - mean) * (x - mean))
                .average()
                .orElse(0);
        return Math.sqrt(variance);
    }

    @Override
    public String toString() {
        return "WristDeviceFeature{" +
                "userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
