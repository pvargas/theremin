

#ifndef THEREMIN_OSCILLATOR_H
#define THEREMIN_OSCILLATOR_H

#include <atomic>
#include <stdint.h>

class Oscillator {
public:
    void setWaveOn(bool isWaveOn);
    void setSampleRate(int32_t sampleRate);
    void setSampleRateWithMultiplier(int32_t sampleRate, float multiplier);
    void setAmplitude(float newAmplitude);
    void render(float *audioData, int32_t numFrames);

private:
    std::atomic<bool> isWaveOn_{false};
    double phase_ = 0.0;
    double phaseIncrement_ = 0.0;
    double amplitude_ = 0.3;
};



#endif //THEREMIN_OSCILLATOR_H
