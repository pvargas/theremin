//#include <jni.h>
//#include <string>
//
//extern "C" JNIEXPORT jstring JNICALL
//Java_com_quadbytes_theremin_MainActivity_stringFromJNI(
//        JNIEnv* env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}

#include <jni.h>
#include <android/input.h>
#include "AudioEngine.h"
#include <string>

static AudioEngine *audioEngine = new AudioEngine();

extern "C" {

//JNIEXPORT void JNICALL
//Java_com_quadbytes_theremin_MainActivity_toggleSound(JNIEnv *env, jobject obj, jboolean soundOn) {
//    switch (soundOn) {
//        case true:
//            audioEngine->setToneOn(true);
//            break;
//        case false:
//            audioEngine->setToneOn(false);
//            break;
//        default:
//            break;
//    }
//}
//
//JNIEXPORT void JNICALL
//Java_com_quadbytes_theremin_MainActivity_startEngine(JNIEnv *env, jobject /* this */) {
//    audioEngine->start();
//}
//
//JNIEXPORT void JNICALL
//Java_com_quadbytes_theremin_MainActivity_stopEngine(JNIEnv *env, jobject /* this */) {
//    audioEngine->stop();
//}
//
//JNIEXPORT void JNICALL
//Java_com_quadbytes_theremin_MainActivity_changePitch(JNIEnv *env, jobject obj, jfloat multiplier) {
//    audioEngine->setPitch(multiplier);
//}

// sounds file

JNIEXPORT void JNICALL
Java_com_quadbytes_theremin_Sounds_toggleSound(JNIEnv *env, jobject obj, jboolean soundOn) {
    switch (soundOn) {
        case true:
            audioEngine->setToneOn(true);
            break;
        case false:
            audioEngine->setToneOn(false);
            break;
        default:
            break;
    }
}

JNIEXPORT void JNICALL
Java_com_quadbytes_theremin_Sounds_startEngine(JNIEnv *env, jobject /* this */) {
    audioEngine->start();
}

JNIEXPORT void JNICALL
Java_com_quadbytes_theremin_Sounds_stopEngine(JNIEnv *env, jobject /* this */) {
    audioEngine->stop();
}

JNIEXPORT void JNICALL
Java_com_quadbytes_theremin_Sounds_changePitch(JNIEnv *env, jobject obj, jfloat multiplier) {
    audioEngine->setPitch(multiplier);
}
JNIEXPORT void JNICALL
Java_com_quadbytes_theremin_Sounds_changeVolume(JNIEnv *env, jobject obj, jfloat newVolume) {
    audioEngine->setVolume(newVolume);
}

}
