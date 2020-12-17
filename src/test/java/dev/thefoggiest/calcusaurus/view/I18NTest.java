/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.thefoggiest.calcusaurus.view;

import java.util.Locale;
import java.util.ResourceBundle;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author diederick
 */
public class I18NTest {

    @org.junit.jupiter.api.Test
    public void i18ntest() {        
        Locale englishLocale = new Locale("en", "GB");
        ResourceBundle englishBundle = ResourceBundle.getBundle("Bundle", englishLocale);
        assertEquals("What would you like?", englishBundle.getString("WAT WIL JE DOEN?"));

        Locale dutchLocale = new Locale("nl", "NL");
        ResourceBundle dutchBundle = ResourceBundle.getBundle("Bundle", dutchLocale);
        assertEquals("Wat wil je doen?", dutchBundle.getString("WAT WIL JE DOEN?"));
    }
}
