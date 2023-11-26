//
// Created by pablo on 10/14/2023.
//

#ifndef THEREMIN_AUDIOENGINE_H
#define THEREMIN_AUDIOENGINE_H


#include <aaudio/AAudio.h>
#include "Oscillator.h"

class AudioEngine {
public:
    bool start();
    void stop();
    void restart();
    void setToneOn(bool isToneOn);
    void setPitch(float multiplier);
    void setVolume(float multiplier);

private:
    Oscillator oscillator_;
    AAudioStream *stream_;
};


#endif //THEREMIN_AUDIOENGINE_H
