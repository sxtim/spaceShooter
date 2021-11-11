import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class AddFont{


        private static Font ttfBase = null;
        private static Font Font = null;
        private static InputStream myStream = null;
        private static final String FONT_PATH = "fonts/spacehorizon.ttf";

        public static Font createFontSpaceHorizon(int size) {
            try {
                myStream = new BufferedInputStream(
                        new FileInputStream(FONT_PATH));
                ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
                Font = ttfBase.deriveFont(Font.BOLD, size);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Font not loaded.");
            }
            return Font;
        }
    }



