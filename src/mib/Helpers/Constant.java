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
    
    public static final String SUCCESS_PASSWORD_SET = "Nytt l�senord satt.\nAnv�nd ditt nya l�senord n�sta g�ng du loggar in.";
    
    public static final String ERROR_DATABASE = "N�gonting gick fel med vid upph�mtning av databasen. F�rs�k igen!";
    public static final String ERROR_LOGIN_AGENT = "Ditt Agent ID eller l�senord �r fel.";
    public static final String ERROR_LOGIN_ALIEN = "Ditt Alien ID eller l�senord �r fel.";
    public static final String ERROR_EMPTY_TEXT_FIELD = "V�nligen fyll i text f�ltet.";
    public static final String ERROR_PASSWORD_LENGTH = "L�senordet m�ste vara 6 tecken eller mindre.";
    public static final String ERROR_OLD_PASSWORD_INCORRECT = "Ditt gammla l�senord �r fel.";
    
    
    
    
    private Constant() { }
}
