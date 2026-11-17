public class Language {
    public static boolean english = true;

    public static String translate(String englishText, String spanishText){
        if (english){
            return englishText;
        }
        return spanishText;
    }
}
