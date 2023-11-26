package com.quadbytes.theremin;

public class Sounds {
    native static void toggleSound(boolean soundOn);
    native static void startEngine();
    native  static void stopEngine();
    native static void changePitch(float multiplier);
    native static void changeVolume(float newVolume);
}
