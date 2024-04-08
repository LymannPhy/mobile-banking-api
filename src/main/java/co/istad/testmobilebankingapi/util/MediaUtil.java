package co.istad.testmobilebankingapi.util;

public class MediaUtil {
    public static String extractExtension(String mediaName){
        int lastDotIndex = mediaName.lastIndexOf(".");
        return mediaName.substring(lastDotIndex + 1);
    }
}
