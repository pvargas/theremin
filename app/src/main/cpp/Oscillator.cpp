//
// Created by pablo on 10/14/2023.
//

#include "Oscillator.h"
#include <math.h>

#define TWO_PI (3.14159 * 2)
//#define AMPLITUDE 0.3
#define FREQUENCY 440.0
float amplitude = 0.3;

void Oscillator::setSampleRate(int32_t sampleRate) {
    phaseIncrement_ = (TWO_PI * FREQUENCY) / (double) sampleRate;
}

void Oscillator::setSampleRateWithMultiplier(int32_t sampleRate, float multiplier) {
//    phaseIncrement_ = (TWO_PI * ( FREQUENCY + (multiplier * 6))) / (double) sampleRate;
    phaseIncrement_ = (TWO_PI *  multiplier) / (double) sampleRate;
}

void Oscillator::setAmplitude(float newAmplitude) {
    amplitude = newAmplitude;
}

void Oscillator::setWaveOn(bool isWaveOn) {
    isWaveOn_.store(isWaveOn);
}

void Oscillator::render(float *audioData, int32_t numFrames) {

    if (!isWaveOn_.load()) phase_ = 0;

    for (int i = 0; i < numFrames; i++) {

        if (isWaveOn_.load()) {

            // Calculates the next sample value for the sine wave.
            audioData[i] = (float) (sin(phase_) * amplitude);

            // Increments the phase, handling wrap around.
            phase_ += phaseIncrement_;
            if (phase_ > TWO_PI) phase_ -= TWO_PI;

        } else {
            // Outputs silence by setting sample value to zero.
            audioData[i] = 0;
        }
    }
}
