package tutorial.internationalization.configuration;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Ioana on 2/28/2016.
 */
public class LocalizedText {
    ResourceBundle resourceBundle;

    public LocalizedText(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("main.resources.messages", locale);
    }

    public String getLocalizedText(String key) {
        return resourceBundle.getString(key);
    }
}
