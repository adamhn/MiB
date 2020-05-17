/*
 * Copyright (C) 2020 by Adam Hassan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Adam Hassan <adamhassan@pm.me>, May 2020
 */
package mib.Helpers;

/**
 * Constant class is used throughout the application with
 * the purpose to create a universal place for static data.
 * @author Adam Hassan <adamhassan@pm.me>
 */
public final class Constant {
    
    public static final String LOGIN_TYPE_AGENT = "Som Agent";
    public static final String LOGIN_TYPE_ALIEN = "Som Alien";
    
    public static final String ENTITY_TYPE_AGENT = "Agent";
    public static final String ENTITY_TYPE_ALIEN = "Alien";
    
    public static final String SUCCESS_PASSWORD_SET = "Nytt lösenord satt.\nAnvänd ditt nya lösenord nästa gång du loggar in.";
    
    public static final String ERROR_DATABASE = "Någonting gick fel med vid upphämtning av databasen. Försök igen!";
    public static final String ERROR_LOGIN_AGENT = "Ditt Agent ID eller lösenord är fel.";
    public static final String ERROR_LOGIN_ALIEN = "Ditt Alien ID eller lösenord är fel.";
    public static final String ERROR_EMPTY_TEXT_FIELD = "Vänligen fyll i text fältet.";
    public static final String ERROR_PASSWORD_LENGTH = "Lösenordet måste vara 6 tecken eller mindre.";
    public static final String ERROR_OLD_PASSWORD_INCORRECT = "Ditt gammla lösenord är fel.";
    
    
    
    
    private Constant() { }
}
