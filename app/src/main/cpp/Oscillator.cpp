
#include "Oscillator.h"
#include <math.h>

#define TWO_PI (3.14159 * 2)
#define FREQUENCY 440.0

void Oscillator::setSampleRate(int32_t sampleRate) {
    phaseIncrement_ = (TWO_PI * FREQUENCY) / (double) sampleRate;
}

void Oscillator::setSampleRateWithMultiplier(int32_t sampleRate, float multiplier) {
    phaseIncrement_ = (TWO_PI *  multiplier) / (double) sampleRate;
}

void Oscillator::setAmplitude(float newAmplitude) {
    // TODO: this is the most simple approach; it works,
    // but the jump in volume could be smoother
    if (newAmplitude > 1.55) {
        amplitude_ = 1.55;
    } else {
        amplitude_ = newAmplitude;
    }
}

void Oscillator::setWaveOn(bool isWaveOn) {
    isWaveOn_.store(isWaveOn);
}

void Oscillator::render(float *audioData, int32_t numFrames) {

    if (!isWaveOn_.load()) phase_ = 0;

    for (int i = 0; i < numFrames; i++) {

        if (isWaveOn_.load()) {

            // Calculates the next sample value for the sine wave.
            audioData[i] = (float) (sin(phase_) * amplitude_);

            // Increments the phase, handling wrap around.
            phase_ += phaseIncrement_;
            if (phase_ > TWO_PI) phase_ -= TWO_PI;

        } else {
            // Outputs silence by setting sample value to zero.
            audioData[i] = 0;
        }
    }
}
