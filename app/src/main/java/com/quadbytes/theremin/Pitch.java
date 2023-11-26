package com.quadbytes.theremin;
// https://en.wikipedia.org/wiki/Piano_key_frequencies
public class Pitch {
    static final float C4 = 261.6256f;
    static final float D4 = 293.6648f;
    static final float E4 = 329.6276f;
    static final float F4 = 349.2282f;
    static final float G4 = 391.9954f;
    static final float A4 = 440.0000f;
    static final float B4 = 493.8833f;

    static final float C5 = 523.2511f;
    static final float D5 = 587.3295f;
    static final float E5 = 659.2551f;
    static final float F5 = 698.4565f;
    static final float G5 = 783.9909f;
    static final float A5 = 880.0000f;
    static final float B5 = 987.7666f;
    static final float C6 = 1046.502f;
    public static float quantizePitch(float input) {
        float pitch = 440f;
        float increment = 0.0714285714286f;

        if (input < 0 || input >= 0 && input < increment) { pitch = C4;}
        if (input >= increment && input < increment * 2) { pitch = D4;}
        if (input >= increment * 2 && input < increment * 3) { pitch = E4;}
        if (input >= increment * 3 && input < increment * 4) { pitch = F4;}
        if (input >= increment * 4 && input < increment * 5) { pitch = G4;}
        if (input >= increment * 5 && input < increment * 6) { pitch = A4;}
        if (input >= increment * 6 && input < increment * 7) { pitch = B4;}
        if (input >= increment * 7 && input < increment * 8) { pitch = C5;}
        if (input >= increment * 8 && input < increment * 9) { pitch = D5;}
        if (input >= increment * 9 && input < increment * 10) { pitch = E5;}
        if (input >= increment * 10 && input < increment * 11) { pitch = F5;}
        if (input >= increment * 11 && input < increment * 12) { pitch = G5;}
        if (input >= increment * 12 && input < increment * 13) { pitch = A5;}
        if (input >= increment * 13 && input < increment * 14) { pitch = B5;}
        if (input >= increment * 14) { pitch = C6;}

        return pitch;
    }
}
