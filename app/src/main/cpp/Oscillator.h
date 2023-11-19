//
// Created by pablo on 10/14/2023.
//

#ifndef THEREMIN_OSCILLATOR_H
#define THEREMIN_OSCILLATOR_H

#include <atomic>
#include <stdint.h>

class Oscillator {
public:
    void setWaveOn(bool isWaveOn);
    void setSampleRate(int32_t sampleRate);
    void setSampleRateWithMultiplier(int32_t sampleRate, float multiplier);
    void render(float *audioData, int32_t numFrames);

private:
    std::atomic<bool> isWaveOn_{false};
    double phase_ = 0.0;
    double phaseIncrement_ = 0.0;
};



#endif //THEREMIN_OSCILLATOR_H