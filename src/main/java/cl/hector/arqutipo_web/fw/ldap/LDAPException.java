package cl.hector.arqutipo_web.fw.security.ldap;

import cl.hector.arqutipo_web.fw.errors.MineducException;


public class LDAPException extends MineducException {
    public static final long GENERIC_ERROR = -1;
    public static final long USER_NOT_FOUND = -2;
    public static final long CONTROLLER_NOT_CONFIGURED_ERROR = -3;
    public static final long ILLEGAL_WORD_IN_PASSWORD_ERROR = -4;
    public static final long USER_NOT_FOUND_ERROR = -5;
    public static final long INVALID_CREDENTIALS_ERROR = -6;
    public static final long LOGON_NOT_PERMITTED_ERROR = -7;
    public static final long PASSWORD_EXPIRED_ERROR = -8;
    public static final long ACCOUNT_DISABLED_ERROR = -9;
    public static final long ACCOUNT_EXPIRED_ERROR = -10;
    public static final long USER_MUST_CHANGE_PASSWORD_ERROR = -11;
    public static final long ACCOUNT_LOCKED_OUT_ERROR = -12;
    public static final long EMPTY_USER_OR_PASSWORD = -13;

    private String message;
    private Exception orginalException;

    /**
     * Constructor con parametros
     *
     * @param inCode - Codigo de error
     * @param msg    - mensaje de error
     * @param orig   - Exception orginal
     **/
    public LDAPException(long inCode, String msg, Exception orig) {
        setCode(inCode);
        setMessage(msg);
        setOrginalException(orig);
    }

    /**
     * Constructor con parametros
     *
     * @param msg  - mensaje de error
     * @param orig - Exception orginal
     **/
    public LDAPException(String msg, Exception orig) {
        this(GENERIC_ERROR, msg, orig);
    }

    /**
     * Este metodo traduce entre un codigo de error como un string a un codigo como
     * int. Los codigos traducciodos son:
     * <p>
     * 52e - invalid credentials, se traduce a
     * 525 - User unknown
     * 530 - not permitted to logon at this time
     * 532 - password expired
     * 533 - account disabled
     * 701 - account expired
     * 773 - user must reset password
     * 775 - Account locked out
     *
     * @param errorCodeStr - codigo de error como string
     * @return int - codigo de error como int
     **/
    public static long parseError(String errorCodeStr) {
        String[] errorStrs = {"52e", "525", "530", "532", "533", "701", "773", "775"};
        long[] errorCodes = {INVALID_CREDENTIALS_ERROR, USER_NOT_FOUND, LOGON_NOT_PERMITTED_ERROR,
                PASSWORD_EXPIRED_ERROR, ACCOUNT_DISABLED_ERROR, ACCOUNT_EXPIRED_ERROR,
                USER_MUST_CHANGE_PASSWORD_ERROR, ACCOUNT_LOCKED_OUT_ERROR};
        int i;

        for (i = 0; i < errorStrs.length; i++) {
            if (errorStrs[i].equalsIgnoreCase(errorCodeStr)) {
                return errorCodes[i];
            }
        }
        return GENERIC_ERROR;
    }

    // Setters & getters
    public String getMessage() {
        return message;
    }

    public void setMessage(String v) {
        message = v;
    }

    public Exception getOrginalException() {
        return orginalException;
    }

    public void setOrginalException(Exception v) {
        orginalException = v;
    }

    /*
     * Overides toString method for convinience
     *
     * @return A string message representing de exception
     */
    public String toString() {
        String messageStr = "";

        if (getMessage() != null) {
            messageStr = "[Codigo " + getCode() + "]" + getMessage();
        }

        if (getOrginalException() != null) {
            messageStr = messageStr + ",Exception orginal [" + getOrginalException().toString() + "]";
        }

        return messageStr;
    }

}

